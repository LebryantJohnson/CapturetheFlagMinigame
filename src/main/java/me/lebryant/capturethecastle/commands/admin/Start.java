package me.lebryant.capturethecastle.commands.admin;

import me.lebryant.capturethecastle.core.GameManager;
import me.lebryant.capturethecastle.core.GameState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Start implements CommandExecutor {

    private GameManager gameManager;

    public Start(GameManager gameManager){
        this.gameManager= gameManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("&aOnly Players use that!");
        }
        if (command.getName().equalsIgnoreCase("start") && sender.hasPermission("cc.admin")) {
            gameManager.setGameState(GameState.STARTING);
            return true;
        }
        return false;
    }
}
