package com.boundless.registry;

import com.boundless.client.RenderParameters;
import com.boundless.entity.hero_action.HeroActionEntity;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class RenderLogicRegistry {
    private static final HashMap<Identifier, BiConsumer<HeroActionEntity, RenderParameters>> CUSTOM_RENDER_LOGIC = new HashMap<>();
    public static void register(Identifier id, BiConsumer<HeroActionEntity, RenderParameters> logic) {
        CUSTOM_RENDER_LOGIC.put(id, logic);
    }

    public static BiConsumer<HeroActionEntity, RenderParameters> getRenderLogic(Identifier id) {
        return CUSTOM_RENDER_LOGIC.get(id);
    }

    public static void initialize() {}
}
