package me.lebryant.capturethecastle.commands;

import me.lebryant.capturethecastle.core.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class leave implements CommandExecutor {
    private GameManager gameManager;
    public leave(GameManager gameManager){
        this.gameManager= gameManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("&aOnly Players use that!");
        }
        if (command.getName().equalsIgnoreCase("leave")) {
            Player p = (Player) sender;
            gameManager.removePlayer(p);
            p.sendMessage("You have left the arena!");

            return true;
        }
        return false;
    }
}
