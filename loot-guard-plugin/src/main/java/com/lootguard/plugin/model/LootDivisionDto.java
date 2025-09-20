package com.lootguard.plugin.model;

import java.util.UUID;

public record LootDivisionDto(UUID playerId, double percent) {
}
