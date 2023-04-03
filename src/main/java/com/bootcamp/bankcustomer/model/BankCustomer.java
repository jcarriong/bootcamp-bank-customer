package com.bootcamp.bankcustomer.model;

import com.bootcamp.bankcustomer.model.dto.BaseAuditDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

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
