package edu.lwtech.csd299.samples.mavenadventure;

public class Armor extends Item {

    private int protection;

    public Armor(String name, int protection) {
        super(name);
        this.protection = protection;
    }

    // ------------------------------------------

    public int getProtection() {
        return protection;
    }

    // ------------------------------------------

    @Override
    public String toString() {
        return "[" + name + "(+" + protection + ")]"; 
    }

}