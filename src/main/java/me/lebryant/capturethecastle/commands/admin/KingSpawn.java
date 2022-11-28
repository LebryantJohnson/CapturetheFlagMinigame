package me.lebryant.capturethecastle.commands.admin;

import me.lebryant.capturethecastle.CaptureTheCastle;
import me.lebryant.capturethecastle.core.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class KingSpawn implements CommandExecutor {
    private GameManager gameManager;
    private CaptureTheCastle plugin;

    public KingSpawn(GameManager gameManager, CaptureTheCastle plugin) {
        this.gameManager = gameManager;
        this.plugin=plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "&Only Players use that!");
        }
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("setkingspawn")) {
            if (args.length != 1) {
                p.sendMessage(ChatColor.RED + "Insuffcient arguments!");
                return true;
            }
            if(args[0].equalsIgnoreCase("setkingspawn")){
                Player player = (Player) sender;
                player.sendMessage(ChatColor.GREEN + "You have placed the king!");

                Zombie zombie = (Zombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
                zombie.setCustomName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("king.name")));
                zombie.setCustomNameVisible(true);
                zombie.setBaby(false);

                zombie.setMaxHealth(plugin.getConfig().getDouble("king.health"));
                player.spigot().setCollidesWithEntities(false);

                ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                SkullMeta headMeta = (SkullMeta) head.getItemMeta();
                headMeta.setOwner("SolidFlames");
                head.setItemMeta(headMeta);

                ItemStack sword = new ItemStack(Material.IRON_SWORD);
                sword.addEnchantment(Enchantment.DURABILITY, 1);

                ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
                ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
                ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);

                ItemMeta chestplateMeta = chestplate.getItemMeta();
                ItemMeta leggingsMeta = leggings.getItemMeta();
                ItemMeta bootsMeta = boots.getItemMeta();

                chestplateMeta.spigot().setUnbreakable(true);
                leggingsMeta.spigot().setUnbreakable(true);
                bootsMeta.spigot().setUnbreakable(true);

                chestplate.setItemMeta(chestplateMeta);
                leggings.setItemMeta(leggingsMeta);
                boots.setItemMeta(bootsMeta);

                zombie.getEquipment().setHelmet(head);
                zombie.getEquipment().setChestplate(chestplate);
                zombie.getEquipment().setLeggings(leggings);
                zombie.getEquipment().setBoots(boots);
            }

        }
        return false;
    }
}
