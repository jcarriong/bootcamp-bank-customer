package com.bootcamp.bankcustomer.proxy.beans.bankAccount;

import com.bootcamp.bankcustomer.model.BankCustomer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {
    private String idAccount;
    private String idProduct;
    private String idCustomer;
    private String accountNumber; //numero de cuenta (14 digits)
    private String cci; //numero de cuenta interbancaria (20 digits)
    private Float availableBalance; //saldo disponible
    private List<String> holderAccount; //cuenta titular 1.*
    private List<String> authorizedSigner; //firmante autorizado 0.*
    private List<String> bankMovements; //lista de movimientos bancarios
    private String creationDatetime;
    private String updateDatetime;
}