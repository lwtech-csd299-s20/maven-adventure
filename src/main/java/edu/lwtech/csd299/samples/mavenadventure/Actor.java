package edu.lwtech.csd299.samples.mavenadventure;

public abstract class Actor {

    private String name;
    private String type;                            // Player or Monster
    private String species;
    private int hitPoints;
    private Locale locale;

    // ------------------------------------------

    public Actor(String name, String type, String species, int hitPoints, Locale locale) {
        this.name = name;
        this.type = type;
        this.species = species;
        this.hitPoints = hitPoints;
        this.locale = locale;
    }

    // ------------------------------------------

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getSpecies() {
        return species;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public Locale getLocale() {
        return locale;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    // ------------------------------------------

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void takeDamage(int damage) {
        hitPoints -= damage;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return "[" + name + " (" + getSpecies() + ") - " + hitPoints + "]";
    }

}