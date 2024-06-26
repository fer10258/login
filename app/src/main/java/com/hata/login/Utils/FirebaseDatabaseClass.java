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
    static FirebaseDatabaseClass INSTANCE;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;

    public static FirebaseDatabaseClass getFirebaseDatabaseClassInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseDatabaseClass();
        } else {
            return INSTANCE;
        }
        return null;
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
        myRef.child("users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.child("name").getValue().toString();
                EventBus.getDefault().post(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}