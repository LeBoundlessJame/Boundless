package com.boundless.mixin.flight_ability;

import com.boundless.hero.SuperHero;
import com.boundless.util.HeroUtils;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerFlightRendering extends LivingEntityRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    public PlayerFlightRendering(EntityRendererFactory.Context ctx, PlayerEntityModel<AbstractClientPlayerEntity> model, float shadowRadius) {
        super(ctx, model, shadowRadius);
    }

    @Inject(at = @At(value = "HEAD"), method = "setupTransforms(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/util/math/MatrixStack;FFFF)V", cancellable = true)
    protected void boundless$flightTransforms(AbstractClientPlayerEntity abstractClientPlayerEntity, MatrixStack matrixStack, float f, float g, float tickDelta, float i, CallbackInfo ci) {
        ItemStack heroStack = HeroUtils.getHeroStack(abstractClientPlayerEntity);
        if (!heroStack.getOrDefault(SuperHero.FLIGHT_ENABLED, false)) return;
        render(abstractClientPlayerEntity, matrixStack, f, g, tickDelta, i, ci);
        ci.cancel();
    }

    @Unique
    public void render(AbstractClientPlayerEntity abstractClientPlayerEntity, MatrixStack matrixStack, float f, float g, float tickDelta, float i, CallbackInfo ci) {
        ItemStack heroStack = HeroUtils.getHeroStack(abstractClientPlayerEntity);
        super.setupTransforms(abstractClientPlayerEntity, matrixStack, f, g, tickDelta, i);

        float pitch = abstractClientPlayerEntity.getPitch(tickDelta);

        long flightBeginTimeStamp = heroStack.getOrDefault(SuperHero.FLIGHT_BEGIN_TIMESTAMP, 0L);
        long flightTicks = abstractClientPlayerEntity.clientWorld.getTime() - flightBeginTimeStamp;

        float l = (float) flightTicks + tickDelta;
        float m = MathHelper.clamp(l * l / 100.0F, 0.0F, 1.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(m * (-90.0F - pitch)));

        Vec3d rotation = abstractClientPlayerEntity.getRotationVec(tickDelta);
        Vec3d lerpedVelocity = abstractClientPlayerEntity.lerpVelocity(tickDelta);
        double d = lerpedVelocity.horizontalLengthSquared();
        double e = rotation.horizontalLengthSquared();

        if (d > 0.0 && e > 0.0) {
            double n = (lerpedVelocity.x * rotation.x + lerpedVelocity.z * rotation.z) / Math.sqrt(d * e);
            double o = lerpedVelocity.x * rotation.z - lerpedVelocity.z * rotation.x;
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotation((float)(Math.signum(o) * Math.acos(n))));
        }
    }


    /*
    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;isFallFlying()Z"), method = "setupTransforms(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/util/math/MatrixStack;FFFF)V")
    protected boolean boundless$flightCondition(boolean original, AbstractClientPlayerEntity abstractClientPlayerEntity, MatrixStack matrixStack, float f, float g, float tickDelta, float i) {
        ItemStack heroStack = HeroUtils.getHeroStack(abstractClientPlayerEntity);
        return original || heroStack.getOrDefault(SuperHero.FLIGHT_ENABLED, false);
    }

    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;getFallFlyingTicks()I"), method = "setupTransforms(Lnet/minecraft/client/network/AbstractClientPlayerEntity;Lnet/minecraft/client/util/math/MatrixStack;FFFF)V")
    protected int boundless$flightTicks(int original, AbstractClientPlayerEntity abstractClientPlayerEntity, MatrixStack matrixStack, float f, float g, float tickDelta, float i) {
        ItemStack heroStack = HeroUtils.getHeroStack(abstractClientPlayerEntity);
        if (heroStack.getOrDefault(SuperHero.FLIGHT_ENABLED, false)) {
            return heroStack.getOrDefault(SuperHero.FLIGHT_TICKS, 0);
        }
        return original;
    }

     */
}