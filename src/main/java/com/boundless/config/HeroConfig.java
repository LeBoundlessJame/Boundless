package com.boundless.config;

import com.boundless.BoundlessAPI;
import me.fzzyhmstrs.fzzy_config.config.Config;

public class HeroConfig extends Config {
    public HeroConfig() {
        super(BoundlessAPI.identifier("hero_config"));
    }
    @Override
    public int defaultPermLevel() {
        return 4;
    }
}
