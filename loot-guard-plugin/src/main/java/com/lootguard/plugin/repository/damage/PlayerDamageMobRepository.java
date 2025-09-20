package com.lootguard.plugin.repository.damage;

import com.lootguard.plugin.model.MobDamageDto;

import java.util.Collection;
import java.util.UUID;

public interface PlayerDamageMobRepository {

    void add(UUID entityId, UUID playerId, double damage);

    void removeAll(UUID entityId);

    void remove(UUID entityId, UUID playerId);

    Collection<MobDamageDto> getAllByEntityId(UUID entityId);
}
