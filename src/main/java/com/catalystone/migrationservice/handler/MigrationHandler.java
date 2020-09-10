package com.catalystone.migrationservice.handler;

import com.catalystone.migrationservice.model.Scale;
import com.catalystone.migrationservice.repository.ScaleRepository;
import com.catalystone.migrationservice.tenancy.utility.WebfluxDatabaseUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class MigrationHandler {

    @Autowired
    ScaleRepository scaleRepository;

    public Mono<ServerResponse> startCvMigration(ServerRequest serverRequest) {
        Mono<List<Scale>> monoOfListOfScales = WebfluxDatabaseUtility.fromCallable(() -> scaleRepository.findAll());

        return monoOfListOfScales.flatMap(res -> ServerResponse.ok().body(BodyInserters.fromObject(res)));

//        TenantProperties tp = Tenant.getCurrentTenant().getProperties();

//        return ServerResponse.ok().body(BodyInserters.fromObject(tp));
    }
}
