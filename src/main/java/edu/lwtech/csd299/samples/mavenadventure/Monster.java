package edu.lwtech.csd299.samples.mavenadventure;

import java.util.*;

public class Monster extends Actor implements Fighter {

    private int maxDamage;
    private int maxProtection;
    private List<Item> loot;

    public Monster(String name, String species, int hitPoints, int maxDamage, int maxProtection, Locale locale) {
        super(name, "Monster", species, hitPoints, locale);
        this.maxDamage = maxDamage;
        this.maxProtection = maxProtection;
        this.loot = new ArrayList<>();
    }

    // ------------------------------------------

    public void addLoot(Item item) {
        loot.add(item);
    }

    public List<Item> takeLoot() {
        List<Item> taken = loot;
        loot = new ArrayList<Item>();       // Remove the loot from the monster
        return taken;
    }

    // ------------------------------------------

    @Override
    public void attack(Fighter target) {
        int damage = (int)(Math.random() * maxDamage);
        target.defend(damage);
    }

    @Override
    public void defend(int damage) {
        damage -= (int)(Math.random() * maxProtection);
        if (damage < 0) damage = 0;
        takeDamage(damage);
    }

}