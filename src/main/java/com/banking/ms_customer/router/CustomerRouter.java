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
    public RouterFunction<ServerResponse> customerRoutes(CustomerHandler handler){
        return route(
                GET("/customers"), handler::findAll)
                .andRoute(GET("/customers/{id}"), handler::findById)
                .andRoute(GET("/customers/document/{documentNumber}"), handler::findByDocumentNumber)
                .andRoute(POST("/customers"), handler::create)
                .andRoute(PUT("/customers/{id}"), handler::update)
                .andRoute(DELETE("/customers/{id}"), handler::delete);
        //return RouterFunctions.route(GET("/customers/hello"), handler::hello);
    }
}