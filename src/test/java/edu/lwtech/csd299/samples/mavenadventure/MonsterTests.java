package edu.lwtech.csd299.samples.mavenadventure;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

// Test Monster-specific methods.  See ActorTests.java for Actor-specific tests.
public class MonsterTests {
    
    private Monster testMonster;

    @Before
    public void setUp() {
        testMonster = new Monster("Fred", "Orc", 100, 50, 50, Locale.ANYWHERE);
    }

    @Test
    public void constructorNoNameTest() {
        testMonster = new Monster("Orc", 100, 50, 50);
        assertNotNull(testMonster);
    }

    @Test
    public void constructorNoLocaleTest() {
        testMonster = new Monster("Fred", "Orc", 100, 50, 50);
        assertNotNull(testMonster);        
    }

    @Test
    public void constructorNoTypeTest() {
        testMonster = new Monster("Fred", "Orc", 100, 50, 50, Locale.ANYWHERE);
        assertNotNull(testMonster);
    }

    @Test
    public void addLootTest() {
        Item ring = new Item("Ring");
        testMonster.addLoot(ring);
        List<Item> loot = testMonster.takeLoot();
        assertEquals(1, loot.size());
        for (Item i : loot) {
            if (i.getName().equals("Ring"))
                return;                             // Test passes
        }
        fail("Unable to add Ring to loot");
    }

    @Test
    public void takeLootTest() {
        List<Item> loot = testMonster.takeLoot();
        assertEquals(0, loot.size());
        Item ring = new Item("Ring");
        testMonster.addLoot(ring);
        loot = testMonster.takeLoot();
        assertEquals(1, loot.size());
        for (Item i : loot) {
            if (i.getName().equals("Ring"))
                return;                             // Test passes
        }
        fail("Unable to take loot with Ring");
    }

    @Test
    public void attackTest() {
        Monster foe = new Monster("Foe", "Orc", 100, 0, 0, Locale.ANYWHERE);    // Foe is defenseless
        int initialHitPoints = foe.getHitPoints();

        testMonster.attack(foe);
        testMonster.attack(foe);
        testMonster.attack(foe);
        testMonster.attack(foe);
        testMonster.attack(foe);

        assertTrue(foe.getHitPoints() < initialHitPoints);
    }

    @Test
    public void defendTest() {
        testMonster = new Monster("Fred", "Orc", 100, 0, 0, Locale.ANYWHERE);    // Fred is defenseless
        int initialHitPoints = testMonster.getHitPoints();

        testMonster.defend(20);
        testMonster.defend(20);
        testMonster.defend(20);
        testMonster.defend(20);
        testMonster.defend(20);

        assertTrue(testMonster.getHitPoints() < initialHitPoints);
    }

}