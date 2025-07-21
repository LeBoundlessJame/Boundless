package com.boundless.registry;

import com.boundless.BoundlessAPI;
import com.boundless.hero.api.Hero;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class ItemGroupRegistry {

    public static final ItemGroup BOUNDLESS_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(HeroRegistry.MELEE_HERO.getArmorSet().get(1)))
            .displayName(Text.translatable("itemGroup.boundless.boundless_group"))
            .entries((context, entries) -> {
                for (Hero hero: HeroRegistry.HEROES) {
                    entries.add(hero.getArmorSet().getFirst());
                    entries.add(hero.getArmorSet().get(1));
                    entries.add(hero.getArmorSet().get(2));
                    entries.add(hero.getArmorSet().getLast());
                }
            })
            .build();

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, BoundlessAPI.identifier("boundless_group"), BOUNDLESS_GROUP);
    }
}
