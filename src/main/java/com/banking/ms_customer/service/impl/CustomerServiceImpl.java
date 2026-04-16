package com.banking.ms_customer.service.impl;

import com.banking.ms_customer.entities.Customer;
import com.banking.ms_customer.repository.CustomerRepository;
import com.banking.ms_customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Flux<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Mono<Customer> findByDocumentNumber(String documentNumber) {
        return customerRepository.findByDocumentNumber(documentNumber);
    }

    @Override
    public Mono<Customer> create(Customer customer) {
        customer.setStatus("ACTIVE");
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Customer> update(Long id, Customer customer) {
        return customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado")))
                .flatMap(existingCustomer -> {
                    existingCustomer.setFirstName(customer.getFirstName());
                    existingCustomer.setLastName(customer.getLastName());
                    existingCustomer.setEmail(customer.getEmail());
                    existingCustomer.setPhone(customer.getPhone());
                    existingCustomer.setUpdatedAt(LocalDateTime.now());
                    existingCustomer.setAddress(customer.getAddress());
                    existingCustomer.setZoneId(customer.getZoneId());
                    return customerRepository.save(existingCustomer);
                });
    }

    @Override
    public Mono<Void> delete(Long id) {
        return customerRepository.findById(id)
                .flatMap(customerRepository::delete);
    }

    @Override
    public Flux<Customer> findByZoneId(Long zoneId) {
        return customerRepository.findByZoneId(zoneId);
    }

}
