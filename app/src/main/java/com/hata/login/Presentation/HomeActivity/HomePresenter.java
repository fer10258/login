package com.hata.login.Presentation.HomeActivity;

import com.hata.login.Utils.FirebaseRepository;

public class HomePresenter implements HomeContract.Presenter {
    HomeContract.View view;
    FirebaseRepository repository;

    public HomePresenter(HomeContract.View view, FirebaseRepository repository) {
        this.view = view;
        this.repository = repository;


        view.changeLayoutText();
    }
    public String newText(String text){
        return text + " testing Junit";
    }
    public void getUser(){
        repository.getDatabaseUser();
    }

}
