package com.boundless.entity.hero_action;

import com.boundless.BoundlessAPI;
import com.boundless.client.RenderParameters;
import com.boundless.registry.RenderLogicRegistry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.Leashable;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.Identifier;

import java.util.function.BiConsumer;

public class HeroActionRenderer extends EntityRenderer<PersistentProjectileEntity> {
    public HeroActionRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(PersistentProjectileEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity instanceof HeroActionEntity heroAction && heroAction.getCustomRenderLogicIdentifier() != null) {
            BiConsumer<HeroActionEntity, RenderParameters> customRenderLogic = RenderLogicRegistry.getRenderLogic(((HeroActionEntity) entity).getCustomRenderLogicIdentifier());
            customRenderLogic.accept(heroAction, new RenderParameters(yaw, tickDelta, matrices, vertexConsumers, light));
        }
    }

    @Override
    public Identifier getTexture(PersistentProjectileEntity entity) {
        return BoundlessAPI.identifier("hero_action");
    }
}
