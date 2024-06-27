package com.hata.login.Presentation.LoginActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.hata.login.R;

public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = "GoogleActivity";
    private final LoginContract.View view;
    private final FirebaseAuth mAuth;
    private final GoogleSignInClient mGoogleSignInClient;

    public LoginPresenter(LoginContract.View view, Context context, FirebaseAuth mAuth) {
        this.view = view;
        this.mAuth = mAuth;

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    @Override
    public void handleGoogleSignInResult(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            if (account != null) {
                firebaseAuthWithGoogle(account.getIdToken());
            }
        } catch (ApiException e) {
            Log.w(TAG, "Google sign in failed", e);
            view.showGoogleSignInFailure("Google sign-in failed: " + e.getMessage());
        }
    }

    @Override
    public void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    view.showLoginSuccess(user.getEmail());
                    view.navigateToHome();
                } else {
                    view.showLoginFailure("Google authentication failed.");
                }
            }
        });
    }

    @Override
    public void authenticateUser(String username, String password) {
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();
                    if (user.isEmailVerified()) {
                        view.showLoginSuccess(user.getEmail());
                        view.navigateToHome();
                    } else {
                        view.showLoginFailure("Please, verify your e-mail.");
                    }
                } else {
                    view.showLoginFailure("Login failed.");
                }
            }
        });
    }

    @Override
    public void onRegisterLinkClick() {
        view.navigateToRegister();
    }

    @Override
    public void onGoogleButtonClick() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        view.launchGoogleSignIn(signInIntent);
    }
}
