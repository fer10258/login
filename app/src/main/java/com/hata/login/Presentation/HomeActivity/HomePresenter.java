package com.hata.login.Presentation.HomeActivity;

import com.hata.login.Utils.FirebaseRepository;

public class HomePresenter implements HomeContract.Presenter {
    HomeContract.View view;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        FirebaseRepository.getInstance().getDatabaseUser();

        view.changeLayoutText();
    }


}
