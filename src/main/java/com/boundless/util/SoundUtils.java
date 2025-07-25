package com.boundless.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;

public class SoundUtils {
    public static void playSound(PlayerEntity player, SoundEvent sound) {
        if (!player.getWorld().isClient) {
            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(), sound, SoundCategory.PLAYERS, 1.0f, 1.0f);
        }
    }
}
