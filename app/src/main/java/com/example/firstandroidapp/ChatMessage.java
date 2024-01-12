package com.example.firstandroidapp;

public class ChatMessage {

    private String name;
    private String message;
    private String timestamp;

    public ChatMessage(String name, String message, String timestamp) {
        this.name = name;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}


