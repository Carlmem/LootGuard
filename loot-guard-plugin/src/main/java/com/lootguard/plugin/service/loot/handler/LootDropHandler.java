package com.lootguard.plugin.service.loot.handler;

import com.lootguard.plugin.model.LootDivisionDto;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface LootDropHandler {

    void handle(Entity entity, ItemStack itemStack, List<LootDivisionDto> loots);
}
