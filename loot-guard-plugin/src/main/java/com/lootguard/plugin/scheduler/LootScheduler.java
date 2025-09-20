package com.lootguard.plugin.scheduler;

import com.lootguard.plugin.model.ConfigDto;
import com.lootguard.plugin.repository.loot.LootOwnerRepository;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class LootScheduler {

    private final ConfigDto configDto;

    private final LootOwnerRepository lootOwnerRepository;

    public void execute() {
        final var now = System.currentTimeMillis();
        this.lootOwnerRepository.getAll().stream()
                .filter(entry -> now - entry.getValue().start() >= this.configDto.getProtectedTime() * 1000L)
                .forEach(entry -> {
                    final var key = entry.getKey();
                    this.lootOwnerRepository.remove(key);
                });
    }
}
