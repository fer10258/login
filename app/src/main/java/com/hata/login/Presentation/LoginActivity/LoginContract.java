package com.hata.login.Presentation.LoginActivity;

import android.content.Intent;

public interface LoginContract {
    interface View {
        void showLoginSuccess(String user);
        void showLoginFailure(String message);
        void showGoogleSignInFailure(String message);
        void navigateToHome();
        void navigateToRegister();
        void launchGoogleSignIn(Intent signInIntent);
    }

    interface Presenter {
        void handleGoogleSignInResult(Intent data);
        void firebaseAuthWithGoogle(String idToken);
        void authenticateUser(String username, String password);
        void onRegisterLinkClick();
        void onGoogleButtonClick();
    }
}
