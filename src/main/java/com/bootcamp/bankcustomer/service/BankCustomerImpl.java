package com.bootcamp.bankcustomer.service;

import com.bootcamp.bankcustomer.exceptions.CustomerNotFoundException;
import com.bootcamp.bankcustomer.exceptions.CustomerTypeBadRequestException;
import com.bootcamp.bankcustomer.model.BankCustomer;
import com.bootcamp.bankcustomer.model.response.CustomerAccountResponse;
import com.bootcamp.bankcustomer.proxy.AccountRetrofitClient;
import com.bootcamp.bankcustomer.proxy.beans.bankAccount.BankAccountDto;
import com.bootcamp.bankcustomer.repository.BankCustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
public class BankCustomerImpl implements BankCustomerService {

    @Autowired
    BankCustomerRepository bankCustomerRepository;

    @Autowired
    AccountRetrofitClient accountRetrofitClient;

    @Override
    public Flux<CustomerAccountResponse> getAccountsByCustomer(String idCustomer) {
        CustomerAccountResponse customerAccountResponse = new CustomerAccountResponse();
        return bankCustomerRepository.findById(idCustomer)
                .flatMapMany(bankCustomer -> {
                    customerAccountResponse.setCustomer(bankCustomer);
                    return Flux.just(customerAccountResponse);
                }).flatMap(response -> accountRetrofitClient.getAccountsByCustomer(idCustomer)
                        .map(accounts -> {
                            customerAccountResponse.setBankAccountDtoList(accounts);
                            return response;
                        }));

        //return accountRetrofitClient.getAccountsByCustomer(idCustomer);

    }

    @Override
    public Mono<BankAccountDto> saveAccount(BankAccountDto bankAccountDto) {
        return accountRetrofitClient.registerAccount(bankAccountDto);
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
        Mono<BankCustomer> bankCustomer = bankCustomerRepository.findById(id);
        return bankCustomerRepository.findById(id)
                .switchIfEmpty(Mono.error(() ->
                        new CustomerNotFoundException(id)));
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
        if (!(bankCustomer.getCustomerType().equalsIgnoreCase("personal")
                || bankCustomer.getCustomerType().equalsIgnoreCase("empresarial"))) {
            log.info("fetch customer: " + bankCustomer.getCustomerType());
            return Mono.error(() -> new CustomerTypeBadRequestException(bankCustomer.getCustomerType()));
        } else {
            return bankCustomerRepository.save(bankCustomer);
        }

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
