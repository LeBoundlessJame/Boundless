package com.boundless.mixin.flight_ability;

import com.boundless.hero.SuperHero;
import com.boundless.util.HeroUtils;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public class FlightConditionalMixin {
    @ModifyReturnValue(at = @At("RETURN"), method = "isFallFlying")
    public boolean boundless$fallFlying(boolean original) {
        if ((LivingEntity) (Object) this instanceof PlayerEntity player) return original || HeroUtils.getHeroStack(player).getOrDefault(SuperHero.FLIGHT_ENABLED, false);
        return original;
    }
}
