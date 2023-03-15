package com.bootcamp.bankcustomer.repository;

import com.bootcamp.bankcustomer.model.BankCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankCustomerRepository extends MongoRepository<BankCustomer, String> {

    BankCustomer findBankCustomerByDni(String dni);
}
