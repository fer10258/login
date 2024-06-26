package com.hata.login.Presentation.HomeActivity;

import com.hata.login.Utils.FirebaseDatabaseClass;

public class HomePresenter implements HomeContract.Presenter {
    public HomePresenter() {
        getDatabaseUser();
    }

    public void getDatabaseUser() {
        FirebaseDatabaseClass.getFirebaseDatabaseClassInstance().getDatabaseReference();
        FirebaseDatabaseClass.getFirebaseDatabaseClassInstance().getDatabaseUserData();
    }
}
