package com.hata.login.Presentation.RegisterActivity;

public interface RegisterContract {
    interface View {
        void showToast(String message);
        void navigateToLogin();
    }

    interface Presenter {
        void registerUser(String name, String username, String password);
    }
}
