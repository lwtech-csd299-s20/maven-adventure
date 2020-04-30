package edu.lwtech.csd299.samples.mavenadventure;

import java.util.*;

public class LocationMenu {

    private List<LocationMenuItem> locationItems;

    public LocationMenu() {
        locationItems = new ArrayList<>();
    }

    // ------------------------------------------

    public void addItem(LocationMenuItem item) {
        locationItems.add(item);
    }

    public void runItem(int i) {
        locationItems.get(i).run();
    }

    // ------------------------------------------

    @Override
    public String toString() {
        int i = 1;
        String s = "";
        for (LocationMenuItem item : locationItems) {
            s += i + ") " + item.toString() + "\n";
            i++;
        }
        return s;
    }
}