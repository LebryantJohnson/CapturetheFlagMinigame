package me.lebryant.capturethecastle.commands;

import me.lebryant.capturethecastle.AdminGuiPanel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminGUICommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("&aOnly Players use that!");
        }
        if(command.getName().equalsIgnoreCase("ccadmingui")){
            Player p = (Player) sender;
            AdminGuiPanel gui = new AdminGuiPanel();
            p.openInventory(gui.getInventory());
            return true;
        }
        return false;
    }
}
