package com.boundless.hero;

import com.boundless.ability.AbilityLoadout;
import com.boundless.ability.reusable_abilities.FlightAbility;
import com.boundless.ability.reusable_abilities.MeleeCombatAbilities;
import com.boundless.hero.api.Hero;
import com.boundless.hero.api.HeroData;
import com.boundless.registry.DataComponentRegistry;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;

public class SuperHero extends Hero {
    public static ComponentType<Integer> FLIGHT_TICKS = DataComponentRegistry.registerComponent("flight_ticks", builder -> ComponentType.<Integer>builder().codec(Codec.INT));
    public static ComponentType<Boolean> FLIGHT_ENABLED = DataComponentRegistry.registerComponent("flight_enabled", builder -> ComponentType.<Boolean>builder().codec(Codec.BOOL));

    public SuperHero() {
        AbilityLoadout loadout = AbilityLoadout.builder()
                .ability("key.attack", MeleeCombatAbilities.JAB)
                .build();

        ABILITY_LOADOUTS.put("LOADOUT_1", loadout);

        this.heroData = HeroData
                .builder()
                .name("super_hero")
                .displayName("Super Hero")
                .tickHandler(FlightAbility::flightTickLogic)
                .defaultAbilityLoadout(ABILITY_LOADOUTS.get("LOADOUT_1"))
                .build();

        this.registerHero();
    }
}