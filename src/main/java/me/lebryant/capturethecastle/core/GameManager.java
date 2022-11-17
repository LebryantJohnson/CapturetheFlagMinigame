package me.lebryant.capturethecastle.core;

import me.lebryant.capturethecastle.CaptureTheCastle;
import me.lebryant.capturethecastle.commands.Arena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class GameManager {
    private static GameManager manager;
    private final CaptureTheCastle plugin;
    public GameState gameState= GameState.LOBBY;

    private final ArrayList<Arena> arenas;

    public GameManager(CaptureTheCastle plugin) {
        this.plugin = plugin;
        this.arenas = new ArrayList<Arena>();
        Location lobbyCord= new Location(Bukkit.getWorld("world"), 0,0,0);
    }

    Location lobby;
    public void updateLobbyCords(Location inputLocation, World inputworld, Player p){
        //new Location(Bukkit.getWorld(String.valueOf(inputworld)), inputLocation.getX(), inputLocation.getY(), inputLocation.getZ());
        lobby= inputLocation;
        p.sendMessage(ChatColor.GREEN+"Set lobby to: " + inputLocation.getX()+ " "+ inputLocation.getY()+ " "+ inputLocation.getZ());
    }
    public void getLobbyCords(Player p, World w){
        p.teleport(lobby);
    }

    public void setGameState(GameState gameState){
        if (this.gameState==GameState.STARTING && this.gameState==GameState.ACTIVE) return;
        this.gameState = gameState;
        switch (gameState){
            case VOTING:
                Bukkit.broadcastMessage(ChatColor.GREEN + "Please vote!");

            case STARTING:
                Bukkit.broadcastMessage(ChatColor.GREEN + "Game Starting!");
            case ACTIVE:
                Bukkit.broadcastMessage(ChatColor.GREEN + "Game is Active");
            case WON:
                Bukkit.broadcastMessage(ChatColor.GREEN + "Game has ended. Thanks for Playing!");
            case RESTARTING:
                Bukkit.broadcastMessage(ChatColor.GREEN + "Game restarting");
        }
    }
    public void cleanUp(){

    }
}
