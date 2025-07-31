package com.boundless.ability.reusable_abilities.flight;

import com.boundless.hero.SuperHero;
import com.boundless.util.HeroUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.datafixer.fix.ChunkPalettedStorageFix;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public class FlightRendering {

    public static void clientInputLogic(MinecraftClient client) {
        if (client.player == null) return;

        if (client.options.forwardKey.isPressed()) {
            client.player.sendMessage(Text.of("Very nice! Great success!"));
        }
    }

    public static void hoverRendering(AbstractClientPlayerEntity abstractClientPlayerEntity, MatrixStack matrixStack, float f, float g, float tickDelta, float i, CallbackInfo ci) {
        String flightDirection = HeroUtils.getHeroStack(abstractClientPlayerEntity).getOrDefault(SuperHero.FLIGHT_DIRECTION, "forward");
        float pitch = abstractClientPlayerEntity.getPitch(tickDelta);

        Vec3d lerpedVelocity = abstractClientPlayerEntity.lerpVelocity(tickDelta);
        double velocityLength = lerpedVelocity.horizontalLength();
        float rotationAmount = (float) MathHelper.clamp(velocityLength, 0, 0.3f);
        rotationAmount *= flightDirection.equals("forward") ? 1 : -1;
        float degrees = rotationAmount * (-90.0F - pitch);

        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(degrees));
    }

    public static void flightRendering(AbstractClientPlayerEntity abstractClientPlayerEntity, MatrixStack matrixStack, float f, float g, float tickDelta, float i, CallbackInfo ci) {
        ItemStack heroStack = HeroUtils.getHeroStack(abstractClientPlayerEntity);
//abstractClientPlayerEntity.sendMessage(Text.of(String.valueOf(abstractClientPlayerEntity.getVelocity())), true);

        float pitch = abstractClientPlayerEntity.getPitch(tickDelta);


        /*
        if (abstractClientPlayerEntity.isSprinting()) {
            ItemStack heroStack = HeroUtils.getHeroStack(abstractClientPlayerEntity);
            long flightBeginTimeStamp = heroStack.getOrDefault(SuperHero.FLIGHT_BEGIN_TIMESTAMP, 0L);
            long flightTicks = abstractClientPlayerEntity.clientWorld.getTime() - flightBeginTimeStamp;

            float l = (float) flightTicks + tickDelta;
            // Easing! maybe add initial rotation amount to l too
            rotationAmount = MathHelper.clamp(l * l * l / 100.0F, 0.0F, 1.0F);
        }
         */


        /*
        long flightBeginTimeStamp = heroStack.getOrDefault(SuperHero.FLIGHT_BEGIN_TIMESTAMP, 0L);
        long flightTicks = abstractClientPlayerEntity.clientWorld.getTime() - flightBeginTimeStamp;

        float l = (float) flightTicks + tickDelta;
        float m = MathHelper.clamp(l * l * l / 100.0F, 0.0F, 1.0F);
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(m * (-90.0F - pitch)));

         */

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
