package me.lebryant.capturethecastle.events;

import me.lebryant.capturethecastle.core.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerManager implements Listener {
    private GameManager manager;

    public PlayerManager(GameManager manager){
        this.manager = manager;

    }

    public PlayerManager() {

    }

    @EventHandler
    public void canBreakBlock(BlockBreakEvent block){
        Player p=block.getPlayer();
        if(p.hasPermission("cc.admin")) { }
        else{
            block.setCancelled(true);
        }
    }
    @EventHandler
    public void BlockPlaceEvent(BlockPlaceEvent block){
        Player p=block.getPlayer();
        if(!p.hasPermission("cc.admin")) {
            block.setCancelled(true);
        }
    }
    @EventHandler
    public void leave(PlayerQuitEvent q){

        Player p = q.getPlayer();
        boolean isingame= manager.isInGame(p);
        if(isingame==true) {
            manager.removePlayer(p);
        }
    }


}
