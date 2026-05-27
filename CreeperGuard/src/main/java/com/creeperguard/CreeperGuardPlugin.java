package com.creeperguard;

import org.bukkit.plugin.java.JavaPlugin;

public class CreeperGuardPlugin extends JavaPlugin {

    private boolean enabled = true;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        enabled = getConfig().getBoolean("enabled", true);

        getServer().getPluginManager().registerEvents(new CreeperExplosionListener(this), this);
        CreeperGuardCommand cmd = new CreeperGuardCommand(this);
        getCommand("creeperguard").setExecutor(cmd);
        getCommand("creeperguard").setTabCompleter(cmd);
        getLogger().info("CreeperGuard " + (enabled ? "enabled" : "disabled") + " - Creeper explosions " + (enabled ? "blocked" : "allowed"));
    }

    @Override
    public void onDisable() {
        getLogger().info("CreeperGuard disabled");
    }

    public boolean isGuardActive() {
        return enabled;
    }

    public void setGuardActive(boolean active) {
        this.enabled = active;
        getConfig().set("enabled", active);
        saveConfig();
    }
}
