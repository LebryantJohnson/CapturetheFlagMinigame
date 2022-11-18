package me.lebryant.capturethecastle.commands;

import me.lebryant.capturethecastle.core.GameManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setlobby_command implements CommandExecutor {
    private GameManager gameManager;

    public setlobby_command(GameManager gameManager){
        this.gameManager= gameManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("&aOnly Players use that!");
        }
        if (command.getName().equalsIgnoreCase("setlobby") && sender.hasPermission("cc.admin")) {
            Player p = (Player) sender;
            Location inputLoc=p.getLocation();
            World w = p.getWorld();
            gameManager.updateLobbyCords(inputLoc, w, p);
            return true;
        }
        return false;
    }
    }
