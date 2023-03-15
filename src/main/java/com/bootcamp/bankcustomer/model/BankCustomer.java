package com.bootcamp.bankcustomer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bankCustomer")
public class BankCustomer {

    @Id
    private String id = UUID.randomUUID().toString();
    private String dni;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Address address;

}
