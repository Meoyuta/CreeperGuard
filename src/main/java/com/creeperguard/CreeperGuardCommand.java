package com.creeperguard;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreeperGuardCommand implements CommandExecutor, TabCompleter {

    private final CreeperGuardPlugin plugin;

    public CreeperGuardCommand(CreeperGuardPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            boolean active = plugin.isGuardActive();
            sender.sendMessage(ChatColor.YELLOW + "CreeperGuard: " + (active ? ChatColor.GREEN + "ON" : ChatColor.RED + "OFF"));
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "on" -> {
                plugin.setGuardActive(true);
                sender.sendMessage(ChatColor.GREEN + "Creeper explosion protection ENABLED");
            }
            case "off" -> {
                plugin.setGuardActive(false);
                sender.sendMessage(ChatColor.RED + "Creeper explosion protection DISABLED");
            }
            case "toggle" -> {
                plugin.setGuardActive(!plugin.isGuardActive());
                boolean active = plugin.isGuardActive();
                sender.sendMessage(ChatColor.YELLOW + "CreeperGuard " + (active ? ChatColor.GREEN + "ENABLED" : ChatColor.RED + "DISABLED"));
            }
            default -> {
                sender.sendMessage(ChatColor.RED + "Usage: /creeperguard [on|off|toggle]");
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return Arrays.asList("on", "off", "toggle");
        }
        return Collections.emptyList();
    }
}
