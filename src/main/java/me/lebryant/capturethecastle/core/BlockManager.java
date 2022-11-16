package me.lebryant.capturethecastle.core;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockManager implements Listener {
    private GameManager manager;

    public BlockManager(GameManager manager){
        this.manager = manager;

    }
    @EventHandler
    public void canBreakBlock(BlockBreakEvent block){
        Player p=block.getPlayer();
        if(! p.hasPermission("cc.admin")) {
            block.setCancelled(true);
        }
    }
}
