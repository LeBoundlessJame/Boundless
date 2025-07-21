package com.boundless.registry;

import com.boundless.BoundlessAPI;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class DataComponentRegistry {
    public static ComponentType<Boolean> VANILLA_MECHANICS = registerComponent("vanilla_mechanics", builder -> ComponentType.<Boolean>builder().codec(Codec.BOOL));
    public static final ComponentType<Map<String, Identifier>> ABILITY_LOADOUT = registerComponent("ability_loadout", builder -> ComponentType.<Map<String, Identifier>>builder().codec(Codec.unboundedMap(Codec.STRING, Identifier.CODEC)));
    public static final ComponentType<Map<Identifier, Long>> COOLDOWN_DATA = registerComponent("cooldown_data", builder -> ComponentType.<Map<Identifier, Long>>builder().codec(Codec.unboundedMap(Identifier.CODEC, Codec.LONG)));
    public static ComponentType<Long> ATTACK_START = registerComponent("attack_start", builder -> ComponentType.<Long>builder().codec(Codec.LONG));
    public static ComponentType<Long> ATTACK_END = registerComponent("attack_end", builder -> ComponentType.<Long>builder().codec(Codec.LONG));

    public static <T> ComponentType<T> registerComponent(String name, UnaryOperator<ComponentType.Builder<T>> builder) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, BoundlessAPI.identifier(name), builder.apply(ComponentType.builder()).build());
    }

    public static void initialize() {}
}
