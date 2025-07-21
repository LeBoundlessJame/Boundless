package com.boundless.event;

import com.boundless.BoundlessAPI;
import com.boundless.ability.Ability;
import com.boundless.hero.HeroHUD;
import com.boundless.hero.api.HeroData;
import com.boundless.registry.AbilityRegistry;
import com.boundless.registry.DataComponentRegistry;
import com.boundless.util.GUIUtils;
import com.boundless.util.HeroUtils;
import com.boundless.util.KeybindingUtils;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.joml.Math;
import org.joml.Quaternionf;

import java.util.*;

public class HeroHudRenderer {
    public static void register() {
        HudRenderCallback.EVENT.register(((drawContext, renderTickCounter) -> {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            if (minecraftClient == null || minecraftClient.player == null || !HeroUtils.isHero(minecraftClient.player)) return;
            HeroData heroData = HeroUtils.getHeroData(minecraftClient.player);
            if (heroData == null) return;
            if (heroData.getHudRenderer() != null) {
                heroData.getHudRenderer().accept(drawContext, renderTickCounter);
            } else {
                HeroHUD.render(drawContext, renderTickCounter);
            }
        }));
    }
}
