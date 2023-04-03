package com.bootcamp.bankcustomer.repository;

import com.bootcamp.bankcustomer.model.BankCustomer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BankCustomerRepository extends ReactiveMongoRepository<BankCustomer, String> {

    Mono<BankCustomer> findBankCustomerByDni(String dni);

}
