package com.lootguard.plugin.listener;

import com.lootguard.plugin.repository.loot.LootOwnerRepository;
import com.google.inject.Inject;
import com.rampagemc.ramplagelibrary.event.annotation.EventListener;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

@EventListener
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class TakeDropListener implements Listener {

    private final LootOwnerRepository lootOwnerRepository;

    @EventHandler
    public void onTakeDrop(EntityPickupItemEvent event) {
        final var entityId = event.getEntity().getUniqueId();
        final var itemId = event.getItem().getUniqueId();
        if (!this.lootOwnerRepository.hasOwner(itemId)) {
            return;
        }

        if (!this.lootOwnerRepository.isOwner(itemId, entityId)) {
            event.setCancelled(true);
            return;
        }

        this.lootOwnerRepository.remove(itemId);
    }
}
