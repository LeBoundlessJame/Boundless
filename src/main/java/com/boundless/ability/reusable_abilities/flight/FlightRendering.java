package com.boundless.ability.reusable_abilities.flight;

import com.boundless.hero.SuperHero;
import com.boundless.util.HeroUtils;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class FlightRendering {
    public static void hoverRendering(AbstractClientPlayerEntity abstractClientPlayerEntity, MatrixStack matrixStack, float f, float g, float tickDelta, float i, CallbackInfo ci) {
        ItemStack heroStack = HeroUtils.getHeroStack(abstractClientPlayerEntity);

        float pitch = abstractClientPlayerEntity.getPitch(tickDelta);

        Vec3d lerpedVelocity = abstractClientPlayerEntity.lerpVelocity(tickDelta);
        double velocityLength = lerpedVelocity.horizontalLength();
        //abstractClientPlayerEntity.getMovementDirection().getVector();

        abstractClientPlayerEntity.sendMessage(Text.of(String.valueOf(velocityLength)), true);

        float rotationAmount = (float) MathHelper.clamp(velocityLength, 0f, 0.3f);
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(rotationAmount * (-90.0F - pitch)));
    }

    public static void flightRendering(AbstractClientPlayerEntity abstractClientPlayerEntity, MatrixStack matrixStack, float f, float g, float tickDelta, float i, CallbackInfo ci) {
        ItemStack heroStack = HeroUtils.getHeroStack(abstractClientPlayerEntity);

        float pitch = abstractClientPlayerEntity.getPitch(tickDelta);

        long flightBeginTimeStamp = heroStack.getOrDefault(SuperHero.FLIGHT_BEGIN_TIMESTAMP, 0L);
        long flightTicks = abstractClientPlayerEntity.clientWorld.getTime() - flightBeginTimeStamp;

        float l = (float) flightTicks + tickDelta;
        float m = MathHelper.clamp(l * l * l / 100.0F, 0.0F, 1.0F);
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
}
