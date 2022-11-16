package me.lebryant.capturethecastle.menus;

import me.lebryant.capturethecastle.CaptureTheCastle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Collections;
import java.util.List;

public class AdminGuiPanel implements InventoryHolder {
    FileConfiguration config = CaptureTheCastle.main.getConfig();

    private Inventory inv;
    public void OpenAdminGui() {
        inv = Bukkit.createInventory(this, 9, "Admin GUI");
        initializeItems();
    }
    public void initializeItems() {
        inv.addItem(createGuiItem("Quit", Material.DIAMOND_SWORD, Collections.singletonList(ChatColor.DARK_GREEN + "click to continue"), 1));
        inv.addItem(createGuiItem("Continue", Material.DIAMOND_AXE, Collections.singletonList(ChatColor.DARK_GREEN + "click to continue"), 1));
    }



    // Nice little method to create a gui item with a custom name, and description
    protected ItemStack createGuiItem(String Name, Material mat, List<String> lore, int amount ) {
        ItemStack item = new ItemStack(mat, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;

    }

    @Override
    public Inventory getInventory() {
        OpenAdminGui();
        return inv;
    }
}
