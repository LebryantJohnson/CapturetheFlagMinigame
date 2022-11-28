package me.lebryant.capturethecastle.commands.admin;

import me.lebryant.capturethecastle.core.Arena;
import me.lebryant.capturethecastle.core.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class blueteamspawn implements CommandExecutor {

    GameManager gameManager;

    public blueteamspawn(GameManager gameManager){
        this.gameManager=gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        Command cmd= command;
        if(cmd.getName().equalsIgnoreCase("blueteamspawn") && p.hasPermission("cc.admin")){
            if(args.length != 1){
                p.sendMessage("Insuffcient arguments!");
                return true;
            }
            int num = 0;
            try{
                num = Integer.parseInt(args[0]);
            }catch(NumberFormatException e){
                p.sendMessage("Invalid arena ID");
            }
            gameManager.setBlueSpawn(p.getLocation(), num);

            return true;
        }
        return false;
    }
}
