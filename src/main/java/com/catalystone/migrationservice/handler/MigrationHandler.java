package com.catalystone.migrationservice.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class MigrationHandler {
    public Mono<ServerResponse> startCvMigration(ServerRequest serverRequest) {
        return ServerResponse.ok().build();
    }
}
