package com.boundless.entity;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class ModEntityDimensions {

    @Getter
    @Setter
    float widthX;
    @Getter
    @Setter
    float height;
    @Getter
    @Setter
    float widthZ;

    public ModEntityDimensions(float widthX, float height, float widthZ) {
        this.widthX = widthX;
        this.height = height;
        this.widthZ = widthZ;
    }

    public static Box getBoxAt(ModEntityDimensions dimensions, Vec3d pos) {
        return getBoxAt(dimensions, pos.getX(), pos.getY(), pos.getZ());
    }

    public static Box getBoxAt(ModEntityDimensions dimensions, double xPos, double yPos, double zPos) {
        float widthX = dimensions.getWidthX() / 2.0F;
        float height = dimensions.getHeight();
        float widthZ = dimensions.getWidthZ() / 2.0F;

        return new ModdedBox(xPos - widthX, yPos, zPos - widthZ, xPos + widthX, yPos + height, zPos + widthZ);
    }

    public static Box getOrientedBoxAt(Vec3d origin, Vec3d forward, float widthX, float height, float widthZ) {
        Vec3d end = origin.add(forward.multiply(widthX));

        double minX = Math.min(origin.x, end.x) - widthX / 2;
        double maxX = Math.max(origin.x, end.x) + widthX / 2;
        double minY = origin.y;
        double maxY = origin.y + height;
        double minZ = Math.min(origin.z, end.z) - widthZ / 2;
        double maxZ = Math.max(origin.z, end.z) + widthZ / 2;

        return new Box(minX, minY, minZ, maxX, maxY, maxZ);
    }

}
