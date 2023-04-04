package com.bootcamp.bankcustomer.service;

import com.bootcamp.bankcustomer.model.BankCustomer;
import com.bootcamp.bankcustomer.model.response.CustomerAccountResponse;
import com.bootcamp.bankcustomer.proxy.beans.bankAccount.BankAccountDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Map;

public interface BankCustomerService {

    Flux<CustomerAccountResponse> getAccountsByCustomer(String idCustomer);

    Mono<BankAccountDto> saveAccount(BankAccountDto bankAccountDto);

    Flux<BankCustomer> findAll();

    Mono<BankCustomer> findById(String id);

    Mono<BankCustomer> findByDocumentId(String dni);

    Mono<BankCustomer> save(BankCustomer bankCustomer);

    Mono<BankCustomer> updateCustomer(BankCustomer bankCustomer, String idCustomer);

    Mono<BankCustomer> deleteCustomerById(String idCustomer);
}
