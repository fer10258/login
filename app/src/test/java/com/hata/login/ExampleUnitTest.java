package com.hata.login;

import static org.junit.Assert.assertEquals;

import com.hata.login.Presentation.HomeActivity.HomePresenter;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    HomePresenter presenter;

    @Test
    public void checkStringAdd() {
        presenter = new HomePresenter()
        String newString = presenter.newText("Pitanga");
        assertEquals("Pitanga ", newString);
    }

}