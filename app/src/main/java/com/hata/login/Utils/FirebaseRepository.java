package com.hata.login.Utils;

public class FirebaseRepository {
    static FirebaseRepository INSTANCE;
    public static FirebaseRepository getInstance(){
        if (INSTANCE == null){
            INSTANCE = new FirebaseRepository();

        }return INSTANCE;
    }
    public void getDatabaseUser() {
        FirebaseDatabaseClass.getFirebaseDatabaseClassInstance().getDatabaseReference();
        FirebaseDatabaseClass.getFirebaseDatabaseClassInstance().getDatabaseUserData();
    }
}
