package com.banking.ms_customer.service;

import com.banking.ms_customer.entities.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    // Retorna varios clientes (flujo de datos)
    Flux<Customer> findAll();

    // Retorna 0 o 1 cliente
    Mono<Customer> findById(Long id);

    Mono<Customer> findByDocumentNumber(String documentNumber);

    // Guarda y retorna el cliente creado
    Mono<Customer> create(Customer customer);

    Mono<Customer> update(Long id, Customer customer);

    Mono<Void> delete(Long id);

}
