package com.catalystone.migrationservice.router;


import com.catalystone.cv.migrations.MigrationHandler;
import com.catalystone.migrationservice.handler.MigrationHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.catalystone.cv.constants.EndPoints;
import com.catalystone.cv.mycv.handler.PageFieldUpdateRequestHandler;
import com.catalystone.cv.mycv.handler.RecordsUpdateRequestHandler;
import com.catalystone.cv.setup.handler.CvSetupHandler;

@Configuration
public class Router {

    @Bean
    RouterFunction<ServerResponse> routerFunction(MigrationHandler migrationHandler) {

        return RouterFunctions
                .route(RequestPredicates.GET("cv/migration").and(
                        RequestPredicates.contentType(MediaType.APPLICATION_JSON)), migrationHandler::startCvMigration);

    }
}
