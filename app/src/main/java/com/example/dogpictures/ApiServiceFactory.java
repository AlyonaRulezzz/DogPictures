package com.example.dogpictures;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceFactory {

    private static final String BASE_URL = "https://dog.ceo/api/breeds/";

    private static ApiService instance;

    public static ApiService getInstance() {
        if (instance == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
            instance = retrofit.create(ApiService.class);
        }
        return instance;
    }
}
