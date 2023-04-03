package com.bootcamp.bankcustomer.model.response;

import com.bootcamp.bankcustomer.model.BankCustomer;
import com.bootcamp.bankcustomer.proxy.beans.bankAccount.BankAccountDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAccountResponse {
    BankCustomer customer;
    List<BankAccountDto> bankAccountDtoList;


}
