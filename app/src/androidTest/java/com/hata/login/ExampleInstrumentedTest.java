package com.hata.login;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.mockito.Mockito.when;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hata.login.Presentation.HomeActivity.HomeActivity;
import com.hata.login.Presentation.LoginActivity.LoginActivity;
import com.hata.login.Presentation.RegisterActivity.RegisterActivity;
import com.hata.login.Utils.FirebaseRepository;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Mock
    FirebaseAuth mAuth;

    @Mock
    FirebaseUser mFirebaseUser;

    @Mock
    FirebaseRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // Configurar o comportamento esperado para o FirebaseAuth e FirebaseUser
        when(mAuth.getCurrentUser()).thenReturn(mFirebaseUser);
        when(mFirebaseUser.getUid()).thenReturn("mocked_uid");

        // Iniciar a atividade manualmente usando ActivityScenario
        ActivityScenario.launch(RegisterActivity.class);
    }

    @Test
    public void fillOutForm() {
        // Simular a entrada de dados
        String email = "teste123@gmail.com";
        String name = "rick";
        String password = "123teste";

        // Executar ações no Espresso
        onView(withId(R.id.emailRegister)).perform(typeText(email), closeSoftKeyboard());
        onView(withId(R.id.nameRegister)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.passwordRegister)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());

        // Verificar se a atividade de login foi iniciada
        intended(hasComponent(LoginActivity.class.getName()));
    }
}
