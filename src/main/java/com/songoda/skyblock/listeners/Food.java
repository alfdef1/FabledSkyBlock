package com.songoda.skyblock.listeners;

import com.songoda.skyblock.SkyBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class Food implements Listener {

    private final SkyBlock plugin;

    public Food(SkyBlock plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();

        if (plugin.getWorldManager().isIslandWorld(player.getWorld())) {
            // Check permissions.
            plugin.getPermissionManager().processPermission(event, player, player.getLocation());
        }
    }
}
