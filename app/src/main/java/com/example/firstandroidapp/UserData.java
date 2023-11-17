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

    public UserData(String username, String password, String email, String userid, String phoneNumber, String dateAccountCreated, String userRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userid = userid;
        this.phoneNumber = phoneNumber;
        this.dateAccountCreated = dateAccountCreated;
        this.userRole = userRole;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateAccountCreated() {
        return dateAccountCreated;
    }

    public void setDateAccountCreated(String dateAccountCreated) {
        this.dateAccountCreated = dateAccountCreated;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
