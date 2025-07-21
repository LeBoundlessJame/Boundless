package com.boundless.util;

import com.boundless.mixin.KeybindAccessor;
import net.minecraft.client.option.KeyBinding;

import java.util.Map;

public class KeybindingUtils {
    public static KeyBinding getKeyBindingFromTranslation(String translation) {
        Map<String, KeyBinding> keysByID = KeybindAccessor.getKeysByID();
        return keysByID.get(translation);
    }
}
