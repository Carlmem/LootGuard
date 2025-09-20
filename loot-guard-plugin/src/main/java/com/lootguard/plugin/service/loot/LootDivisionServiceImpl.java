package com.lootguard.plugin.service.loot;

import com.lootguard.plugin.model.LootDivisionDto;
import com.lootguard.plugin.repository.damage.PlayerDamageMobRepositoryImpl;
import com.lootguard.plugin.service.loot.handler.LootDropHandler;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class LootDivisionServiceImpl implements LootDivisionService {

    private final LootDropHandler lootDropHandler;

    private final PlayerDamageMobRepositoryImpl playerDamageMobRepository;

    @Override
    public void execute(LivingEntity entity, List<ItemStack> items) {
        final var entityId = entity.getUniqueId();
        final var damages = this.playerDamageMobRepository.getAllByEntityId(entityId);
        if (damages.isEmpty()) {
            return;
        }

        var loots = new ArrayList<LootDivisionDto>();
        for (final var damage : damages) {
            loots.add(new LootDivisionDto(damage.getPlayerId(), damage.getDamage() / entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()));
        }

        loots.sort(Comparator.comparingDouble(LootDivisionDto::percent));
        for (final var item : items) {
            this.lootDropHandler.handle(entity, item, loots);
        }

        this.playerDamageMobRepository.removeAll(entityId);
    }
}
