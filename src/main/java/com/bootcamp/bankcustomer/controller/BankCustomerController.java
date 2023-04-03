package com.bootcamp.bankcustomer.controller;

import com.bootcamp.bankcustomer.model.BankCustomer;
import com.bootcamp.bankcustomer.proxy.beans.bankAccount.BankAccountDto;
import com.bootcamp.bankcustomer.service.BankCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Slf4j
public class BankCustomerController {

    @Autowired
    private BankCustomerService bankCustomerService;


    /**
     * Un cliente personal solo puede tener un máximo de una cuenta de ahorro,una cuenta corriente o cuentas a plazo fijo.
     **/

    @GetMapping("/findAccountsByCustomer/{idCustomer}")
    public Flux<List<BankAccountDto>> getAccountsByCustomer(@PathVariable("idCustomer") String idCustomer) {
        log.info("All bank accounts of a client were consulted");
        return bankCustomerService.getAccountsByCustomer(idCustomer);
    }

    @GetMapping("/findAllCustomers")
    public Flux<BankCustomer> findAll() {
        log.info("All bank customers were consulted");

        return bankCustomerService.findAll()
                .doOnNext(bankCustomer -> bankCustomer.toString());
    }

    @GetMapping("/findById/{id}")
    public Mono<ResponseEntity<BankCustomer>> findById(@PathVariable("id") String id) {
        log.info("bank customer consulted by id " + id);
        Mono<BankCustomer> bankCustomer = bankCustomerService.findById(id);
        return bankCustomer.map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }

    @GetMapping("/findByDocumentId/{dni}")
    public Mono<ResponseEntity<BankCustomer>> findByDocumentId(@PathVariable("dni") String dni) {
        log.info("bank customer consulted by DNI " + dni);
        return bankCustomerService.findByDocumentId(dni)
                .map(bc -> new ResponseEntity<>(bc, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public Mono<ResponseEntity<BankCustomer>> save(@RequestBody BankCustomer bankCustomer) {
        log.info("A bank customer was created");
        bankCustomer.setCreationDatetime(LocalDateTime.now());
        return bankCustomerService.save(bankCustomer)
                .map(bc -> new ResponseEntity<>(bc, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @PutMapping("/updateCustomerById/{idCustomer}")
    public Mono<ResponseEntity<BankCustomer>> update(@RequestBody BankCustomer bankCustomer,
                                                     @PathVariable("customerId") String idCustomer) {
        log.info("A bank customer was changed");
        return bankCustomerService.updateCustomer(bankCustomer, idCustomer)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/deleteCustomerById/{idCustomer}")
    public Mono<ResponseEntity<Void>> deleteCustomerById(@PathVariable(name = "idCustomer") String idCustomer) {
        log.info("A bank customer was deleted");
        return bankCustomerService.deleteCustomerById(idCustomer)
                .map(bankCustomer -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());

    }
}
