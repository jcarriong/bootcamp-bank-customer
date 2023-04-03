package com.bootcamp.bankcustomer.proxy.config;

import com.bootcamp.bankcustomer.proxy.AccountRetrofitClient;
import com.jakewharton.retrofit2.adapter.reactor.ReactorCallAdapterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class ClientRetrofitConfig {
    @Bean
    AccountRetrofitClient accountRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:9008/v1/")
                .addCallAdapterFactory(ReactorCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(AccountRetrofitClient.class);
    }

    /* @Bean
    ProductRetrofitClient productRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8084/api/v1/product/")
                .addCallAdapterFactory(ReactorCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ProductRetrofitClient.class);
    }*/

  /*  @Bean
    OperationRetrofitClient operationRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8086/api/v1/operation/")
                .addCallAdapterFactory(ReactorCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(OperationRetrofitClient.class);
    }*/
}