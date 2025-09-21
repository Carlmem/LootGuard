package com.lootguard.plugin.service.glowing;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface EntityGlowingService {

    void glow(Player player, Entity entity);

    void unGlow(Player player, Entity entity);
}
