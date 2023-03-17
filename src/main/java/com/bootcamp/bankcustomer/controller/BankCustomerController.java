package com.bootcamp.bankcustomer.controller;

import com.bootcamp.bankcustomer.exceptions.GeneralException;
import com.bootcamp.bankcustomer.model.BankCustomer;
import com.bootcamp.bankcustomer.model.dto.GlobalResponse;
import com.bootcamp.bankcustomer.service.BankCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@Slf4j
public class BankCustomerController {

    @Autowired
    private BankCustomerService bankCustomerService;

    @GetMapping("/findAll")
    public List<BankCustomer> findAll() {
        log.info("All bank customers were consulted");

        return bankCustomerService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<BankCustomer> findById(@PathVariable("id") String id) {
        log.info("bank customer consulted by id " + id);
        return bankCustomerService.findById(id);

    }

    @GetMapping("/findByDocumentId/{dni}")
    public ResponseEntity<GlobalResponse> findByDocumentId(@PathVariable("dni") String dni) {
        log.info("bank customer consulted by DNI " + dni);
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(GlobalResponse.builder()
                            .data(bankCustomerService.findByDocumentId(dni)
                                    .get()).message("Consulta con exito")
                            .build());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(GlobalResponse.builder()
                            .data(GeneralException.builder()
                                    .message(e.getMessage())
                                    .build())
                            .build());
        }
    }

    @PostMapping("/save")
    public ResponseEntity<GlobalResponse> save(@RequestBody BankCustomer bankCustomer) {
        log.info("A bank customer was created");
        try {
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
        }
    }

    @PutMapping("/updateByDni/{dni}")
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
    }
}
