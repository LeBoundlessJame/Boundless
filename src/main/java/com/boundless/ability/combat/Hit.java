package com.boundless.ability.combat;

import com.boundless.entity.hero_action.HeroActionEntity;
import lombok.Builder;
import lombok.Getter;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;

import java.util.function.BiConsumer;

@Builder
@Getter
public class Hit {
    private Float damage;
    private DamageSource damageSource;
    private SoundEvent sound;
    @Builder.Default
    private float soundPitch = 1.0f;
    private BiConsumer<PlayerEntity, HeroActionEntity> hitEffect;
    private BiConsumer<PlayerEntity, HeroActionEntity> actionLogic;
}
