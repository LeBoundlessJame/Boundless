package com.boundless.registry;

import com.boundless.BoundlessAPI;
import com.boundless.effect.InvulnerabilityEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class StatusEffectRegistry {
    public static final RegistryEntry<StatusEffect> INVULNERABILITY_EFFECT = registerStatusEffect("invulnerability", new InvulnerabilityEffect(StatusEffectCategory.BENEFICIAL, 0x5d8385));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, BoundlessAPI.identifier(name), statusEffect);
    }

    public static void initialize() {
    }
}
