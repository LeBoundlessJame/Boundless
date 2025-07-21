package com.boundless.hero.armor;

import mod.azure.azurelib.rewrite.render.armor.AzArmorRenderer;
import mod.azure.azurelib.rewrite.render.armor.AzArmorRendererConfig;
import net.minecraft.util.Identifier;

public class HeroArmorRenderer extends AzArmorRenderer {

    public HeroArmorRenderer(Identifier model, Identifier texture) {
        super(AzArmorRendererConfig.builder(model, texture).build());
    }
}
