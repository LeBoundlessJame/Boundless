package com.boundless.util;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;

public interface IAnimatedHero {
    ModifierLayer<IAnimation> boundless_getModAnimation();
}
