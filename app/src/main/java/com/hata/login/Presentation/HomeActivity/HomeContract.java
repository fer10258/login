package com.hata.login.Presentation.HomeActivity;

public interface HomeContract {
    interface View {
        void changeLayoutText(String text);
        void navigateToLoginScreen();
    }

    interface Presenter {
        void changeTextButton();
        void lockScreen();
    }
}
