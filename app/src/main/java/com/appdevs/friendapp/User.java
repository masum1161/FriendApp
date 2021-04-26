package com.appdevs.friendapp;

public class User {
    private String name;
    private String email;
    private String uid;
    private String photo;
    private boolean active;

    public User() {
    }

    public User(String name, String email, String uid, String photo, boolean active) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.photo = photo;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
