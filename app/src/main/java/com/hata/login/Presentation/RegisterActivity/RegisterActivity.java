package com.hata.login.Presentation.RegisterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hata.login.Presentation.LoginActivity.LoginActivity;
import com.hata.login.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View {
    private @NonNull ActivityRegisterBinding binding;
    private RegisterContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new RegisterPresenter(this);

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.registerUser(
                        binding.name.getText().toString(),
                        binding.email.getText().toString(),
                        binding.password.getText().toString()
                );
            }
        });
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
