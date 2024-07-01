package com.hata.login;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.hata.login.Presentation.HomeActivity.HomeActivity;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Before
    public void setUp() {
        // Inicie a atividade manualmente usando ActivityScenario
        ActivityScenario.launch(HomeActivity.class);
    }

    @Test
    public void testLockScreenButton() {
        // Clique no botão lockScreen
        Espresso.onView(withId(R.id.lockScreen)).perform(click());

        // Verifique se a view 'main' está visível após o clique
        Espresso.onView(withId(R.id.login)).check(matches(isDisplayed()));
    }
}
