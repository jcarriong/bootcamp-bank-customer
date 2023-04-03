package com.bootcamp.bankcustomer.proxy;

import com.bootcamp.bankcustomer.proxy.beans.bankAccount.BankAccountDto;
import reactor.core.publisher.Flux;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

/**
 * @author jcarriong
 */
public interface AccountRetrofitClient {
    @GET(value = "findAccountsByCustomer/{idCustomer}")
    Flux<List<BankAccountDto>> getAccountsByCustomer(@Path("idCustomer") String idCustomer);
}