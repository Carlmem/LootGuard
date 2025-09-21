package com.lootguard.plugin;

import com.google.inject.Guice;
import com.lootguard.api.LootGuardApiProvider;
import com.google.inject.Injector;
import com.lootguard.plugin.configuration.*;
import dev.carlmem.luminaslib.bootstrap.BootstrapGuiceConfiguration;
import dev.carlmem.luminaslib.bootstrap.BootstrapService;
import dev.carlmem.luminaslib.event.EventGuiceConfiguration;
import dev.carlmem.luminaslib.scheduler.SchedulerGuiceConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class LootGuardPlugin extends JavaPlugin implements LootGuardApiProvider {

    private Injector injector;

    private BootstrapService bootstrapService;

    @Override
    public void onEnable() {
        this.injector = Guice.createInjector(
                new LootApiConfiguration(),
                new LootConfiguration(this),
                new BootstrapGuiceConfiguration(this),
                new EventGuiceConfiguration(this),
                new SchedulerGuiceConfiguration(this),
                new SourceConfiguration(this),
                new GlowingConfiguration(this)
        );

        this.bootstrapService = this.injector.getInstance(BootstrapService.class);
    }

    @Override
    public void onDisable() {
        this.bootstrapService.onDisable();
    }
}
