package com.lootguard.plugin.repository.loot;

import com.lootguard.plugin.model.LootOwnerDto;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;

public interface LootOwnerRepository {

    boolean hasOwner(UUID entityId);

    boolean isOwner(UUID entityId, UUID playerId);

    void add(UUID entityId, UUID ownerId);

    void remove(UUID entityId);

    Collection<Map.Entry<UUID, LootOwnerDto>> getAll();
}
