package com.hata.login.Presentation.RegisterActivity;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hata.login.Data.User;
import com.hata.login.Utils.FirebaseRepository;

public class RegisterPresenter implements RegisterContract.Presenter {
    private static final String TAG = "RegisterPresenter";
    private RegisterContract.View view;
    private FirebaseAuth mAuth;



    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        FirebaseRepository.getInstance().getDatabaseUser();
    }

    @Override
    public void registerUser(String name, String username, String password) {
        if (isStringEmpty(username) || isStringEmpty(password)) {
            view.showToast("You can't leave blank spaces!");
            return;
        }

        mAuth.createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                user.sendEmailVerification();
                                createUserDatabase(name, username);
                                view.navigateToLogin();
                                view.showToast("User has been successfully created.");
                            }
                        } else {
                            view.showToast("Please, try again. Register has failed.");
                            Log.e(TAG, "Failed to register user: ", task.getException());
                        }
                    }
                });
    }



    private boolean isStringEmpty(String inputString) {
        return inputString == null || inputString.isEmpty();
    }

    private void createUserDatabase(String name, String email) {
        String userId = mAuth.getCurrentUser().getUid();
        Log.d(TAG, "createUserDatabase() called with: name = [" + name + "], email = [" + email + "], User ID: " + userId);

        User newUser = new User(name, email);
        FirebaseRepository.getInstance().getDatabaseUser();
    }
}
