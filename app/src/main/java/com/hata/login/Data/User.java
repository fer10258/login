package com.hata.login.Data;

public class User {
    public String name;
    public String email;

    public User() {
        // Construtor vazio necessário para o Firebase
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
