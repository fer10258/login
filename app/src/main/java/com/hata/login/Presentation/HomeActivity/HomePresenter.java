package com.hata.login.Presentation.HomeActivity;

import com.hata.login.Utils.FirebaseRepository;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View view;
    private FirebaseRepository repository;

    public HomePresenter(HomeContract.View view, FirebaseRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void changeTextButton() {
        view.changeLayoutText("Pitanga");
    }

    @Override
    public void lockScreen() {
        view.navigateToLoginScreen();
    }


}
