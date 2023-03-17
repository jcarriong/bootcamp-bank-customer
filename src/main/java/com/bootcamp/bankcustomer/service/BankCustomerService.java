package com.bootcamp.bankcustomer.service;

import com.bootcamp.bankcustomer.model.BankCustomer;

import java.util.List;
import java.util.Optional;

public interface BankCustomerService {
    List<BankCustomer> findAll();

    Optional<BankCustomer> findById(String id);

    Optional<BankCustomer> findByDocumentId(String dni);

    Optional<BankCustomer> save(BankCustomer bankCustomer);

    void updateByDni(BankCustomer bankCustomer, String dni);

    void deleteById(String id);
}
