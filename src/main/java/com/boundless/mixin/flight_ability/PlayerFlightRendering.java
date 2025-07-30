package com.boundless.mixin.flight_ability;

import com.boundless.hero.SuperHero;
import com.boundless.util.DataComponentUtils;
import com.boundless.util.HeroUtils;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntityRenderer.class)
public class PlayerFlightRendering {
    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isFallFlying()Z"),  method = "setupTransforms(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/util/math/MatrixStack;FFFF)V")
    protected boolean boundless$flightRotation(boolean original, AbstractClientPlayerEntity abstractClientPlayerEntity, MatrixStack matrixStack, float f, float g, float h, float i) {
        boolean shouldRotate = HeroUtils.getHeroStack(abstractClientPlayerEntity).getOrDefault(SuperHero.FLIGHT_ENABLED, false);
        shouldRotate &= DataComponentUtils.getInt(SuperHero.FLIGHT_TICKS, abstractClientPlayerEntity, 0) > 0;
        return original || shouldRotate;
    }

    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;getFallFlyingTicks()I"),  method = "setupTransforms(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/util/math/MatrixStack;FFFF)V")
    protected int boundless$useFlightTicks(int original, AbstractClientPlayerEntity abstractClientPlayerEntity, MatrixStack matrixStack, float f, float g, float h, float i) {
        if (!HeroUtils.getHeroStack(abstractClientPlayerEntity).getOrDefault(SuperHero.FLIGHT_ENABLED, false)) return original;
        return DataComponentUtils.getInt(SuperHero.FLIGHT_TICKS, abstractClientPlayerEntity, 0);
    }
}