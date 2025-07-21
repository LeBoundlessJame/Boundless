package com.boundless.util;

import com.boundless.hero.api.HeroArmor;
import com.boundless.hero.api.HeroData;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class HeroUtils {
    public static boolean isHero(PlayerEntity player) {
        return getHeroStack(player).getItem() instanceof HeroArmor;
    }

    public static ItemStack getHeroStack(PlayerEntity player) {
        return player.getEquippedStack(EquipmentSlot.CHEST);
    }

    public static HeroData getHeroData(PlayerEntity player) {
        if (!isHero(player)) return null;
        HeroArmor heroArmor = (HeroArmor) player.getEquippedStack(EquipmentSlot.CHEST).getItem();
        return heroArmor.getHeroData();
    }
}
