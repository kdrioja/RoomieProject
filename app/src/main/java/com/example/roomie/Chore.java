package com.example.roomie;

public class Chore {
    private String title;
    private String assigned_to;
    private boolean completed;

    public Chore() {}

    public Chore(String title, String userID, boolean completed) {
        this.title = title;
        this.assigned_to = userID;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(String assigned_to) {
        this.assigned_to = assigned_to;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}