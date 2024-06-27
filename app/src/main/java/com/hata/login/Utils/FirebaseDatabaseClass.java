package com.hata.login.Utils;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

public class FirebaseDatabaseClass {
    private static FirebaseDatabaseClass INSTANCE;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference myRef;

    public static FirebaseDatabaseClass getFirebaseDatabaseClassInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseDatabaseClass();
        }
        return INSTANCE;
    }

    public FirebaseDatabase getDatabaseInstance() {
        if (firebaseDatabase == null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
        }
        return firebaseDatabase;
    }

    public DatabaseReference getDatabaseReference() {
        if (myRef == null) {
            myRef = getDatabaseInstance().getReference();
        }
        return myRef;
    }

    public void getDatabaseUserData() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        getDatabaseReference().child("users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("name").exists()) {
                    String username = snapshot.child("name").getValue(String.class);
                    EventBus.getDefault().post(username);
                } else {
                    // Handle case where "name" doesn't exist
                    EventBus.getDefault().post("Name not found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }
}
