package edu.lwtech.csd299.samples.mavenadventure;

import org.junit.*;
import static org.junit.Assert.*;

// Test Player-specific methods.  See ActorTests.java for Actor-specific tests.
public class PlayerTests {
    
    private Player testPlayer;

    @Before
    public void setUp() {
        testPlayer = new Player("Fred", 100, Locale.ANYWHERE);
    }

    @Test
    public void constructor() {
        testPlayer = new Player("Fred", 100, Locale.ANYWHERE);
        assertNotNull(testPlayer);
    }

    @Test
    public void equipWeaponTest() {
        assertFalse(testPlayer.toString().contains("Sword"));
        Weapon sword = new Weapon("Sword", 10);
        testPlayer.equipWeapon(sword);
        assertTrue(testPlayer.toString().contains("Sword"));
    }

    @Test
    public void equipArmorTest() {
        assertFalse(testPlayer.toString().contains("Plate"));
        Armor plate = new Armor("Plate", 50);
        testPlayer.equipArmor(plate);
        assertTrue(testPlayer.toString().contains("Plate"));
    }

    @Test
    public void addToBackpackTest() {
        assertFalse(testPlayer.toString().contains("Ring"));
        Item ring = new Item("Ring");
        testPlayer.addToBackpack(ring);
        assertTrue(testPlayer.toString().contains("Ring"));
    }

    @Test
    public void removeFromBackpackTest() {
        assertFalse(testPlayer.toString().contains("Ring"));
        Item ring = new Item("Ring");
        testPlayer.addToBackpack(ring);
        assertTrue(testPlayer.toString().contains("Ring"));
        ring = testPlayer.removeFromBackpack("Ring");
        assertNotNull(ring);
        assertFalse(testPlayer.toString().contains("Ring"));
        // Try to remove a missing item
        ring = testPlayer.removeFromBackpack("Ring");
        assertNull(ring);
    }

    @Test
    public void searchBackpackTest() {
        assertFalse(testPlayer.searchBackpack("Ring"));
        testPlayer.addToBackpack(new Item("Ring"));
        assertTrue(testPlayer.searchBackpack("Ring"));
    }

    @Test
    public void attackTest() {
        testPlayer.equipWeapon(new Weapon("Sword", 10));
        Monster foe = new Monster("Foe", "Orc", 100, 0, 0, Locale.ANYWHERE);        // Foe is defenseless
        int initialHitPoints = foe.getHitPoints();

        testPlayer.attack(foe);
        testPlayer.attack(foe);
        testPlayer.attack(foe);
        testPlayer.attack(foe);
        testPlayer.attack(foe);

        assertTrue(foe.getHitPoints() < initialHitPoints);
    }

    @Test
    public void defendTest() {
        int initialHitPoints = testPlayer.getHitPoints();

        testPlayer.defend(20);
        testPlayer.defend(20);
        testPlayer.defend(20);
        testPlayer.defend(20);
        testPlayer.defend(20);

        assertTrue(testPlayer.getHitPoints() < initialHitPoints);
    }

}