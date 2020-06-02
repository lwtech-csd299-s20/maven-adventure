package edu.lwtech.csd299.samples.mavenadventure;

import org.junit.*;
import static org.junit.Assert.*;

// Test the abstract class Actor using the concrete class Monster which extends Actor
public class ActorTests {
    
    private Monster testMonster;

    @Before
    public void setUp() {
        testMonster = new Monster("Fred", "Orc", 100, 50, 50, Locale.ANYWHERE);
    }

    @Test
    public void constructorTest() {
        testMonster = new Monster("Fred", "Orc", 100, 50, 50, Locale.ANYWHERE);
        assertNotNull(testMonster);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorNameNullTest() {
        testMonster = new Monster(null, "Orc", 0, 0, 0, Locale.WOODS);
        fail("Exception not thrown.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorNameEmptyTest() {
        testMonster = new Monster("", "Orc", 0, 0, 0, Locale.WOODS);
        fail("Exception not thrown.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorSpeciesNullTest() {
        testMonster = new Monster("Fred", null, 0, 0, 0, Locale.WOODS);
        fail("Exception not thrown.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorSpeciesEmptyTest() {
        testMonster = new Monster("Fred", "", 0, 0, 0, Locale.WOODS);
        fail("Exception not thrown.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorHitPointsNegativeTest() {
        testMonster = new Monster("Fred", "Orc", -15, 0, 0, Locale.WOODS);
        fail("Exception not thrown.");
    }

    @Test
    public void getNameTest() {
        assertEquals("Fred", testMonster.getName());
    }

    @Test
    public void getTypeTest() {
        assertEquals("Monster", testMonster.getType());
    }

    @Test
    public void getSpeciesTest() {
        assertEquals("Orc", testMonster.getSpecies());
    }

    @Test
    public void getHitPointsTest() {
        assertEquals(100, testMonster.getHitPoints());
    }

    @Test
    public void getLocaleTest() {
        assertEquals(Locale.ANYWHERE, testMonster.getLocale());
    }

    @Test
    public void getIsAliveTest() {
        assertTrue(testMonster.isAlive());
        testMonster = new Monster("Fred", "Orc", 0, 50, 50, Locale.ANYWHERE);
        assertFalse(testMonster.isAlive());
    }    

    @Test
    public void setHitPointsTest() {
        testMonster.setHitPoints(0);
        assertEquals(0, testMonster.getHitPoints());
    }

    @Test
    public void takeDamageTest() {
        testMonster.takeDamage(100);
        assertEquals(0, testMonster.getHitPoints());
        testMonster.takeDamage(999);
        assertEquals(0, testMonster.getHitPoints());        // HitPoints cannot go negative
    }

    @Test
    public void setLocaleTest() {
        testMonster.setLocale(Locale.CROSSROADS);
        assertEquals(Locale.CROSSROADS, testMonster.getLocale());
    }

    @Test
    public void toStringTest() {
        String s = testMonster.toString();
        assertTrue(s.contains(testMonster.getName()));
        assertTrue(s.contains(testMonster.getSpecies()));
        assertTrue(s.contains("" + testMonster.getHitPoints()));
    }

}