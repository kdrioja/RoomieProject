package com.example.roomie;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String chore;
    private boolean choreComplete;
    private String id;
    private String household;

    public User() {}

    public User(String firstName, String lastName, String email, String chore, boolean choreComplete, String id, String household) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.chore = chore;
        this.choreComplete = choreComplete;
        this.id = id;
        this.household = household;
    }

    public String getHousehold() {
        return household;
    }

    public void setHousehold(String household) {
        this.household = household;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChore() {
        return chore;
    }

    public void setChore(String chore) {
        this.chore = chore;
    }

    public boolean isChoreComplete() {
        return choreComplete;
    }

    public void setChoreComplete(boolean choreComplete) {
        this.choreComplete = choreComplete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
