package com.boundless.ability.reusable_abilities;

import com.boundless.BoundlessAPI;
import com.boundless.hero.SuperHero;
import com.boundless.util.DataComponentUtils;
import com.boundless.util.EffekUtils;
import com.boundless.util.HeroUtils;
import com.boundless.util.SoundUtils;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;

public class FlightAbility {

    public static void flightTick(PlayerEntity player) {
        if (player.getWorld().isClient) return;
        if (!player.getAbilities().flying) {
            HeroUtils.getHeroStack(player).set(SuperHero.FLIGHT_ENABLED, false);
            DataComponentUtils.setInt(SuperHero.FLIGHT_TICKS, player, 0);
            return;
        }

        if (player.isSprinting()) {
            HeroUtils.getHeroStack(player).set(SuperHero.FLIGHT_ENABLED, true);
            DataComponentUtils.addOrSubtractInt(SuperHero.FLIGHT_TICKS, player, 1, Integer.MAX_VALUE);

            if (DataComponentUtils.getInt(SuperHero.FLIGHT_TICKS, player, 0) == 1) {
                HeroUtils.getHeroStack(player).set(SuperHero.FLIGHT_BEGIN_TIMESTAMP, player.getWorld().getTime());
                boostLogic(player);
            }

            flightMovement(player);
        } else {
            HeroUtils.getHeroStack(player).set(SuperHero.FLIGHT_ENABLED, false);
            DataComponentUtils.setInt(SuperHero.FLIGHT_TICKS, player, 0);
        }
    }

    public static void flightMovement(PlayerEntity player) {
        Vec3d rotation = player.getRotationVector();
        player.setVelocity(rotation.x, rotation.y, rotation.z);
        player.velocityModified = true;
        player.velocityDirty = true;
        player.onLanding();
    }

    public static void boostLogic(PlayerEntity player) {
        SoundUtils.playSound(player, SoundEvents.ITEM_FIRECHARGE_USE, 1.0f);
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 5, 0, true, false, false));

        Vec3d playerRotation = player.getRotationVector();
        Vec3d effectPos = player.getPos().add(playerRotation.normalize().multiply(-player.getWidth()).x, 0.5f, playerRotation.normalize().multiply(-player.getWidth() ).z);
        Vec3d effectScale = new Vec3d(player.getScale() * 0.5f, player.getScale() * 0.5f, player.getScale() * 0.5f);
        Vec3d effectRotation = new Vec3d(player.getPitch(), player.getYaw() * -1, 0);
        EffekUtils.playRotatedEffect(BoundlessAPI.identifier("flight_boost"), player, effectPos, effectScale, effectRotation);
    }
}