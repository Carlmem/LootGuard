package com.lootguard.plugin.service.loot.handler;

import com.lootguard.plugin.model.ConfigDto;
import com.lootguard.plugin.model.LootDivisionDto;
import com.lootguard.plugin.repository.loot.LootOwnerRepository;
import com.google.inject.Inject;
import com.lootguard.plugin.service.glowing.EntityGlowingService;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class GearLootDropHandler implements LootDropHandler {

    private final ConfigDto configDto;

    private final LootOwnerRepository lootOwnerRepository;

    private final EntityGlowingService entityGlowingService;

    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    @Override
    public void handle(Entity entity, ItemStack itemStack, List<LootDivisionDto> loots) {
        int totalItems = itemStack.getAmount();
        Map<UUID, Integer> finalLoot = new HashMap<>();
        for (int i = 0; i < totalItems; i++) {
            double roll = random.nextDouble();
            double cumulative = 0.0;
            for (final var loot : loots) {
                cumulative += loot.percent();
                if (roll <= cumulative) {
                    final var playerId = loot.playerId();
                    finalLoot.put(playerId, finalLoot.getOrDefault(playerId, 0) + 1);
                    break;
                }
            }
        }

        final var world = entity.getWorld();
        final var location = entity.getLocation();
        finalLoot.forEach((uuid, integer) -> {
            final var itemStackClone = itemStack.clone();
            itemStackClone.setAmount(integer);
            final var item = world.dropItemNaturally(location, itemStackClone);
            final var itemId = item.getUniqueId();
            this.lootOwnerRepository.add(itemId, uuid);

            if (this.configDto.isGlowing()) {
                this.entityGlowingService.glow(Bukkit.getPlayer(uuid), item);
            }
        });
    }
}
