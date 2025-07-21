package com.boundless.ability.combat;

import com.boundless.util.CombatUtils;
import com.boundless.util.HeroUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import static com.boundless.registry.DataComponentRegistry.ATTACK_END;
import static com.boundless.registry.DataComponentRegistry.ATTACK_START;

public class CombatSystem {
    /*
    public static void triggerAttack(PlayerEntity player, Attack attack, int duration) {
        CombatSystem.startAttackTimer(player, duration);
        CombatUtils.performAction(player, attack, duration);
    }
     */

    public static void startAttackTimer(PlayerEntity player, long duration) {
        ItemStack heroStack = HeroUtils.getHeroStack(player);
        heroStack.set(ATTACK_START, player.getWorld().getTime());
        heroStack.set(ATTACK_END, heroStack.getOrDefault(ATTACK_START, 0L) + duration);
    }
}
