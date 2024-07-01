package com.hata.login;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.hata.login.Presentation.HomeActivity.HomeContract;
import com.hata.login.Presentation.HomeActivity.HomePresenter;
import com.hata.login.Utils.FirebaseRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ExampleUnitTest {
    HomeContract.Presenter presenter;

    @Mock
    HomeContract.View view;

    @Mock
    FirebaseRepository repository;

    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
        presenter = new HomePresenter(view, repository);
    }


}
