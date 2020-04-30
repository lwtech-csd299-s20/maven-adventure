package edu.lwtech.csd299.samples.mavenadventure;

import java.util.*;

public class Player extends Actor implements Fighter {

    private List<Item> backpack;
    private Weapon weapon;
    private Armor armor;

    public Player(String name, int hitPoints, Locale locale) {
        super(name, "Player", "Human", hitPoints, locale);
        backpack = new ArrayList<>();
        weapon = new Weapon("Hands", 1);
        armor = new Armor("Skin", 0);
    }

    // ------------------------------------------
 
    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void equipArmor(Armor armor) {
        this.armor = armor;
    }

    public void addToBackpack(Item item) {
        backpack.add(item);
    }

    public boolean searchBackpack(String itemName) {
        for (Item item : backpack) {
            if (item.name == itemName)
                return true;
        }
        return false;
    }

    // ------------------------------------------

    @Override
    public void attack(Fighter target) {
        int damage = (int)(Math.random() * weapon.getDamage());
        target.defend(damage);
    }

    @Override
    public void defend(int damage) {
        damage -= (int)(Math.random() * armor.getProtection());
        if (damage < 0) damage = 0;
        takeDamage(damage);
    }

    // ------------------------------------------

    @Override
    public String toString() {
        String s;
        String comma = "";

        s = super.toString();
        s += "  Weapon: " + weapon + "  Armor: " + armor + "\n";
        s += "  Backpack: [";
        for (Item item : backpack) {
            s += comma + item.toString();
            comma = ", ";
        }
        s += "]";
        return s;
    }

}