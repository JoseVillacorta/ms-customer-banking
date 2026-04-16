package com.banking.ms_customer.repository;

import com.banking.ms_customer.entities.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
    // Metodo para buscar por documento
    Mono<Customer> findByDocumentNumber(String documentNumber);

    // Metodo para buscar por zona
    Flux<Customer> findByZoneId(Long zoneId);
}
