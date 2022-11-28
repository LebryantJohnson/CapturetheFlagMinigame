package me.lebryant.capturethecastle.commands.user;
import me.lebryant.capturethecastle.core.GameManager;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class lobby_command implements CommandExecutor {

    private GameManager gameManager;
    public lobby_command(GameManager gameManager){
        this.gameManager= gameManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("&aOnly Players use that!");
        }
        if (command.getName().equalsIgnoreCase("lobby")) {
            Player p = (Player) sender;
            gameManager.getLobbyCords(p);
            return true;
        }
        return false;
    }
    }
