package com.example.esapp_test.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.esapp_test.model.LoginRequest;
import com.example.esapp_test.model.LoginResponse;
import com.example.esapp_test.network.NetworkManager;
import com.example.esapp_test.util.Status;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    //Login

    MutableLiveData<Status> _status = new MutableLiveData<>(Status.NOT_LOADING);

    private MutableLiveData<LoginResponse> _loginResponse = new MutableLiveData<>(null);
    LiveData<LoginResponse> loginResponse = _loginResponse;

    private MutableLiveData<String> _loginError = new MutableLiveData<>(null);
    LiveData<String> loginError = _loginError;

    void login(String email, String password) {
        _status.setValue(Status.LOADING);
        NetworkManager.service.login(new LoginRequest(email, password)).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    _loginResponse.setValue(response.body());
                    _status.setValue(Status.NOT_LOADING);
                } else {
                    _loginError.setValue(response.message());
                    _status.setValue(Status.NOT_LOADING);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                _loginError.setValue(throwable.getMessage());
                _status.setValue(Status.NOT_LOADING);
            }
        });
    }


}
