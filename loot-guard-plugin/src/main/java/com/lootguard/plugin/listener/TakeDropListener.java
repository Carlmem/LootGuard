package com.lootguard.plugin.listener;

import com.lootguard.plugin.model.ConfigDto;
import com.lootguard.plugin.repository.loot.LootOwnerRepository;
import com.google.inject.Inject;
import dev.carlmem.luminaslib.event.annotation.EventListener;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

@EventListener
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class TakeDropListener implements Listener {

    private final ConfigDto configDto;

    private final LootOwnerRepository lootOwnerRepository;

    @EventHandler
    public void onTakeDrop(EntityPickupItemEvent event) {
        final var entity = event.getEntity();
        final var entityId = entity.getUniqueId();
        final var itemId = event.getItem().getUniqueId();
        if (!this.lootOwnerRepository.hasOwner(itemId)) {
            return;
        }

        if (!this.lootOwnerRepository.isOwner(itemId, entityId) && !entity.hasPermission(this.configDto.getLootPermission())) {
            event.setCancelled(true);
            return;
        }

        this.lootOwnerRepository.remove(itemId);
    }
}
