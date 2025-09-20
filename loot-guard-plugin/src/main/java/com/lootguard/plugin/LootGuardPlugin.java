package com.lootguard.plugin;

import com.google.inject.Guice;
import com.lootguard.api.LootGuardApiProvider;
import com.google.inject.Injector;
import com.lootguard.plugin.configuration.*;
import com.rampagemc.ramplagelibrary.bootstrap.BootstrapGuiceConfiguration;
import com.rampagemc.ramplagelibrary.bootstrap.BootstrapService;
import com.rampagemc.ramplagelibrary.event.EventGuiceConfiguration;
import com.rampagemc.ramplagelibrary.scheduler.SchedulerGuiceConfiguration;
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
                new SourceConfiguration(this)
        );

        this.bootstrapService = this.injector.getInstance(BootstrapService.class);
    }

    @Override
    public void onDisable() {
        this.bootstrapService.onDisable();
    }
}
