package com.bootcamp.bankcustomer.service;

import com.bootcamp.bankcustomer.model.BankCustomer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankCustomerService {
    Flux<BankCustomer> findAll();

    Mono<BankCustomer> findById(String id);

    Mono<BankCustomer> findByDocumentId(String dni);

    Mono<BankCustomer> save(BankCustomer bankCustomer);

    /*void updateByDni(BankCustomer bankCustomer, String dni);

    void deleteById(String id);*/
}
