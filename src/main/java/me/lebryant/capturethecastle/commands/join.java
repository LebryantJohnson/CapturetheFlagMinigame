package me.lebryant.capturethecastle.commands;

import me.lebryant.capturethecastle.core.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class join implements CommandExecutor {
    private GameManager gameManager;
    public join(GameManager gameManager){
        this.gameManager= gameManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("&aOnly Players use that!");
        }
        if(command.getName().equalsIgnoreCase("join") && args.length==0){
            sender.sendMessage("&aYou did not specify which game to join");
        }
        if (command.getName().equalsIgnoreCase("join")) {
            Player p = (Player) sender;
            int num = 0;
            try {
                num = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                p.sendMessage("Invalid arena ID");
                return false;
            }
            gameManager.addPlayer(p, num);
            return true;
        }
        return false;
    }
}
