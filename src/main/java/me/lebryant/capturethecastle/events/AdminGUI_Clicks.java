package me.lebryant.capturethecastle.events;

import me.lebryant.capturethecastle.menus.AdminGuiPanel;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class AdminGUI_Clicks implements Listener {
    private Object AdminGuiPanel;

    @EventHandler
    public void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getClickedInventory()== null){return;}

        if(e.getClickedInventory().getHolder() instanceof AdminGuiPanel){
            e.setCancelled(true);

            if(e.getCurrentItem() .getType()== null){return;}
            if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD){
            p.sendMessage(ChatColor.RED + "Closed Panel");
            p.closeInventory();
            }

        }

    }
}
