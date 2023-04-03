package com.bootcamp.bankcustomer.service;

import com.bootcamp.bankcustomer.model.BankCustomer;
import com.bootcamp.bankcustomer.proxy.AccountRetrofitClient;
import com.bootcamp.bankcustomer.proxy.beans.bankAccount.BankAccountDto;
import com.bootcamp.bankcustomer.repository.BankCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankCustomerImpl implements BankCustomerService {

    @Autowired
    BankCustomerRepository bankCustomerRepository;

    @Autowired
    AccountRetrofitClient accountRetrofitClient;

    @Override
    public Flux<List<BankAccountDto>> getAccountsByCustomer(String idCustomer) {
        return accountRetrofitClient.getAccountsByCustomer(idCustomer);

    }

    @Override
    public Flux<BankCustomer> findAll() {
        return bankCustomerRepository.findAll()
                .onErrorReturn(new BankCustomer());
        /*  .stream().filter(bankCustomer -> bankCustomer.getAddress().getDistrict().equalsIgnoreCase("San juan de lurigancho"))*/
        /* .collect(Collectors.toList());*/
    }

    @Override
    public Mono<BankCustomer> findById(String id) {
        return bankCustomerRepository.findById(id);
    }

    @Override
    public Mono<BankCustomer> findByDocumentId(String dni) {
        return bankCustomerRepository.findBankCustomerByDni(dni)
                .onErrorReturn(new BankCustomer());
        /* return bankCustomerRepository.findBankCustomerByDni(dni).onErrorMap(throwable -> new RuntimeException("El numero"));*/
        /*.orElseThrow(() -> new RuntimeException("El numero de documento ingresado no existe en la relación de clientes")));*/
    }

    @Override
    public Mono<BankCustomer> save(BankCustomer bankCustomer) {
        return bankCustomerRepository.save(bankCustomer);

        /*BankCustomer bankCustomer1 = bankCustomerRepository.findBankCustomerByDni(bankCustomer.getDni());

        if (!ObjectUtils.isEmpty(bankCustomer1)) {
            throw new RuntimeException("No se puede realizar el registro, ya existe un cliente con el número de documento");
        }
        return Optional.of(bankCustomerRepository.save(bankCustomer));*/
    }

    @Override
    public Mono<BankCustomer> updateCustomer(BankCustomer bankCustomer, String idCustomer) {

        return bankCustomerRepository.findById(idCustomer)
                .flatMap(currentBankCustomer -> {
                    currentBankCustomer.setCustomerType(bankCustomer.getCustomerType());
                    currentBankCustomer.setCustomerCategory(bankCustomer.getCustomerCategory());
                    currentBankCustomer.setDni(bankCustomer.getDni());
                    currentBankCustomer.setFirstName(bankCustomer.getFirstName());
                    currentBankCustomer.setLastName(bankCustomer.getLastName());
                    currentBankCustomer.setEmail(bankCustomer.getEmail());
                    currentBankCustomer.setUsername(bankCustomer.getUsername());
                    currentBankCustomer.setAddress(bankCustomer.getAddress());
                    currentBankCustomer.setUpdateDatetime(LocalDateTime.now());
                    return bankCustomerRepository.save(currentBankCustomer);
                });


    }

    @Override
    public Mono<BankCustomer> deleteCustomerById(String idCustomer) {
        return bankCustomerRepository.findById(idCustomer)
                .flatMap(existingCustomer -> bankCustomerRepository.delete(existingCustomer)
                        .then(Mono.just(existingCustomer)));
        /*bankCustomerRepository.deleteById(id);*/
    }
}
