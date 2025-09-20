package com.lootguard.plugin.service.loot;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public interface LootDivisionService {

    void execute(LivingEntity entity, List<ItemStack> items);
}
