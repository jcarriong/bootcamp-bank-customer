package com.bootcamp.bankcustomer.controller;

import com.bootcamp.bankcustomer.model.BankCustomer;
import com.bootcamp.bankcustomer.service.BankCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1")
@Slf4j
public class BankCustomerController {

    @Autowired
    private BankCustomerService bankCustomerService;

    @GetMapping("/findAll")
    public Flux<BankCustomer> findAll() {
        log.info("All bank customers were consulted");

        return bankCustomerService.findAll()
                .doOnNext(bankCustomer -> bankCustomer.toString());
    }

    @GetMapping("/findById/{id}")
    public Mono<BankCustomer> findById(@PathVariable("id") String id) {
        log.info("bank customer consulted by id " + id);
        return bankCustomerService.findById(id);

    }

    @GetMapping("/findByDocumentId/{dni}")
    public Mono<ResponseEntity<BankCustomer>> findByDocumentId(@PathVariable("dni") String dni) {
        log.info("bank customer consulted by DNI " + dni);
        return bankCustomerService.findByDocumentId(dni)
                .map(bc -> new ResponseEntity<>(bc, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
      /*  try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(GlobalResponse.builder()
                            .data(bankCustomerService.findByDocumentId(dni)
                                    .getClass()).message("Consulta con exito")
                            .build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GlobalResponse.builder()
                            .data(GeneralException.builder()
                                    .message(e.getMessage())
                                    .build())
                            .build());
        }*/
    }

    @PostMapping("/save")
    public Mono<ResponseEntity<BankCustomer>> save(@RequestBody BankCustomer bankCustomer) {
        log.info("A bank customer was created");
        bankCustomer.setCreationDatetime(LocalDateTime.now());
        return bankCustomerService.save(bankCustomer)
                .map(bc -> new ResponseEntity<>(bc, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.CONFLICT));
        /*try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(GlobalResponse.builder()
                            .data(bankCustomerService.save(bankCustomer)
                                    .get()).message("Registrado con exito")
                            .build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GlobalResponse.builder()
                            .data(GeneralException.builder()
                                    .message(e.getMessage())
                                    .build())
                            .build());
        }*/
    }

    /*@PutMapping("/updateByDni/{dni}")
    public ResponseEntity<Void> update(@RequestBody BankCustomer bankCustomer,
                                       @PathVariable("dni") String dni) {
        log.info("A bank customer was changed");
        bankCustomerService.updateByDni(bankCustomer, dni);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") String id) {
        log.info("A bank customer was deleted");
        bankCustomerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }*/
}
