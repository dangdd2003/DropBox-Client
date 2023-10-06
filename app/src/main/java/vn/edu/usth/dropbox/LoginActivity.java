package vn.edu.usth.dropbox;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dropbox.core.DbxRequestConfig;

import vn.edu.usth.dropbox.api.DropboxOAuth2Utils;
import vn.edu.usth.dropbox.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

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

    }

    @Override
    public void onClick(View view) {
//        binding.continueBtn.setOnClickListener(v -> {
//            if (binding.email.getText().toString().equals("alo1234")) {
//                Intent intent = new Intent(Login.this, MainActivity.class);
//                startActivity(intent);
//            } else {
//                Toast.makeText(this, "???????????", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        binding.loginWithGoogle.setOnClickListener(v -> {
//            Intent intent = new Intent(Login.this, MainActivity.class);
//            startActivity(intent);
//        });
//
//        binding.troubleBtn.setOnClickListener(v -> {
//            Toast.makeText(this, "Who care ? ", Toast.LENGTH_SHORT).show();
//        });

        binding.loginButtonPkce.setOnClickListener(v -> {
            dropboxOAuth2Utils.startDropboxAuthorization2PKCE(this);
        });

        binding.loginButtonOauth.setOnClickListener(v -> {
            dropboxOAuth2Utils.startDropboxAuthorizationOAuth2(this);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.loginButtonPkce.setOnClickListener(this);
        binding.loginButtonOauth.setOnClickListener(this);
    }
}
