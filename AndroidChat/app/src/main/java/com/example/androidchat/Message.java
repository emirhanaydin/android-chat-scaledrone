package com.example.androidchat;

public class Message {
    private String text;
    private MemberData data;
    private boolean isSentByCurrentUser;

    public Message(String text, MemberData data, boolean isSentByCurrentUser) {
        this.text = text;
        this.data = data;
        this.isSentByCurrentUser = isSentByCurrentUser;
    }

    public String getText() {
        return text;
    }

    public MemberData getData() {
        return data;
    }

    public boolean isSentByCurrentUser() {
        return isSentByCurrentUser;
    }
}
