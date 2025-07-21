package com.boundless.mixin;

import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(KeyBinding.class)
public interface KeybindAccessor {
    @Accessor("KEYS_BY_ID")
    public static Map<String, KeyBinding> getKeysByID() {
        throw new AssertionError();
    }
}
