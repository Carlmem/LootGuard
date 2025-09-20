package com.lootguard.plugin.bootstrap;

import com.google.inject.Injector;
import com.lootguard.plugin.scheduler.LootScheduler;
import com.rampagemc.ramplagelibrary.bootstrap.BootstrapInitializer;
import com.rampagemc.ramplagelibrary.bootstrap.annotation.Bootstrap;
import com.rampagemc.ramplagelibrary.scheduler.Scheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import javax.inject.Inject;

@Bootstrap
public class LootBootstrap implements BootstrapInitializer {

    @Inject
    public LootBootstrap(Scheduler scheduler, LootScheduler lootScheduler) {
        scheduler.runTimerSeconds(lootScheduler::execute, 1L);
    }

    @Override
    public void initialize(Plugin plugin, Injector injector) throws Exception {
    }

    @Override
    public String getDescription() {
        return "LootBootstrap";
    }
}
