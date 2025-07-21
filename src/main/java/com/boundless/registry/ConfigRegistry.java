package com.boundless.registry;

import com.boundless.config.HeroConfig;
import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;

public class ConfigRegistry {
    public static HeroConfig HERO_CONFIG = ConfigApiJava.registerAndLoadConfig(HeroConfig::new);

    public static void initialize() {}
}
