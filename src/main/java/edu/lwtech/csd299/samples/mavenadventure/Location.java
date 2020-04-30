package edu.lwtech.csd299.samples.mavenadventure;

public class Location {

    private Locale locale;
    private String description;
    private LocationMenu menu;
    private Runnable preAction;

    public Location(Locale locale, String description, LocationMenu menu) {
        this.locale = locale;
        this.description = description;
        this.menu = menu;
        this.preAction = ()->{};            // "Do nothing" lambda
    }

    // ------------------------------------------

    public Locale getLocale() {
        return locale;
    }

    public String getDescription() {
        return description;
    }

    public LocationMenu getMenu() {
        return menu;
    }

    public Runnable getPreAction() {
        return preAction;
    }

    // ------------------------------------------

    public void setPreAction(Runnable preAction) {
        this.preAction = preAction;
    }

    // ------------------------------------------

    @Override
    public String toString() {
        return description;
    }


}