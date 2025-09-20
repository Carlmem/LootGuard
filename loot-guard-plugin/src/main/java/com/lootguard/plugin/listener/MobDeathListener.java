package com.lootguard.plugin.listener;

import com.lootguard.plugin.service.loot.LootDivisionService;
import com.google.inject.Inject;
import com.rampagemc.ramplagelibrary.event.annotation.EventListener;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

@EventListener
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class MobDeathListener implements Listener {

    private final LootDivisionService lootDivisionService;

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        final var mob = event.getEntity();
        if (mob instanceof Player) {
            return;
        }

        this.lootDivisionService.execute(mob, event.getDrops());
        event.getDrops().clear();
    }

}
