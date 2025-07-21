package com.boundless;

import com.boundless.networking.PayloadRegistry;
import com.boundless.registry.*;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoundlessAPI implements ModInitializer {
	public static final String MOD_ID = "boundless";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ConfigRegistry.initialize();
		DataComponentRegistry.initialize();
		HeroRegistry.initialize();
		PayloadRegistry.registerPayloads();
		PayloadRegistry.registerC2SPackets();
		EntityRegistry.intialize();
		SoundRegistry.initialize();
		StatusEffectRegistry.initialize();
		ParticleRegistry.initialize();
		ItemGroupRegistry.initialize();
		AttributeRegistry.initialize();
		AbilityRegistry.initialize();
		LOGGER.info("Boundless API Initialized");
	}

	public static Identifier identifier(String name) {
		return Identifier.of(BoundlessAPI.MOD_ID, name);
	}

	public static Identifier hudPNG(String name) {
		return BoundlessAPI.identifier("textures/gui/sprites/hud/" + name + ".png");
	}
}