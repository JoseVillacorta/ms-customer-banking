package com.banking.ms_customer.handler;

import com.banking.ms_customer.dto.ApiResponse;
import com.banking.ms_customer.entities.Customer;
import com.banking.ms_customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class CustomerHandler {

        private final CustomerService customerService;

        // Obtener todos los clientes
        public Mono<ServerResponse> findAll(ServerRequest request) {
                return customerService.findAll()
                                .collectList() // Agrupamos el flux en una lista para poder envolverla en ApiResponse
                                .flatMap(customers -> ServerResponse.ok()
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(ApiResponse.success(customers, "Ok",
                                                                HttpStatus.OK.value())));
        }

        // Obtener un cliente por su ID
        public Mono<ServerResponse> findById(ServerRequest request) {
                Long id = Long.valueOf(request.pathVariable("id"));
                return customerService.findById(id)
                                .flatMap(c -> ServerResponse.ok()
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(ApiResponse.success(c, "Ok", HttpStatus.OK.value())))
                                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                                                .bodyValue(ApiResponse.error("Customer not found",
                                                                HttpStatus.NOT_FOUND.value())));
        }

        // Obtener un cliente por su dni
        public Mono<ServerResponse> findByDocumentNumber(ServerRequest request) {
                String documentNumber = request.pathVariable("documentNumber");
                return customerService.findByDocumentNumber(documentNumber)
                                .flatMap(c -> ServerResponse.ok()
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(ApiResponse.success(c, "OK", HttpStatus.OK.value())))
                                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                                                .bodyValue(ApiResponse.error("Customer not found",
                                                                HttpStatus.NOT_FOUND.value())));
        }

        // Crear un nuevo cliente
        public Mono<ServerResponse> create(ServerRequest request) {
                return request.bodyToMono(Customer.class)
                                .flatMap(customerService::create)
                                .flatMap(c -> ServerResponse
                                                .created(URI.create("/customers/" + c.getId()))
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(ApiResponse.success(c, "Customer created successfully",
                                                                HttpStatus.CREATED.value())));
        }

        // Actualizar un cliente existente
        public Mono<ServerResponse> update(ServerRequest request) {
                Long id = Long.valueOf(request.pathVariable("id"));

                return request.bodyToMono(Customer.class)
                                .flatMap(c -> customerService.update(id, c))
                                .flatMap(c -> ServerResponse.ok()
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(ApiResponse.success(c, "Customer updated successfully",
                                                                HttpStatus.OK.value())))
                                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                                                .bodyValue(ApiResponse.error("Customer not found",
                                                                HttpStatus.NOT_FOUND.value())));
        }

        // Eliminar un cliente
        public Mono<ServerResponse> delete(ServerRequest request) {
                Long id = Long.valueOf(request.pathVariable("id"));
                return customerService.findById(id)
                                .flatMap(c -> customerService.delete(id)
                                                .then(ServerResponse.ok()
                                                                .contentType(MediaType.APPLICATION_JSON)
                                                                .bodyValue(ApiResponse.success(null,
                                                                                "Customer deleted successfully",
                                                                                HttpStatus.OK.value()))))
                                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                                                .bodyValue(ApiResponse.error("Customer not found",
                                                                HttpStatus.NOT_FOUND.value())));
        }

        // Obtener clientes por zona
        public Mono<ServerResponse> findByZoneId(ServerRequest request) {
                Long zoneId = Long.parseLong(request.pathVariable("zoneId"));
                return customerService.findByZoneId(zoneId)
                                .collectList()
                                .flatMap(customers -> ServerResponse.ok()
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .bodyValue(ApiResponse.success(customers, "Ok",
                                                                HttpStatus.OK.value())));
        }
}
