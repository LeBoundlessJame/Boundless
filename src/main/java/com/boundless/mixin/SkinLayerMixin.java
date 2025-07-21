package com.boundless.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerModelPart;
import net.minecraft.item.Equipment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.entity.EquipmentSlot.*;

@Mixin(LivingEntity.class)
public abstract class SkinLayerMixin {
    @Shadow protected abstract boolean isArmorSlot(EquipmentSlot slot);

    @Inject(method = "onEquipStack", at = @At("HEAD"))
    public void boundless$onEquipStack(EquipmentSlot slot, ItemStack oldStack, ItemStack newStack, CallbackInfo ci) {
        Equipment equipment = Equipment.fromStack(newStack);

        if (this.isArmorSlot(slot)) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client == null) return;
            boolean enabled = equipment == null;

            if (slot.equals(CHEST)) {
                client.options.togglePlayerModelPart(PlayerModelPart.JACKET, enabled);
                client.options.togglePlayerModelPart(PlayerModelPart.LEFT_SLEEVE, enabled);
                client.options.togglePlayerModelPart(PlayerModelPart.RIGHT_SLEEVE, enabled);
            } else if (slot.equals(HEAD)) {
                client.options.togglePlayerModelPart(PlayerModelPart.HAT, enabled);
            } else if (slot.equals(LEGS)) {
                client.options.togglePlayerModelPart(PlayerModelPart.LEFT_PANTS_LEG, enabled);
                client.options.togglePlayerModelPart(PlayerModelPart.RIGHT_PANTS_LEG, enabled);
            } else if (slot.equals(FEET)) {
                client.options.togglePlayerModelPart(PlayerModelPart.LEFT_PANTS_LEG, enabled);
                client.options.togglePlayerModelPart(PlayerModelPart.RIGHT_PANTS_LEG, enabled);
            }
        }
    }

}
