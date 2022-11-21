package me.lebryant.capturethecastle.commands;

import fr.mrmicky.fastboard.FastBoard;
import me.lebryant.capturethecastle.core.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class join implements CommandExecutor {
    private GameManager gameManager;

    public join(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "&Only Players use that!");
        }
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("join")) {
            if (args.length != 1) {
                p.sendMessage(ChatColor.RED+"Insuffcient arguments!");
                return true;
            }
            int num = 0;
            try {
                num = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                p.sendMessage(ChatColor.RED+"Invalid arena ID");
            }
            gameManager.addPlayer(p, num);
            gameManager.createBoard(p);
            return true;
        }
        return false;
    }
}
