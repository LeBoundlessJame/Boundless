package com.boundless.ability.reusable_abilities;

import com.boundless.BoundlessAPI;
import com.boundless.hero.SuperHero;
import com.boundless.util.AnimationUtils;
import com.boundless.util.DataComponentUtils;
import com.boundless.util.HeroUtils;
import com.boundless.util.SoundUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

public class FlightAbility {
    public static void flightTickLogic(PlayerEntity player) {
        if (!player.getAbilities().flying || player.isOnGround() || player.getWorld().isClient) return;

        if (player.isSprinting()) {
            HeroUtils.getHeroStack(player).set(SuperHero.FLIGHT_ENABLED, true);
            DataComponentUtils.addOrSubtractInt(SuperHero.FLIGHT_TICKS, player, 1, Integer.MAX_VALUE);
            if (DataComponentUtils.getInt(SuperHero.FLIGHT_TICKS, player, 0) == 1) {
                SoundUtils.playSound(player, SoundEvents.ITEM_FIRECHARGE_USE);
            }

            Vec3d rotation = player.getRotationVector();
            player.setVelocity(rotation.x, rotation.y, rotation.z);
            player.velocityModified = true;
            player.velocityDirty = true;
            player.onLanding();
        } else {
            DataComponentUtils.setInt(SuperHero.FLIGHT_TICKS, player, 0);
            HeroUtils.getHeroStack(player).set(SuperHero.FLIGHT_ENABLED, false);
        }
    }
}