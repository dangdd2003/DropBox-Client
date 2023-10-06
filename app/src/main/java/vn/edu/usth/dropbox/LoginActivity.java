package vn.edu.usth.dropbox;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import vn.edu.usth.dropbox.api.BuildConfig;
import vn.edu.usth.dropbox.api.DropboxOAuth2Utils;
import vn.edu.usth.dropbox.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityLoginBinding binding;

    DropboxOAuth2Utils dropboxOAuth2Utils;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        binding.continueBtn.setOnClickListener(this);
//        binding.loginWithGoogle.setOnClickListener(this);
//        binding.email.setOnClickListener(this);
//        binding.troubleBtn.setOnClickListener(this);


        binding.continueBtn.setOnClickListener(this);
        binding.loginWithGoogle.setOnClickListener(this);
        binding.troubleBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

//        binding.loginButtonPkce.setOnClickListener(v -> {
//            dropboxOAuth2Utils.startDropboxAuthorization2PKCE(this);
//        });
//
//        binding.loginButtonOauth.setOnClickListener(v -> {
//            dropboxOAuth2Utils.startDropboxAuthorizationOAuth2(this);
//        });

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



//    @Override
//    protected void onStart() {
//        super.onStart();
//        binding.loginButtonPkce.setOnClickListener(this);
//        binding.loginButtonOauth.setOnClickListener(this);
//    }
}
