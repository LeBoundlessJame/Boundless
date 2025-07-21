package com.boundless.entity.hero_action;

import com.boundless.BoundlessAPI;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.Identifier;

public class HeroActionRenderer extends EntityRenderer<PersistentProjectileEntity> {
    public HeroActionRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(PersistentProjectileEntity entity) {
        return BoundlessAPI.identifier("hero_action");
    }
}
