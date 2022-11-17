package me.lebryant.capturethecastle;

import me.lebryant.capturethecastle.commands.*;
import me.lebryant.capturethecastle.core.BlockManager;
import me.lebryant.capturethecastle.events.AdminGUI_Clicks;
import me.lebryant.capturethecastle.core.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class CaptureTheCastle extends JavaPlugin {

    public static CaptureTheCastle main;
    private GameManager gameManager;
    @Override
    public void onEnable() {

        main=this;
        // Plugin startup logic
        this.saveDefaultConfig();
        this.gameManager= new GameManager(this);
        //register commands
        getCommand("capturethecastle").setExecutor(new selfcommand());
        getCommand("ccadmingui").setExecutor(new AdminGUICommand());
        getCommand("start").setExecutor(new Start(this.gameManager));
        getCommand("lobby").setExecutor(new lobby_command(this.gameManager));
        getCommand("setlobby").setExecutor(new setlobby_command(this.gameManager));


        //register events
        getServer().getPluginManager().registerEvents( new AdminGUI_Clicks(), this);
        getServer().getPluginManager().registerEvents(new BlockManager(), this);



        //send messages to console
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "-----------========      Capture the Castle     ========-----------");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "- Welcome to Capture the Castle Plugin. View our github for help: https://github.com/LebryantJohnson/CapturetheFlagMinigame");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "- If an error occurs you are supposed to contact the developer and DO NOT try out things yourself.");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "- Report bugs to the developer if there are any.");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "-----------=========================-----------");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        gameManager.cleanUp();
    }
}

