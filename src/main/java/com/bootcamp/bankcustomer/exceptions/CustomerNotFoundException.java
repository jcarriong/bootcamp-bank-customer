package com.bootcamp.bankcustomer.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CustomerNotFoundException extends RuntimeException {
    private final String idCustomer;
    private static final String msg = "Datos del cliente no encontrados";

    public CustomerNotFoundException(String idCustomer) {
        super(msg);
        this.idCustomer = idCustomer;
    }

}
