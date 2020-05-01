package edu.lwtech.csd299.samples.mavenadventure;

import java.util.*;

public class AdventureGame {

    private static Scanner scanner = new Scanner(System.in);
    private static Map<Locale, Location> locations = new HashMap<>();
    private static Player player = null;

	public static void main(String[] args) {

        initializeGame();

		System.out.print("Ahoy good sir!  Welcome to Jumanji.  Please enter your name: ");
		String playerName = scanner.nextLine();
		System.out.println("Allrighty then, " + playerName + ", let's start the game!");	

        player = new Player(playerName, 10, Locale.TOWN_GATE);
        player.addToBackpack(new Item("Rabbit's Foot"));
        player.addToBackpack(new Item("Empty Bottle"));
        player.equipWeapon(new Weapon("Dagger", 4));
        player.equipArmor(new Armor("Leather", 4));

        while (player.isAlive() && player.getLocale() != Locale.IN_TOWN) {
            takeTurn();
        }

        if (player.isAlive()) {
            System.out.println("Guard: Oh, I see you killed that pesky goblin! You have saved us all!");
            System.out.println("Guard: It seems you are a trustworthy guy, " + player.getName() + ". Welcome to our town!");
        } else {
            System.out.println("And thus ends the tale of poor old " + player.getName() + ".  What an idiot!");
        }

        System.out.println();
        System.out.println("      THE END(?)");
        System.out.println();
    }

    private static void takeTurn() {
        System.out.println();
        System.out.println("------------------------------------------------------------------");
        System.out.println(player);
        System.out.println("------------------------------------------------------------------");

        Location location = locations.get(player.getLocale());
        System.out.println("You are at " + location.getDescription());
        location.getPreAction().run();
        System.out.println("\nWhat would you like to do?");
        System.out.print(location.getMenu());
        int i = scanner.nextInt();
        System.out.println();
        i--;                                    // Convert 1-based menus into 0-based indexes
        location.getMenu().runItem(i);
    }

    private static void initializeGame() {

        // TOWN_GATE
        LocationMenu menu = new LocationMenu();
        menu.addItem(new LocationMenuItem("Go north", ()->player.setLocale(Locale.CROSSROADS)));
        menu.addItem(new LocationMenuItem("Talk to the guard", ()->talkToGuard()));
        menu.addItem(new LocationMenuItem("Attack the guard", ()->attackGuard()));
        Location location = new Location(Locale.TOWN_GATE,
            "the gates of a town. A guard is standing in front of you.",
            menu);
        locations.put(Locale.TOWN_GATE, location);

        // CROSSROADS
        menu = new LocationMenu();
        menu.addItem(new LocationMenuItem("Go north", ()->player.setLocale(Locale.RIVER)));
        menu.addItem(new LocationMenuItem("Go east", ()->player.setLocale(Locale.WOODS)));
        menu.addItem(new LocationMenuItem("Go south", ()->player.setLocale(Locale.TOWN_GATE)));
        menu.addItem(new LocationMenuItem("Go west", ()->player.setLocale(Locale.GOBLIN)));
        location = new Location(Locale.CROSSROADS,
            "a lonely 4-way crossroads. You cannot see what lies in each direction.",
            menu);
        locations.put(Locale.CROSSROADS, location);

        // RIVER
        menu = new LocationMenu();
        menu.addItem(new LocationMenuItem("Go south", ()->player.setLocale(Locale.CROSSROADS)));
        menu.addItem(new LocationMenuItem("Rest some more", ()->player.setLocale(Locale.RIVER)));
        location = new Location(Locale.RIVER,
            "a swift-flowing, broad river.\nYou drink the water and rest at the riverside. You feel refreshed.",
            menu);
        location.setPreAction(()->player.setHitPoints(player.getHitPoints() + 1));
        locations.put(Locale.RIVER, location);

        // WOODS
        menu = new LocationMenu();
        menu.addItem(new LocationMenuItem("Go west", ()->player.setLocale(Locale.CROSSROADS)));
        location = new Location(Locale.WOODS,
            "a dark forboding forest.\nYou discover a Long Sword hidden behind a tree. You pick up the sword.",
            menu);
        location.setPreAction(()->player.equipWeapon(new Weapon("Long Sword", 8)));
        locations.put(Locale.WOODS, location);

        // GOBLIN
        menu = new LocationMenu();
        monster.addLoot(new Item("Silver Ring"));
        menu.addItem(new LocationMenuItem("Attack!", ()->fight(monster)));
        menu.addItem(new LocationMenuItem("Run away!", ()->player.setLocale(Locale.CROSSROADS)));
        location = new Location(Locale.GOBLIN,
            "a tree that has fallen (or been placed) across the road.\nSuddenly, a Goblin advances towards you menacingly...",
            menu);
        locations.put(Locale.GOBLIN, location);
    }

    private static void talkToGuard() {
        if (player.searchBackpack("Silver Ring")) {
            player.setLocale(Locale.IN_TOWN);
        } else {
            System.out.println("Guard: Hello there, stranger. So your name is " + player.getName() + " is it?");
            System.out.println("Sorry but I cannot let a stranger like yourself enter our town without proof that you are trustworthy.");
            scanner.nextLine();
        }
    }

    private static void attackGuard() {
        System.out.println("Guard: Right. Here's something for your trouble.");
        System.out.println("You loose 2 health.");
        player.setHitPoints(player.getHitPoints() - 2);
    }
    
	private static void fight(Monster monster) {
        while (true) {
            player.attack(monster);
            System.out.println("You attack " + monster.getName() + "!  They now have " + monster.getHitPoints() + " health.");
            if (monster.getHitPoints() <= 0) {
                System.out.println("Congratulations! You are victorious (this time)!");
                List<Item> loot = monster.takeLoot();
                for (Item item : loot) {
                    System.out.println("You find a " + item.getName() + " on the corpse and put it in your backpack.");
                    player.addToBackpack(item);
                }
                System.out.println("You head back to the crossroads before more monsters can arrive.");
                player.setLocale(Locale.CROSSROADS);
                return;
            }

            monster.attack(player);
            System.out.println(monster.getName() + " attacks you!  You now have " + player.getHitPoints() + " health.");
            if (player.getHitPoints() <= 0) {
                System.out.println("Oooooooooo...  That's gotta hurt!");
                return;
            }

            System.out.print("Do you wish to continue this ill-advised assault (y/n)?");
            String s = scanner.next();
            if (s.toLowerCase().startsWith("n")) {
                System.out.println("Descretion truly is the better part of valor, eh what?");
                player.setLocale(Locale.CROSSROADS);
                return;
            }
        }
	}
			
}