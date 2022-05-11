package com.example.persistence;

public class AppData {
    private long id;
    private String text;

    public AppData(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
