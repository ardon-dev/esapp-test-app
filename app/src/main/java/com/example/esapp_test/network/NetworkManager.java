package com.example.esapp_test.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    public static String baseUrl = "https://dentist-app.000webhostapp.com/api/test/";

    public static Retrofit retrofit;
    public static ApiService service;

    public static void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiService.class);
    }



}
