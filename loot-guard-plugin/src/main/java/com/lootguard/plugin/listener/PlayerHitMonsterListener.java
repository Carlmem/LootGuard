package com.lootguard.plugin.listener;

import com.lootguard.plugin.model.PlayerLastHitKey;
import com.lootguard.plugin.repository.damage.PlayerDamageMobRepository;
import com.google.inject.Inject;
import com.rampagemc.ramplagelibrary.event.annotation.EventListener;
import lombok.RequiredArgsConstructor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Optional;

@EventListener
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class PlayerHitMonsterListener implements Listener {

    private final PlayerDamageMobRepository playerDamageMobRepository;

    @EventHandler
    public void onEntityHitEntity(EntityDamageByEntityEvent event) {
        final var damager = event.getDamager();
        final var target = event.getEntity();
        if (!(damager instanceof Player) || (target instanceof Player)) {
            return;
        }

        final var health = Optional.of(target)
                .filter(entity -> entity instanceof LivingEntity)
                .map(entity -> (LivingEntity) entity)
                .map(Damageable::getHealth)
                .orElse(1.0);

        this.playerDamageMobRepository.add(target.getUniqueId(), damager.getUniqueId(), Math.min(health, event.getFinalDamage()));
    }
}
