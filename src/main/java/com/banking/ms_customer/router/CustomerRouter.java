package com.banking.ms_customer.router;

import com.banking.ms_customer.handler.CustomerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class CustomerRouter {

    @Bean
    public RouterFunction<ServerResponse> customerRoutes(CustomerHandler handler) {
        return route(
                GET("/api/v1/customers"), handler::findAll)
                .andRoute(GET("/api/v1/customers/{id}"), handler::findById)
                .andRoute(GET("/api/v1/customers/document/{documentNumber}"), handler::findByDocumentNumber)
                .andRoute(POST("/api/v1/customers"), handler::create)
                .andRoute(PUT("/api/v1/customers/{id}"), handler::update)
                .andRoute(DELETE("/api/v1/customers/{id}"), handler::delete);
        // return RouterFunctions.route(GET("/customers/hello"), handler::hello);
    }
}