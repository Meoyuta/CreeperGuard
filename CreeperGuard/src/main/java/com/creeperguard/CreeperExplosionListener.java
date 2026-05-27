package com.creeperguard;

import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExplosionPrimeEvent;

public class CreeperExplosionListener implements Listener {

    private final CreeperGuardPlugin plugin;

    public CreeperExplosionListener(CreeperGuardPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onExplosionPrime(ExplosionPrimeEvent event) {
        if (plugin.isGuardActive() && event.getEntity() instanceof Creeper) {
            event.setCancelled(true);
            event.getEntity().remove();
        }
    }
}
