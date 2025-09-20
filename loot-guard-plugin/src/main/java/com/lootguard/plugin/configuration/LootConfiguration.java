package com.lootguard.plugin.configuration;

import com.google.inject.Provides;
import com.lootguard.plugin.repository.damage.PlayerDamageMobRepository;
import com.lootguard.plugin.repository.damage.PlayerDamageMobRepositoryImpl;
import com.lootguard.plugin.repository.loot.LootOwnerRepository;
import com.lootguard.plugin.repository.loot.LootOwnerRepositoryImpl;
import com.lootguard.plugin.service.loot.LootDivisionService;
import com.lootguard.plugin.service.loot.LootDivisionServiceImpl;
import com.lootguard.plugin.service.loot.handler.GearLootDropHandler;
import com.lootguard.plugin.service.loot.handler.LootDropHandler;
import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.Plugin;

@RequiredArgsConstructor
public class LootConfiguration extends AbstractModule {

    private final Plugin plugin;

    @Override
    protected void configure() {
        bind(LootOwnerRepository.class).to(LootOwnerRepositoryImpl.class);
        bind(LootDropHandler.class).to(GearLootDropHandler.class);
        bind(LootDivisionService.class).to(LootDivisionServiceImpl.class);
        bind(PlayerDamageMobRepository.class).to(PlayerDamageMobRepositoryImpl.class);
    }

    @Provides
    public Plugin plugin() {
        return this.plugin;
    }

}
