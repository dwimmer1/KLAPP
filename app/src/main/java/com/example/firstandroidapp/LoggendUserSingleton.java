package com.example.firstandroidapp;


//Singelton in which User is currently logged in
public class LoggendUserSingleton {

    private static LoggendUserSingleton instance;
    private String loggedinUser;

    private LoggendUserSingleton() {
        loggedinUser = "";
    }

    public static synchronized LoggendUserSingleton getInstance() {
        if (instance == null) {
            instance = new LoggendUserSingleton();
        }
        return instance;
    }

    public String getUserNames() {
        return loggedinUser;
    }

    public void setUserNames(String loggedinUser) {
        this.loggedinUser = loggedinUser;
    }
}
