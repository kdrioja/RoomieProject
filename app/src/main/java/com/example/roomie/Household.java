package com.example.roomie;

import java.util.ArrayList;
import java.util.List;

public class Household {
    private String name;
    private List<String> roommates; // Contains uid of all roommates
    private List<String> chores; // Can be equal to or less than the size of roommates
    private String rotationDay; // set as Monday by default

    public Household() {}

    public Household(String name, List<String> roommates, List<String> chores, String rotationDay) {
        this.name = name;
        this.roommates = roommates;
        this.chores = chores;
        this.rotationDay = rotationDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoommates() {
        return roommates;
    }

    public void setRoommates(List<String> roommates) {
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
