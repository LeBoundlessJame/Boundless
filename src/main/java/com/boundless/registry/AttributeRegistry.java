package com.boundless.registry;

import com.boundless.BoundlessAPI;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class AttributeRegistry {
    public static final RegistryEntry<EntityAttribute> DAMAGE_RESISTANCE = registerAttribute("damage_resistance", new ClampedEntityAttribute("boundless.damage_resistance", 1.0D, 1.0D, 2).setTracked(true));

    private static RegistryEntry<EntityAttribute> registerAttribute(String name, EntityAttribute attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE, BoundlessAPI.identifier(name), attribute);
    }

    public static void initialize() {}
}
