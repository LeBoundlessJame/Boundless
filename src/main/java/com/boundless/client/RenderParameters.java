package com.boundless.client;

import lombok.Getter;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;

@Getter
public class RenderParameters {
    public float yaw;
    public float tickDelta;
    public MatrixStack matrices;
    public VertexConsumerProvider vertexConsumerProvider;
    public int light;

    public RenderParameters(float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        this.yaw = yaw;
        this.tickDelta = tickDelta;
        this.matrices = matrices;
        this.vertexConsumerProvider = vertexConsumers;
        this.light = light;
    }
}
