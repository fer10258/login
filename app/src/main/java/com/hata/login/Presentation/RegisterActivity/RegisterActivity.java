package com.hata.login.Presentation.RegisterActivity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hata.login.Data.User;
import com.hata.login.Presentation.LoginActivity.LoginActivity;
import com.hata.login.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private @NonNull ActivityRegisterBinding binding;
    EditText etUsername;
    EditText etPassword;
    EditText etName;
    Button registerButton;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseDatabase = FirebaseDatabase.getInstance();

        // Inicializar FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "], mAuth: " + mAuth);
        myRef = firebaseDatabase.getReference();
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "], MyRef: " + myRef);

        // Declaração de variáveis
        etUsername = binding.email;
        etPassword = binding.password;
        registerButton = binding.register;
        etName = binding.name;

        registerUser();
    }

    private boolean isStringEmpty(String inputString) {
        return inputString == null || inputString.isEmpty();
    }

    private void registerUser() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.name.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                Log.d("RegisterActivity", "Username: " + username);
                Log.d("RegisterActivity", "Password: " + password);

                if (!isStringEmpty(username) && !isStringEmpty(password)) {
                    mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                if (user != null) {
                                    user.sendEmailVerification();
                                    createUserDatabase(name, username);
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(RegisterActivity.this, "User has been successfully created.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "Please, try again. Register has failed.", Toast.LENGTH_SHORT).show();
                                Log.e("RegisterActivity", "Failed to register user: ", task.getException());
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "You can't leave blank spaces!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createUserDatabase(String name, String email) {
        String userId = mAuth.getCurrentUser().getUid();
        Log.d(TAG, "createUserDatabase() called with: name = [" + name + "], email = [" + email + "], User ID: " + userId);

        // Criar um objeto User
        User newUser = new User(name, email);

        myRef.child("users").child(userId).setValue(newUser)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User data saved successfully.");
                        } else {
                            Log.d(TAG, "Failed to save user data.", task.getException());
                        }
                    }
                });
    }
}
