package me.lebryant.capturethecastle.core;

import fr.mrmicky.fastboard.FastBoard;
import me.lebryant.capturethecastle.CaptureTheCastle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class GameManager {
    private final CaptureTheCastle plugin;
    public GameState gameState= GameState.LOBBY;

    //player data
    private final Map<UUID, Location> locs = new HashMap<>();
    private final Map<UUID, ItemStack[]> inv = new HashMap<>();
    private final Map<UUID, ItemStack[]> armor = new HashMap<>();

    private final List<Arena> arenas = new ArrayList<>();
    private int arenaSize = 0;



    public GameManager(CaptureTheCastle plugin) {

        this.plugin = plugin;
    }


    /**
     * Acquires an arena based on its ID number
     *
     * @param i the ID to search the arenas for
     * @return the arena possessing the specified ID
     */
    public Arena getArena(int i){
        for (Arena a : this.arenas) {
            if (a.getId() == i) {
                return a;
            }
        }

        return null; // Not found
    }
    /**
     * Adds the player to an arena
     *
     * <p>Gets the arena by ID, checks that it exists,
     * and check the player isn't already in a game.</p>
     *
     * @param p the player to add
     * @param i the arena ID. A check will be done to ensure its validity.
     */
    public void addPlayer(Player p, int i) {
        Arena a = this.getArena(i);
        if (a == null) {
            p.sendMessage("Invalid arena!");
            return;
        }

        if (this.isInGame(p)) {
            p.sendMessage("Cannot join more than 1 game!");
            return;
        }

        // Adds the player to the arena player list
        a.getPlayers().add(p.getUniqueId());

        // Save the inventory and armor
        inv.put(p.getUniqueId(), p.getInventory().getContents());
        armor.put(p.getUniqueId(), p.getInventory().getArmorContents());

        // Clear inventory and armor
        p.getInventory().setArmorContents(null);
        p.getInventory().clear();

        // Save the players' last location before joining,
        // then teleporting them to the arena spawn
        locs.put(p.getUniqueId(), p.getLocation());
        p.teleport(a.spawn);
    }
    /**
     * Removes the player from their current arena.
     *
     * <p>The player is allowed to not be in game, a check
     * will be performed to ensure the validity of the arena</p>
     *
     * @param p the player to remove from the arena
     */
    public void removePlayer(Player p) {
        Arena a = null;

        // Searches each arena for the player
        for (Arena arena : this.arenas) {
            if (arena.getPlayers().contains(p.getUniqueId()))
                a = arena;
        }

        // Check arena validity
        if (a == null) {
            p.sendMessage("Invalid operation!");
            return;
        }

        // Remove from arena player lost
        a.getPlayers().remove(p.getName());

        // Remove inventory acquired during the game
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);

        // Restore inventory and armor
        // p.getInventory().setContents(inv.get(p.getName()));
        // p.getInventory().setArmorContents(armor.get(p.getName()));

        // Remove player data entries
        inv.remove(p.getUniqueId());
        armor.remove(p.getUniqueId());

        // Teleport to original location, remove it too
        p.teleport(locs.get(p.getUniqueId()));
        locs.remove(p.getUniqueId());

        // Heh, you're safe now :)
        p.setFireTicks(0);
    }

    /**
     * Creates an arena at the specified location
     *
     * @param loc the location for arena spawn
     * @return the arena created
     */
    public Arena createArena(Location loc){
        int num = arenaSize + 1;
        arenaSize++;

        Arena a = new Arena(num, loc);
        arenas.add(a);

        plugin.getDataConfig().set("Arenas." + num, serializeLoc(loc));
        List<Integer> list = plugin.getDataConfig().getIntegerList("Arenas.Arenas");
        list.add(num);
        plugin.getDataConfig().set("Arenas.Arenas", list);
        plugin.saveData();

        return a;
    }

    /**
     * Checks if the player is currently in an arena
     *
     * @param p the player to check
     * @return {@code true} if the player is in game
     */
    public boolean isInGame(Player p) {
        for (Arena a : this.arenas) {
            if (a.getPlayers().contains(p.getUniqueId()))
                return true;
        }
        return false;
    }

    public void loadGames(){
        arenaSize = 0;

        if(plugin.getDataConfig().getIntegerList("Arenas.Arenas").isEmpty()){
            return;
        }

        for(int i : plugin.getDataConfig().getIntegerList("Arenas.Arenas")){
            Arena a = reloadArena(deserializeLoc(plugin.getDataConfig().getString("Arenas." + i)));
            a.id = i;
        }
    }

    public Arena reloadArena(Location loc) {
        int num = arenaSize + 1;
        arenaSize++;

        Arena a = new Arena(num, loc);
        arenas.add(a);

        return a;
    }


    //remove arena
    public void removeArena(int i) {
        Arena a = getArena(i);
        if(a == null) {
            return;
        }
        arenas.remove(a);

        plugin.getDataConfig().set("Arenas." + i, null);
        List<Integer> list = plugin.getDataConfig().getIntegerList("Arenas.Arenas");
        list.remove(i);
        plugin.getDataConfig().set("Arenas.Arenas", list);
        plugin.saveData();
    }

    // UTILITY METHODS

    public String serializeLoc(Location l){
        return l.getWorld().getName() + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ();
    }

    public Location deserializeLoc(String s){
        String[] st = s.split(",");
        return new Location(Bukkit.getWorld(st[0]), Integer.parseInt(st[1]), Integer.parseInt(st[2]), Integer.parseInt(st[3]));
    }




    // set the coordinates of the lobby
    public void updateLobbyCords(Location inputLocation, World inputworld, Player p){
        Location lobby= inputLocation;
        plugin.getDataConfig().set("lobby.world", lobby);
        plugin.saveData();
        p.sendMessage(ChatColor.GREEN+"Set Cords to: " + lobby.getX() + " "+ lobby.getY()+" "+ lobby.getZ());

    }
    // tp the player to the lobby cords
    public void getLobbyCords(Player p){
        Location loc = (Location) plugin.getDataConfig().get("lobby.world");
        p.teleport(loc);
    }

    public void setGameState(GameState gameState){
        if (this.gameState==GameState.STARTING && this.gameState==GameState.ACTIVE) return;
        this.gameState = gameState;
        switch (gameState){
            case VOTING:
                Bukkit.broadcastMessage(ChatColor.GREEN + "Please vote!");

            case STARTING:
                Bukkit.broadcastMessage(ChatColor.GREEN + "Game Starting!");
            case ACTIVE:
                Bukkit.broadcastMessage(ChatColor.GREEN + "Game is Active");
            case WON:
                Bukkit.broadcastMessage(ChatColor.GREEN + "Game has ended. Thanks for Playing!");
            case RESTARTING:
                Bukkit.broadcastMessage(ChatColor.GREEN + "Game restarting");
        }
    }
    public void SetArenas(Player p){
        Location loc= p.getLocation();
        plugin.getConfig().set("Arenas.world", loc);
        plugin.saveConfig();
        p.sendMessage(ChatColor.GREEN+"Set Cords to: " + loc.getX() + " "+ loc.getY()+" "+ loc.getZ());

    }

    public void createBoard(Player p ){
        FastBoard gameBoard = new FastBoard(p);
        // Set the title
        gameBoard.updateTitle(ChatColor.GOLD + "Capture the Castle Minigame");
        // Change the lines
        ArrayList<String> linesArray = new ArrayList<String>();
        for(String lines : plugin.getConfig().getStringList("gameScoreboard")){
            linesArray.add(lines);
        }
        gameBoard.updateLines(
                linesArray
        );

    }
    public void cleanUp(){

    }

}
