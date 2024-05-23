package com.example.esapp_test.ui.main;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.esapp_test.R;
import com.example.esapp_test.databinding.ActivityMainBinding;
import com.example.esapp_test.network.NetworkManager;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding = null;
    private NavHostFragment hostFragment;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        hostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_container);
        navController = hostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.mainNavView, navController);


    }

    /** Network **/

    private void initializeRetrofit() {
        NetworkManager.initRetrofit();

    }

}