package com.boundless.hero.api;

import com.boundless.BoundlessAPI;
import com.boundless.ability.Ability;
import com.boundless.ability.AbilityLoadout;
import com.boundless.registry.AbilityRegistry;
import com.boundless.registry.HeroRegistry;
import com.boundless.util.RegistryUtils;
import lombok.Getter;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Hero {
    @Getter
    ArrayList<Item> armorSet;
    @Getter
    public HeroData heroData;
    @Getter
    public static LinkedHashMap<String, AbilityLoadout> ABILITY_LOADOUTS = new LinkedHashMap<>();

    public void registerHero() {
        this.armorSet = RegistryUtils.registerHero(this);

        if (!ABILITY_LOADOUTS.isEmpty()) {
            for (AbilityLoadout loadout : ABILITY_LOADOUTS.values()) {
                for (Map.Entry<String, Ability> abilityEntry : loadout.getAbilities().entrySet()) {
                    Ability ability = abilityEntry.getValue();
                    if (!AbilityRegistry.ABILITIES.containsKey(ability.getAbilityID())) {
                        BoundlessAPI.LOGGER.info("Registered " + ability.getAbilityID() + " ability");
                    }
                    AbilityRegistry.ABILITIES.putIfAbsent(ability.getAbilityID(), abilityEntry.getValue());
                }
            }
        }

        HeroRegistry.HEROES.add(this);
        BoundlessAPI.LOGGER.info("Registered " + this.heroData.getName());
    }
}
