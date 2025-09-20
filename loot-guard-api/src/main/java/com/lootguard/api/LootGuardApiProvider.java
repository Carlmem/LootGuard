package com.lootguard.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.IllegalPluginAccessException;

@SuppressWarnings("unused")
public interface LootGuardApiProvider {

    String PLUGIN_NAME = "LootGuard";

    static LootGuardApiProvider getInstance() {
        final var plugin = Bukkit.getPluginManager().getPlugin(PLUGIN_NAME);
        if (plugin == null) {
            throw new IllegalPluginAccessException("Can't find: " + PLUGIN_NAME);
        }

        if (plugin instanceof LootGuardApiProvider apiProvider) {
            return apiProvider;
        }

        throw new IllegalPluginAccessException("Can't hook: " + PLUGIN_NAME);
    }
}
