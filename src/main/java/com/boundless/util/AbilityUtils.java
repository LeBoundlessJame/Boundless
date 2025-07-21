package com.boundless.util;

import com.boundless.BoundlessAPI;
import com.boundless.ability.Ability;
import com.boundless.ability.AbilityLoadout;
import com.boundless.registry.AbilityRegistry;
import com.boundless.registry.DataComponentRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AbilityUtils {

    public static void setAbilityCooldown(PlayerEntity player, Identifier abilityID, long cooldownTime) {
        ItemStack heroStack = player.getEquippedStack(EquipmentSlot.CHEST);
        Map<Identifier, Long> updatedCooldownData = DataComponentUtils.updatedCooldownMap(heroStack, abilityID, player.getWorld().getTime() + cooldownTime);
        heroStack.set(DataComponentRegistry.COOLDOWN_DATA, updatedCooldownData);
    }

    public static boolean canUseAbility(PlayerEntity player, Identifier abilityID) {
        ItemStack heroStack = player.getEquippedStack(EquipmentSlot.CHEST);
        Map<Identifier, Long> cooldownData = heroStack.getOrDefault(DataComponentRegistry.COOLDOWN_DATA, Map.of());

        boolean abilityUsable = player.getWorld().getTime() > cooldownData.getOrDefault(abilityID, 0L);
        Predicate<PlayerEntity> abilityPredicate = AbilityRegistry.getAbilityFromID(abilityID).getAbilityConditional();
        abilityUsable &= abilityPredicate == null || abilityPredicate.test(player);

        return abilityUsable;
    }

    public static Identifier abilityIDFromKeybind(PlayerEntity player, String keybindTranslation) {
        if (!HeroUtils.isHero(player)) return null;
        ItemStack stack = HeroUtils.getHeroStack(player);
        Map<String, Identifier> abilities = stack.getOrDefault(DataComponentRegistry.ABILITY_LOADOUT, Map.of());
        return abilities.get(keybindTranslation);
    }

    public static boolean checkAndUseAbility(PlayerEntity player, Identifier abilityID) {
        if (!HeroUtils.isHero(player)) return false;

        Ability ability = AbilityRegistry.getAbilityFromID(abilityID);
        if (ability == null) return false;
        Consumer<PlayerEntity> abilityConsumer = ability.getAbilityConsumer();

        if (abilityConsumer != null && canUseAbility(player, abilityID)) {
            abilityConsumer.accept(player);
            if (!player.getWorld().isClient) {
                long cooldown = ability.getCooldown();
                setAbilityCooldown(player, abilityID, cooldown);
            }
            return true;
        }
        return false;
    }

    public static void assignAbilityLoadout(PlayerEntity player, Map<String, Identifier> abilities) {
        if (!HeroUtils.isHero(player)) return;
        HeroUtils.getHeroStack(player).set(DataComponentRegistry.ABILITY_LOADOUT, abilities);
    }

    public static void assignAbilityLoadout(PlayerEntity player, AbilityLoadout loadout) {
        if (!HeroUtils.isHero(player)) return;
        LinkedHashMap<String, Identifier> abilities = new LinkedHashMap();
        for (Map.Entry<String, Ability> entry: loadout.getAbilities().entrySet()) {
            abilities.put(entry.getKey(), entry.getValue().getAbilityID());
        }
        HeroUtils.getHeroStack(player).set(DataComponentRegistry.ABILITY_LOADOUT, abilities);
    }

    public static Ability abilityWithIcon(String name, String iconName, Consumer<PlayerEntity> abilityLogic, int cooldown) {
        return Ability.builder().abilityConsumer(abilityLogic)
                .cooldown(cooldown)
                .abilityID(BoundlessAPI.identifier(name))
                .abilityIcon(BoundlessAPI.hudPNG(iconName))
                .build();
    }

    public static Ability abilityWithIcon(String name, Consumer<PlayerEntity> abilityLogic, int cooldown) {
        return AbilityUtils.abilityWithIcon(name, name, abilityLogic, cooldown);
    }
}