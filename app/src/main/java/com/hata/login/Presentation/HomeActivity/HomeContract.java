package com.hata.login.Presentation.HomeActivity;

public interface HomeContract {
    interface View {
        void changeLayoutText();
    }

    interface Presenter {
        public String newText(String text);
    }
}
