package com.banking.ms_customer.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class CustomerHandler {
    public Mono<ServerResponse> hello(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("message", "Hola desde ms-customer"));
    }
}
