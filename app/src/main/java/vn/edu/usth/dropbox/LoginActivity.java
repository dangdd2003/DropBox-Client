package vn.edu.usth.dropbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import vn.edu.usth.dropbox.api.BuildConfig;
import vn.edu.usth.dropbox.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.continueBtn.setOnClickListener(this);
        binding.loginWithGoogle.setOnClickListener(this);
        binding.troubleBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        binding.continueBtn.setOnClickListener(v -> {
            BuildConfig.ACCESS_TOKEN = binding.email.getText().toString();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });

        binding.loginWithGoogle.setOnClickListener(v -> {
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);

            Toast.makeText(this, "This button is for fun :))", Toast.LENGTH_SHORT).show();
        });
        
        binding.troubleBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Dun know :(", Toast.LENGTH_SHORT).show();
        });
    }
}
