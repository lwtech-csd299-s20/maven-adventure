package edu.lwtech.csd299.samples.mavenadventure;

public class LocationMenuItem implements Runnable {

    private String description;
    private Runnable action;

    public LocationMenuItem(String description, Runnable action) {
        this.description = description;
        this.action = action;
    }

    // ------------------------------------------

    public String getDescription() {
        return description;
    }

    // ------------------------------------------

    @Override
    public void run() {
        action.run();
    }

    // ------------------------------------------

    @Override
    public String toString() {
        return description;
    }

}