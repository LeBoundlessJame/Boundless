package com.boundless.networking;

import com.boundless.BoundlessAPI;
import com.boundless.networking.payloads.AbilityUsePayload;
import com.boundless.networking.payloads.AnimationPlayPayload;
import com.boundless.networking.payloads.evasion.EvasionClientPayload;
import com.boundless.networking.payloads.evasion.EvasionServerPayload;
import com.boundless.networking.payloads.flight.UpdateFlightDirectionPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class PayloadRegistry {
    public static final Identifier ABILITY_USE = BoundlessAPI.identifier("ability_use");
    public static final Identifier ANIMATION_PLAY = BoundlessAPI.identifier("animation_play");
    public static final Identifier EVASION_CLIENT = BoundlessAPI.identifier("evasion_client");
    public static final Identifier EVASION_SERVER = BoundlessAPI.identifier("evasion_server");
    public static final Identifier UPDATE_FLIGHT_DIRECTION = BoundlessAPI.identifier("update_flight_direction");

    public static void registerPayloads() {
        PayloadTypeRegistry.playC2S().register(UpdateFlightDirectionPayload.ID, UpdateFlightDirectionPayload.CODEC);
        PayloadTypeRegistry.playC2S().register(AbilityUsePayload.ID, AbilityUsePayload.CODEC);
        PayloadTypeRegistry.playC2S().register(EvasionServerPayload.ID, EvasionServerPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(AnimationPlayPayload.ID, AnimationPlayPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(EvasionClientPayload.ID, EvasionClientPayload.CODEC);
    }

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(UpdateFlightDirectionPayload.ID, UpdateFlightDirectionPayload::receive);
        ServerPlayNetworking.registerGlobalReceiver(AbilityUsePayload.ID, AbilityUsePayload::receive);
        ServerPlayNetworking.registerGlobalReceiver(EvasionServerPayload.ID, EvasionServerPayload::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(AnimationPlayPayload.ID, AnimationPlayPayload::receive);
        ClientPlayNetworking.registerGlobalReceiver(EvasionClientPayload.ID, EvasionClientPayload::receive);
    }
}
