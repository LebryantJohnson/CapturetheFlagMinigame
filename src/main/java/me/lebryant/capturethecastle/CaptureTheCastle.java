package me.lebryant.capturethecastle;

import me.lebryant.capturethecastle.commands.AdminGUICommand;
import me.lebryant.capturethecastle.commands.selfcommand;
import me.lebryant.capturethecastle.events.AdminGUI_Clicks;
import org.bukkit.plugin.java.JavaPlugin;

public final class CaptureTheCastle extends JavaPlugin {

    public static CaptureTheCastle main;

    @Override
    public void onEnable() {
        main=this;
        // Plugin startup logic
        getCommand("capturethecastle").setExecutor(new selfcommand());
        getCommand("ccadmingui").setExecutor(new AdminGUICommand());
        getServer().getPluginManager().registerEvents( new AdminGUI_Clicks(), this);

        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

