package com.lootguard.plugin.service.glowing;

import fr.skytasul.glowingentities.GlowingEntities;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import javax.inject.Inject;
import java.util.logging.Level;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class EntityGlowingServiceImpl implements EntityGlowingService {

    private final GlowingEntities glowingEntities;

    @Override
    public void glow(Player player, Entity entity) {
        try {
            this.glowingEntities.setGlowing(entity, player, ChatColor.GREEN);
        } catch (ReflectiveOperationException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Failed to glow entity " + entity, e);
        }
    }

    @Override
    public void unGlow(Player player, Entity entity) {
        try {
            this.glowingEntities.unsetGlowing(entity, player);
        } catch (ReflectiveOperationException e) {
            Bukkit.getLogger().log(Level.SEVERE, "Failed to remove glowing entity " + entity, e);
        }
    }
}
