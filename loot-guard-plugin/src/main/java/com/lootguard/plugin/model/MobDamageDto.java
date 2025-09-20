package com.lootguard.plugin.model;

import lombok.Data;

import java.util.UUID;

@Data
public class MobDamageDto {

    private final UUID playerId;

    private double damage;
}
