package com.boundless.registry;

import com.boundless.entity.hero_action.HeroActionRenderer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class EntityRenderRegistry {
    public static void initialize() {
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.HERO_ACTION_ENTITY, HeroActionRenderer::new);
    }
}
