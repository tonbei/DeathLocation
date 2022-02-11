package com.github.tonbei.deathlocation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeathLocation extends JavaPlugin implements Listener {

    private final boolean debug = false;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player deathplayer = e.getEntity();
        Location location = deathplayer.getLocation();
        double x = Math.round(location.getX() * 1000.0) / 1000.0;
        double y = Math.round(location.getY() * 1000.0) / 1000.0;
        double z = Math.round(location.getZ() * 1000.0) / 1000.0;
        World.Environment dimension = deathplayer.getWorld().getEnvironment();
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage("[" + ChatColor.RED + "Death Location" + ChatColor.RESET + "] <" + deathplayer.getDisplayName()
                    + "> Dimension: " + ChatColor.DARK_PURPLE + dimension + ChatColor.RESET);
            p.sendMessage("[" + ChatColor.RED + "Death Location" + ChatColor.RESET + "] "
                    + "{ X: " + ChatColor.GREEN + x + ChatColor.RESET
                    + ", Y: " + ChatColor.GREEN + y + ChatColor.RESET
                    + ", Z: " + ChatColor.GREEN + z + ChatColor.RESET + " }");
        }
    }
}
