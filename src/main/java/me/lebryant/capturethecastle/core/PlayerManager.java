package me.lebryant.capturethecastle.core;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerManager implements Listener {
    private GameManager gameManager;

    public PlayerManager(GameManager gameManager){
        this.gameManager=gameManager;
    }

    @EventHandler
    public void leave(PlayerQuitEvent q){

        Player p = q.getPlayer();
        boolean isingame= gameManager.isInGame(p);
        if(isingame=true) {
            gameManager.removePlayer(p);
        }
    }
}
