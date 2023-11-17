package com.example.firstandroidapp;

public class UserData {
    private String username;
    private String password;
    private String email;
    private String userid;
    private String profilePicPath;
    private String phoneNumber;
    private String dateAccountCreated;
    private String userRole;

    // Konstruktor, Getter und Setter für die Benutzerdaten

    // Beispielkonstruktor und Getter/Setters:
    public UserData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
