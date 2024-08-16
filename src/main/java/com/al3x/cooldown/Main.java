package com.al3x.cooldown;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.WindCharge;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(this, this);
        System.out.println("Enabled");
    }

    @EventHandler
    public void onPearlThrow(ProjectileLaunchEvent e) {
        if (e.getEntity().getShooter() instanceof Player player) {
            if (e.getEntity() instanceof EnderPearl) {
                Bukkit.getScheduler().runTaskLater(this, () -> {
                    player.setCooldown(Material.ENDER_PEARL, getConfig().getInt("EnderPearlCooldown"));
                }, 1);
            } else if (e.getEntity() instanceof WindCharge) {
                Bukkit.getScheduler().runTaskLater(this, () -> {
                    player.setCooldown(Material.WIND_CHARGE, getConfig().getInt("WindChargeCooldown"));
                }, 1);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Bukkit.getScheduler().runTaskLater(this, () -> {
            player.setCooldown(Material.GOAT_HORN, getConfig().getInt("GoatHornCooldown"));
        }, 1);
    }
}
