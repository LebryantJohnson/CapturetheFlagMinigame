package me.lebryant.capturethecastle;

import me.lebryant.capturethecastle.commands.*;
import me.lebryant.capturethecastle.events.PlayerManager;
import me.lebryant.capturethecastle.events.AdminGUI_Clicks;
import me.lebryant.capturethecastle.core.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class CaptureTheCastle extends JavaPlugin {

    public static CaptureTheCastle main;
    private GameManager gameManager;

    private File dataConfigFile;
    private FileConfiguration dataConfig;
    @Override
    public void onEnable() {

        main=this;
        // Plugin startup logic
        this.saveDefaultConfig();
        createDataConfig();
        saveDefaultData();
        this.gameManager= new GameManager(this);

        //register commands
        getCommand("capturethecastle").setExecutor(new selfcommand());
        getCommand("ccadmingui").setExecutor(new AdminGUICommand());
        getCommand("start").setExecutor(new Start(this.gameManager));
        getCommand("lobby").setExecutor(new lobby_command(this.gameManager));
        getCommand("setlobby").setExecutor(new setlobby_command(this.gameManager));
        getCommand("setarenalocation").setExecutor(new arenaloc(this.gameManager));
        getCommand("join").setExecutor(new join(this.gameManager));
        getCommand("leave").setExecutor(new leave(this.gameManager));
        getCommand("createccarena").setExecutor(new create(this.gameManager));
        getCommand("createccarena").setExecutor(new create(this.gameManager));
        //register events
        getServer().getPluginManager().registerEvents( new AdminGUI_Clicks(), this);
        getServer().getPluginManager().registerEvents(new PlayerManager(), this);
        //send messages to console
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "-----------========      Capture the Castle     ========-----------");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "- Welcome to Capture the Castle Plugin. View our github for help: https://github.com/LebryantJohnson/CapturetheFlagMinigame");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "- If an error occurs you are supposed to contact the developer and DO NOT try out things yourself.");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "- Report bugs to the developer if there are any.");
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "-----------=========================-----------");

        gameManager.loadGames();
    }


    // create the data.yml file to store data essential to the minigame
    public FileConfiguration getDataConfig(){
        return this.dataConfig;
    }
    public void createDataConfig(){
        dataConfigFile= new File(getDataFolder(), "data.yml");
        if(!dataConfigFile.exists()){
            dataConfigFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
        }
        dataConfig = new YamlConfiguration();
        try {
            dataConfig.load(dataConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
   public void saveData(){
        if(this.dataConfig==null) {

        }
        try{
            this.getDataConfig().save(this.dataConfigFile);
        } catch (IOException e){
            e.printStackTrace();
        }
   }
   private void saveDefaultData(){
        if(this.dataConfigFile == null){
            this.dataConfigFile=new File(this.getDataFolder(), "data.yml");
        }
        if(!this.dataConfigFile.exists()){
            this.saveResource("data.yml", false);
        }
   }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        gameManager.cleanUp();
    }
}

