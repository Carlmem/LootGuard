package com.lootguard.plugin.repository.damage;

import com.lootguard.plugin.model.MobDamageDto;
import com.google.inject.Singleton;

import java.util.*;

@Singleton
public class PlayerDamageMobRepositoryImpl implements PlayerDamageMobRepository {

    private final Map<UUID, Map<UUID, MobDamageDto>> playerDamageMobs = new HashMap<>();

    @Override
    public void add(UUID entityId, UUID playerId, double damage) {
        final var damages = this.playerDamageMobs.getOrDefault(entityId, new HashMap<>());
        final var mobDamage = damages.getOrDefault(playerId, new MobDamageDto(playerId));
        mobDamage.setDamage(mobDamage.getDamage() + damage);
        damages.put(playerId, mobDamage);
        this.playerDamageMobs.put(entityId, damages);
    }

    @Override
    public void removeAll(UUID entityId) {
        this.playerDamageMobs.remove(entityId);
    }

    @Override
    public void remove(UUID entityId, UUID playerId) {
        this.playerDamageMobs.get(entityId).remove(playerId);
    }

    @Override
    public Collection<MobDamageDto> getAllByEntityId(UUID entityId) {
        return Optional.ofNullable(this.playerDamageMobs.get(entityId)).orElse(Collections.emptyMap())
                .values();
    }
}
