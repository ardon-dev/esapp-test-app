package com.example.esapp_test.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.esapp_test.R;
import com.example.esapp_test.databinding.ActivityLoginBinding;
import com.example.esapp_test.model.LoginResponse;
import com.example.esapp_test.network.NetworkManager;
import com.example.esapp_test.ui.main.MainActivity;
import com.example.esapp_test.util.Status;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        NetworkManager.initRetrofit();
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initFlow();
    }

    private void initFlow() {
        //Observers
        observeLogin();
    }

    public void login(View view) {

        String email = binding.loginEmailInputTxt.getText().toString().trim();
        String password = binding.loginPasswordInputTxt.getText().toString().trim();

        if (email.isEmpty()) {
            binding.loginEmailInputTxt.setError("Empty email.");
            return;
        }

        if (password.isEmpty()) {
            binding.loginPasswordInputTxt.setError("Empty password.");
            return;
        }

        viewModel.login(email, password);

    }

    private void observeLogin() {

        viewModel._status.observe(this, status -> {
            if (status == Status.LOADING) {
                binding.loginProgressBar.setVisibility(View.VISIBLE);
            } else {
                binding.loginProgressBar.setVisibility(View.GONE);
            }
        });

        viewModel.loginResponse.observe(this, loginResponse -> {
            if (loginResponse != null) {
                handleLogin(loginResponse);
            }
        });

        viewModel.loginError.observe(this, loginError -> {
            if (loginError != null) {
                Log.d("Error", loginError);
                Toast.makeText(this, loginError, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void handleLogin(LoginResponse loginResponse) {
        if (loginResponse.isResult()) {
            goToMainActivity();
        } else {
            Toast.makeText(this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

}