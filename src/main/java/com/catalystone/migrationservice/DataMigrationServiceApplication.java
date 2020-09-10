package com.catalystone.migrationservice;

import com.catalystone.auth.AuthConstants;
import com.catalystone.auth.TenantIdentity;
import com.catalystone.tenancy.api.CoTenancyApiClientConfiguration;
import com.catalystone.tenancy.api.TenantApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

import java.util.UUID;

@SpringBootApplication
@Import({CoTenancyApiClientConfiguration.class})
public class DataMigrationServiceApplication {

    @Autowired
    TenantApiClient apiClient;

    public static void main(String[] args) {
        SpringApplication.run(DataMigrationServiceApplication.class, args);
    }

    /*@Bean
    public WebFilter setupCvPermissionCheck() {
        return (exchange, chain) -> exchange.getRequest()
                .getHeaders()
                .get(AuthConstants.HEADER_TENANT_ID)
                .stream()
                .findFirst()
                .map(UUID::fromString)
                .map(TenantIdentity::new)
                .map(tenantIdentity -> {
                    return
                            apiClient.getProperties(tenantIdentity)
                                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "No tenant for origin")))
                                    .map(prop -> {
                                        System.out.println(prop);
                                        return prop;
                                    });
                })
                .get()
                .flatMap(monolithconfig -> chain.filter(exchange).subscriberContext((context -> context.put("MonolithConfig.class", monolithconfig))));


    }*/

}
