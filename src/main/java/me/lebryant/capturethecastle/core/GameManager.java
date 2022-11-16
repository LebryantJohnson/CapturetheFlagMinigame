package me.lebryant.capturethecastle.core;

import me.lebryant.capturethecastle.CaptureTheCastle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class GameManager {
    private final CaptureTheCastle plugin;
    public GameState gameState= GameState.LOBBY;
    public GameManager(CaptureTheCastle plugin) {
        this.plugin = plugin;
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
