package com.banking.ms_customer.service;

import com.banking.ms_customer.entities.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    // Retorna varios clientes (flujo de datos)
    Flux<Customer> findAll();

    // Retorna 0 o 1 cliente
    Mono<Customer> findById(Long id);

    // Busca por documento
    Mono<Customer> findByDocumentNumber(String documentNumber);

    // Guarda y retorna el cliente creado
    Mono<Customer> create(Customer customer);

    // Actualiza y retorna el cliente actualizado
    Mono<Customer> update(Long id, Customer customer);

    // Elimina el cliente
    Mono<Void> delete(Long id);

    // Busca clientes por zona
    Flux<Customer> findByZoneId(Long zoneId);
}
