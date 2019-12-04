package com.example.roomie;

import java.util.List;

public class Household {
    private String name;
    private List<String> roommates; // Contains uid of all roommates
    private List<Chore> chores; // Can be equal to or less than the size of roommates
    private int numRoommates;
    private int pointer;
    private String rotationDay; // set as Monday by default

    public Household() {}

    public Household(String name, List<String> roommates, List<Chore> chores, String rotationDay, int numRoommates, int pointer) {
        this.name = name;
        this.roommates = roommates;
        this.chores = chores;
        this.rotationDay = rotationDay;
        this.numRoommates = numRoommates;
        this.pointer = pointer;
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

    public List<Chore> getChores() {
        return chores;
    }

    public void setChores(List<Chore> chores) {
        this.chores = chores;
    }

    public String getRotationDay() {
        return rotationDay;
    }

    public void setRotationDay(String rotationDay) {
        this.rotationDay = rotationDay;
    }
}
