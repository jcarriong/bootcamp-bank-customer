package com.bootcamp.bankcustomer.proxy;

import com.bootcamp.bankcustomer.proxy.beans.bankAccount.BankAccountDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

/**
 * @author jcarriong
 */
public interface AccountRetrofitClient {
    @GET(value = "findAccountsByCustomer/{idCustomer}")
    Flux<List<BankAccountDto>> getAccountsByCustomer(@Path("idCustomer") String idCustomer);

    @POST(value = "saveAccount/")
    Mono<BankAccountDto> registerAccount(@Body BankAccountDto bankAccountDto);
}