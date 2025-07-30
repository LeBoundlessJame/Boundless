package com.boundless.registry;

import com.boundless.client.RenderParameters;
import com.boundless.entity.hero_action.HeroActionEntity;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class RenderLogicRegistry {
    public static HashMap<String, BiConsumer<HeroActionEntity, RenderParameters>> CUSTOM_RENDER_LOGIC = new HashMap<>();

    public static void addRenderEntry(String ID, BiConsumer<HeroActionEntity, RenderParameters> entry) {
        CUSTOM_RENDER_LOGIC.put(ID, entry);
    }

    public static BiConsumer<HeroActionEntity, RenderParameters> getRenderEntry(String ID) {
        return CUSTOM_RENDER_LOGIC.get(ID);
    }

    public static void initialize() {}
}
