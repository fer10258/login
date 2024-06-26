package com.hata.login.Presentation.HomeActivity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hata.login.Presentation.LoginActivity.LoginActivity;
import com.hata.login.Utils.FirebaseDatabaseClass;
import com.hata.login.databinding.ActivityHomeBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class HomeActivity extends AppCompatActivity implements HomeContract.View{

    private ActivityHomeBinding binding;
    ImageButton lockButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    TextView messageGreeting;
    HomePresenter presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        lockButton = binding.lockScreen;
        mAuth = FirebaseAuth.getInstance();
        //Database
        FirebaseDatabaseClass.getFirebaseDatabaseClassInstance();

        presenter = new HomePresenter(this);
//        Intent intent = getIntent();
//        String user = intent.getStringExtra("user");
//        TextView tx = binding.homeTextview;
//        tx.setText(user);


        //Declaração de funções:
        lockScreen();
    }

    private void lockScreen() {
        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

@Subscribe
public void setGreeting(String username){
    messageGreeting = binding.homeTextview;
    messageGreeting.setText("Hello, " + username);
}

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void changeLayoutText() {
        String text = messageGreeting.getText().toString();
        messageGreeting.setText(text + "Pelo amor de Deus funciona");
    }
//    private void deleteUser() {
//        String userId = mAuth.getCurrentUser().getUid();
//
//
//        myRef.child("users").
//                child(userId).
//                removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(HomeActivity.this, "Successful delete!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
}
