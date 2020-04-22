package com.example.email1;

import java.util.Random;

public class ItemModel {
    String name;
    String subject;
    String time;
    boolean isFavorite;
    int color;

    public ItemModel(String name, String subject, String time, boolean isFavorite) {
        this.name = name;
        this.subject = subject;
        this.time = time;
        this.isFavorite = isFavorite;

        isFavorite=false;
        Random random=new Random();
        color=random.nextInt();
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getTime() {
        return time;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public int getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
