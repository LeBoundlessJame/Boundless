package com.boundless.entity.hero_action;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class HeroActionModel extends EntityModel<HeroActionEntity> {
    public HeroActionModel(Function<Identifier, RenderLayer> layerFactory) {
        super(layerFactory);
    }

    @Override
    public void setAngles(HeroActionEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {}

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {}
}
