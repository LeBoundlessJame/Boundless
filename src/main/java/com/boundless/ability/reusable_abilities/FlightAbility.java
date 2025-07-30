package com.boundless.ability.reusable_abilities;

import com.boundless.BoundlessAPI;
import com.boundless.hero.SuperHero;
import com.boundless.util.AnimationUtils;
import com.boundless.util.DataComponentUtils;
import com.boundless.util.HeroUtils;
import net.minecraft.entity.player.PlayerEntity;

public class FlightAbility {
    public static void flightTickLogic(PlayerEntity player) {
        if (!player.getAbilities().flying || player.isOnGround()) {
            DataComponentUtils.setInt(SuperHero.FLIGHT_TICKS, player, 0);
            HeroUtils.getHeroStack(player).set(SuperHero.FLIGHT_ENABLED, false);
            return;
        }

        HeroUtils.getHeroStack(player).set(SuperHero.FLIGHT_ENABLED, true);
        DataComponentUtils.addOrSubtractInt(SuperHero.FLIGHT_TICKS, player, 1, Integer.MAX_VALUE);
        AnimationUtils.playAnimation(player, BoundlessAPI.identifier("flight_loop"));

        /*
        Vec3d velocity = player.getVelocity();
        Vec3d rotation = player.getRotationVector();

        player.setVelocity(rotation.x, rotation.y, rotation.z);
        player.velocityModified = true;
        player.velocityDirty = true;
        player.onLanding();
         */
    }
}