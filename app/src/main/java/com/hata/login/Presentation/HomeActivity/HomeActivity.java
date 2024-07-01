package com.hata.login.Presentation.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.hata.login.Presentation.LoginActivity.LoginActivity;
import com.hata.login.Presentation.RegisterActivity.RegisterActivity;
import com.hata.login.Utils.FirebaseDatabaseClass;
import com.hata.login.Utils.FirebaseRepository;
import com.hata.login.databinding.ActivityHomeBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private ActivityHomeBinding binding;
    private ImageButton lockButton;
    private TextView messageGreeting;
    private HomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        messageGreeting = binding.homeTextview;

        presenter = new HomePresenter(this, FirebaseRepository.getInstance());

        binding.changeText.setOnClickListener(v -> presenter.changeTextButton());
        binding.lockScreen.setOnClickListener(v -> presenter.lockScreen());

    }

    public void navigateToLoginScreen(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Subscribe
    public void setGreeting(String username) {
        messageGreeting.setText("Hello, " + username);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void changeLayoutText(String text) {
        messageGreeting.setText(text);
    }
}
