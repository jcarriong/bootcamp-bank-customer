package com.bootcamp.bankcustomer.model;

import com.bootcamp.bankcustomer.model.dto.BaseAuditDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "bankCustomer")
public class BankCustomer extends BaseAuditDto {

    @Id
    private String idCustomer;
    private String customerType;
    private String customerCategory;
    private String dni;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private Address address;

}
