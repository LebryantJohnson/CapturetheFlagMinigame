package me.lebryant.capturethecastle.core;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {
    public int id;
    public  Location BlueSpawn;
    public  Location RedSpawn;
    public final Location spawn;
    private final List<UUID> players = new ArrayList<UUID>();


    public Arena(int id, Location spawn) {
        this.id = id;
        this.spawn = spawn;
    }

    public int getId() {
        return this.id;
    }

    public List<UUID> getPlayers() {
        return this.players;
    }
}
