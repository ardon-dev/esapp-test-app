package com.example.esapp_test.network;

import com.example.esapp_test.model.LoginRequest;
import com.example.esapp_test.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);

}
