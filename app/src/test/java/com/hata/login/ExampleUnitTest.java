package com.hata.login;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import com.hata.login.Presentation.HomeActivity.HomeActivity;
import com.hata.login.Presentation.HomeActivity.HomePresenter;
import com.hata.login.Utils.FirebaseRepository;

import org.junit.Before;
import org.junit.Test;

import org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
public class ExampleUnitTest {
    HomePresenter presenter;

    @Mock
    HomeActivity view;

    @Mock
    FirebaseRepository repository;

    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkStringAdd(){
        presenter = new HomePresenter(view, repository);
        String newString = presenter.newText("Pitanga");
        assertEquals("Pitanga testing Junit", newString);
    }

    @Test
    public void checkgetUser(){
        presenter = new HomePresenter(view, repository);
        presenter.getUser();
        verify(repository).getDatabaseUser();
    }
}