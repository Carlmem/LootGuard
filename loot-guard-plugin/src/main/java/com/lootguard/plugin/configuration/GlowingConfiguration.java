package com.lootguard.plugin.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.lootguard.plugin.service.glowing.EntityGlowingService;
import com.lootguard.plugin.service.glowing.EntityGlowingServiceImpl;
import fr.skytasul.glowingentities.GlowingBlocks;
import fr.skytasul.glowingentities.GlowingEntities;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;

@RequiredArgsConstructor
public class GlowingConfiguration extends AbstractModule {

    private final Plugin plugin;

    @Override
    protected void configure() {
        bind(EntityGlowingService.class).to(EntityGlowingServiceImpl.class);
    }

    @Singleton
    @Provides
    public GlowingEntities glowingEntities() {
        return new GlowingEntities(plugin);
    }

    @Singleton
    @Provides
    public GlowingBlocks glowingBlocks() {
        return new GlowingBlocks(plugin);
    }
}
