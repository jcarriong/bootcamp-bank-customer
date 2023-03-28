package com.bootcamp.bankcustomer.service;

import com.bootcamp.bankcustomer.model.BankCustomer;
import com.bootcamp.bankcustomer.repository.BankCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankCustomerImpl implements BankCustomerService {

    @Autowired
    BankCustomerRepository bankCustomerRepository;

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

    /*@Override
    public void updateByDni(BankCustomer bankCustomer, String dni) {
        BankCustomer currentBankCustomer = bankCustomerRepository.findBankCustomerByDni(dni);

        if (ObjectUtils.isEmpty(currentBankCustomer)) {
            throw new RuntimeException("No se encontró el cliente en el registro");

        } else {
            currentBankCustomer.setFirstName(bankCustomer.getFirstName());
            currentBankCustomer.setLastName(bankCustomer.getLastName());
            currentBankCustomer.setEmail(bankCustomer.getEmail());
            currentBankCustomer.setUsername(bankCustomer.getUsername());
            currentBankCustomer.setAddress(bankCustomer.getAddress());

            bankCustomerRepository.save(currentBankCustomer);

        }
    }

    @Override
    public void deleteById(String id) {
        bankCustomerRepository.deleteById(id);
    }*/
}
