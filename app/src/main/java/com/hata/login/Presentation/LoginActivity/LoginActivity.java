package com.hata.login.Presentation.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hata.login.Presentation.HomeActivity.HomeActivity;
import com.hata.login.Presentation.RegisterActivity.RegisterActivity;
import com.hata.login.R;
import com.hata.login.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    private ActivityLoginBinding binding;
    private LoginContract.Presenter presenter;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        presenter = new LoginPresenter(this, this, mAuth); // Passa o contexto da atividade para o presenter

        binding.buttonGoogle.setOnClickListener(v -> presenter.onGoogleButtonClick());
        binding.registerLink.setOnClickListener(v -> presenter.onRegisterLinkClick());
        binding.login.setOnClickListener(v -> presenter.authenticateUser(
                binding.email.getText().toString(),
                binding.password.getText().toString()
        ));
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(this, "Olá, " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();
        }
    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    presenter.handleGoogleSignInResult(result.getData());
                }
            });

    @Override
    public void showLoginSuccess(String user) {
        Toast.makeText(this, "Successful login, " + user, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginFailure(String message) {
        Toast.makeText(this, "Login failed: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGoogleSignInFailure(String message) {
        Toast.makeText(this, "Google sign-in failed: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void navigateToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    @Override
    public void launchGoogleSignIn(Intent signInIntent) {
        signInLauncher.launch(signInIntent);
    }
}
