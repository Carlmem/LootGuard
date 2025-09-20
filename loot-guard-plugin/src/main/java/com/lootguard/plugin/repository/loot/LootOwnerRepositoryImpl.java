package com.lootguard.plugin.repository.loot;

import com.lootguard.plugin.model.LootOwnerDto;
import com.google.inject.Singleton;

import java.util.*;

@Singleton
public class LootOwnerRepositoryImpl implements LootOwnerRepository {

    private final Map<UUID, LootOwnerDto> lootOwners = new HashMap<>();

    @Override
    public boolean hasOwner(UUID entityId) {
        return this.lootOwners.containsKey(entityId);
    }

    @Override
    public boolean isOwner(UUID entityId, UUID playerId) {
        return Optional.ofNullable(this.lootOwners.get(entityId))
                .filter(lootOwnerDto -> lootOwnerDto.ownerId().equals(playerId)).isPresent();
    }

    @Override
    public void add(UUID entityId, UUID ownerId) {
        this.lootOwners.put(entityId, new LootOwnerDto(ownerId, System.currentTimeMillis()));
    }

    @Override
    public void remove(UUID entityId) {
        this.lootOwners.remove(entityId);
    }

    @Override
    public Collection<Map.Entry<UUID, LootOwnerDto>> getAll() {
        return new ArrayList<>(this.lootOwners.entrySet());
    }
}
