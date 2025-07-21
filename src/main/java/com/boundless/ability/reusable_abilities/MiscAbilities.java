package com.boundless.ability.reusable_abilities;

import com.boundless.registry.DataComponentRegistry;
import com.boundless.util.HeroUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class MiscAbilities {
    public static void toggleMechanics(PlayerEntity player) {
        ItemStack stack = HeroUtils.getHeroStack(player);
        stack.set(DataComponentRegistry.VANILLA_MECHANICS, !stack.getOrDefault(DataComponentRegistry.VANILLA_MECHANICS, true));
    }
}
