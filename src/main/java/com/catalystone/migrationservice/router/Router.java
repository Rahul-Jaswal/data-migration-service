package com.catalystone.migrationservice.router;


import com.catalystone.migrationservice.handler.MigrationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class Router {

    @Bean
    RouterFunction<ServerResponse> routerFunction(MigrationHandler migrationHandler) {

        return RouterFunctions
                .route(RequestPredicates.GET("/cv/migration").and(
                        RequestPredicates.contentType(MediaType.APPLICATION_JSON)), migrationHandler::startCvMigration);

    }
}
