package com.lootguard.plugin.scheduler;

import com.lootguard.plugin.repository.loot.LootOwnerRepository;
import com.google.inject.Inject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class LootScheduler {

    private final LootOwnerRepository lootOwnerRepository;

    public void execute() {
        final var now = System.currentTimeMillis();
        this.lootOwnerRepository.getAll().stream()
                .filter(entry -> now - entry.getValue().start() >= 10000)
                .forEach(entry -> {
                    final var key = entry.getKey();
                    this.lootOwnerRepository.remove(key);
                });
    }
}
