package com.hata.login.Presentation.HomeActivity;

import com.hata.login.Utils.FirebaseDatabaseClass;

public class HomePresenter implements HomeContract.Presenter {
    HomeContract.View view;
    public HomePresenter(HomeContract.View view) {
        this.view = view;


        getDatabaseUser();
        view.changeLayoutText();
    }

    public void getDatabaseUser() {
        FirebaseDatabaseClass.getFirebaseDatabaseClassInstance().getDatabaseReference();
        FirebaseDatabaseClass.getFirebaseDatabaseClassInstance().getDatabaseUserData();
    }
}
