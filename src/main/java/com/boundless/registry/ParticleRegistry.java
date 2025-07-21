package com.boundless.registry;

import com.boundless.BoundlessAPI;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.particle.ParticleType;

public class ParticleRegistry {
    public static void initialize() {}

    public static SimpleParticleType registerParticle(String name, SimpleParticleType particle) {
        Registry.register(Registries.PARTICLE_TYPE, BoundlessAPI.identifier(name), particle);
        return particle;
    }
}
