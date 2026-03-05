package com.banking.ms_customer.router;

import com.banking.ms_customer.handler.CustomerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class CustomerRouter {

    @Bean
    public RouterFunction<ServerResponse> customerRoutes(CustomerHandler handler){
        return RouterFunctions.route(GET("/customers/hello"), handler::hello);
    }
}