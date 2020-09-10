package com.catalystone.migrationservice.tenancy.utility;

import com.catalystone.migrationservice.tenancy.contextSetClasses.TenantContext;
import com.catalystone.migrationservice.tenancy.model.Tenant;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class WebfluxDatabaseUtility {

    private WebfluxDatabaseUtility() {
    }

    private static Mono<Optional<TenantContext>> getConnectionProperties() {
        return Mono.subscriberContext().map(context -> context.getOrEmpty(TenantContext.class));
    }

    public static <T> Mono<T> fromCallable(Supplier<T> supplier) {
        return getConnectionProperties().flatMap(tenantContext -> {
            try {
                setContextDataInThread(tenantContext);
                T supplied = supplier.get();
                return Mono.justOrEmpty(supplied);
            } finally {
                clearContextDataInThread();
            }
        });

    }


    public static <T, R> Function<Mono<T>, Mono<R>> monoMap(Function<? super T, ? extends R> mapper) {
        return mono ->
                mono.zipWith(getConnectionProperties())
                        .map(tuples -> {
                            try {
                                Optional<TenantContext> tenantContext = tuples.getT2();
                                setContextDataInThread(tenantContext);
                                return mapper.apply(tuples.getT1());
                            } finally {
                                clearContextDataInThread();
                            }
                        });
    }

    private static void clearContextDataInThread() {
        Tenant.clear();
    }

    private static void setContextDataInThread(Optional<TenantContext> tenantContext) {
        if (tenantContext.isPresent())
            Tenant.setCurrentTenant(tenantContext.get());
    }

}
