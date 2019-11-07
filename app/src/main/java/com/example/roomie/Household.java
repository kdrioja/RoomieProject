package com.example.roomie;

import java.util.ArrayList;
import java.util.List;

public class Household {
    private String name;
    private List<User> roommates;
    private List<String> chores;
    private String rotationDay; // day of the week

    public Household() {}

    public Household(String name, String rotationDay) {
        this.name = name;
        this.roommates = new ArrayList<>();
        this.chores = new ArrayList<>();
        this.rotationDay = rotationDay;
    }

    public void addChore(String newChore) {
        this.chores.add(newChore);
    }

    public void addRoommate(User newRoommate) {
        this.roommates.add(newRoommate);
    }

    /**
     * need to implement remove chore and remove roommate
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getRoommates() {
        return roommates;
    }

    public void setRoommates(List<User> roommates) {
        this.roommates = roommates;
    }

    public List<String> getChores() {
        return chores;
    }

    public void setChores(List<String> chores) {
        this.chores = chores;
    }

    public String getRotationDay() {
        return rotationDay;
    }

    public void setRotationDay(String rotationDay) {
        this.rotationDay = rotationDay;
    }
}
