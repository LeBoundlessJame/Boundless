package com.boundless;

import com.boundless.event.HeroHudRenderer;
import com.boundless.event.KeyInputHandler;
import com.boundless.networking.PayloadRegistry;
import com.boundless.registry.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class BoundlessAPIClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRenderRegistry.initialize();
        KeybindRegistry.initialize();
        KeyInputHandler.keyInputs();
        PayloadRegistry.registerS2CPackets();
        HeroHudRenderer.register();
        ParticleClientRegistry.initialize();
        ArmorRenderRegistry.initialize();
    }
}
