package com.hata.login.Utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseRepository {
    private static FirebaseRepository INSTANCE;
    private FirebaseAuth mAuth;

    private FirebaseRepository() {
        mAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FirebaseRepository();
        }
        return INSTANCE;
    }

    public String getCurrentUserEmail() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        return currentUser != null ? currentUser.getEmail() : null;
    }

    public void getDatabaseUser() {
        FirebaseDatabaseClass.getFirebaseDatabaseClassInstance().getDatabaseReference();
        FirebaseDatabaseClass.getFirebaseDatabaseClassInstance().getDatabaseUserData();
    }
}
