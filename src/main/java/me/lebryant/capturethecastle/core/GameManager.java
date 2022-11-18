package me.lebryant.capturethecastle.core;

import me.lebryant.capturethecastle.CaptureTheCastle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class GameManager {
    private static GameManager manager;
    private final CaptureTheCastle plugin;
    public GameState gameState= GameState.LOBBY;

    public GameManager(CaptureTheCastle plugin) {
        this.plugin = plugin;
        Location lobbyCord= new Location(Bukkit.getWorld("world"), 0,0,0);
    }

    public void updateLobbyCords(Location inputLocation, World inputworld, Player p){
        Location lobby= inputLocation;
        plugin.getDataConfig().set("lobby.world", lobby);
        plugin.saveData();
        p.sendMessage(ChatColor.GREEN+"Set Cords to: " + lobby.getX() + " "+ lobby.getY()+" "+ lobby.getZ());
    }
    public void getLobbyCords(Player p){
        Location loc = (Location) plugin.getDataConfig().get("lobby.world");
        p.teleport(loc);
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
    public void SetArenas(Player p){
        Location loc= p.getLocation();
        plugin.getConfig().set("Arenas.world", loc);
        plugin.saveConfig();
        p.sendMessage(ChatColor.GREEN+"Set Cords to: " + loc.getX() + " "+ loc.getY()+" "+ loc.getZ());

    }

    public void Setup(){
        int AmountofArenas= (int) plugin.getConfig().get("Arenas.amount");
        Location loc = (Location) plugin.getConfig().get("Arenas.world");
        for (AmountofArenas = 1; AmountofArenas <= 10; AmountofArenas++){
            //
        }
    }

    public void cleanUp(){

    }

}
