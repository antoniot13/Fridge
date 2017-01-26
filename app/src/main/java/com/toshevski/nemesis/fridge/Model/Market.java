package com.toshevski.nemesis.fridge.Model;

import android.location.Location;

/**
 * Model koj go pretstavuva Marketot
 */
public class Market {
    public String Name;
    public Location Location;

    public Market(String Name, Location Location) {
        this.Name = Name;
        this.Location = Location;
    }
}
