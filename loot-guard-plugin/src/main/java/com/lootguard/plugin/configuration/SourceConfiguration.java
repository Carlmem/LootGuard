package com.lootguard.plugin.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.lootguard.plugin.model.ConfigDto;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;

@RequiredArgsConstructor
public class SourceConfiguration extends AbstractModule {

    private final Plugin plugin;

    private FileConfiguration config;

    @Override
    protected void configure() {
        final var dataFolder = plugin.getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdir();
        }

        File configFile = new File(dataFolder, "config.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                Bukkit.getLogger().log(Level.SEVERE, "Could not create config.yml", e);
            }
        }

        this.config = YamlConfiguration.loadConfiguration(configFile);
        this.config.set("protected-time", this.config.get("protected-time") == null ? 10 : this.config.get("protected-time"));
        this.config.set("loot-permission", this.config.get("loot-permission") == null ? "loot.guard.permission" : this.config.get("loot-permission"));
        this.config.set("glowing", this.config.get("glowing") == null ? true : this.config.get("glowing"));
        try {
            this.config.save(configFile);
        } catch (IOException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Could not save config.yml", e);
        }
    }

    @Singleton
    @Provides
    private ConfigDto configDto() {
        return new ConfigDto(
                this.config.getInt("protected-time"),
                this.config.getString("loot-permission"),
                this.config.getBoolean("glowing")
        );
    }
}
