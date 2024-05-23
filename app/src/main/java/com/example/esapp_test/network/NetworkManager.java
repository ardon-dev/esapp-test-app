package com.example.esapp_test.network;

import retrofit2.Retrofit;

public class NetworkManager {

    public static String baseUrl = "https://dentist-app.000webhostapp.com/api/test/";

    public static Retrofit retrofit;
    public static ApiService service;

    public static void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();

        service = retrofit.create(ApiService.class);
    }



}
