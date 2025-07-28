package com.boundless.util;

import com.boundless.BoundlessAPI;
import com.boundless.ability.combat.AttackDataBuilder;
import com.boundless.entity.hero_action.HeroActionEntity;
import com.boundless.registry.DataComponentRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;

import static com.boundless.registry.DataComponentRegistry.ATTACK_END;

public class CombatUtils {
    public static void attack(HeroActionEntity heroAction, AttackDataBuilder attackDataBuilder) {
        heroAction.repositionBox();
        boolean hasPlayedSound = false;

        if (heroAction.getOwner() == null) return;
        PlayerEntity player = (PlayerEntity) heroAction.getOwner();

        if (attackDataBuilder.getCustomAttackLogic() != null) {
            attackDataBuilder.getCustomAttackLogic().accept(attackDataBuilder, player);
        }

        for (LivingEntity livingEntity : heroAction.getWorld().getEntitiesByClass(LivingEntity.class, heroAction.getBoundingBox(), entity -> true)) {
            if (livingEntity != player) {
                if (!hasPlayedSound && attackDataBuilder.getImpactSound() != null) {
                    player.getWorld().playSound(null, livingEntity.getBlockPos(), attackDataBuilder.getImpactSound(), SoundCategory.PLAYERS, 1f, 1f);
                    hasPlayedSound = true;
                }

                if (attackDataBuilder.getCustomHitLogic() != null) {
                    attackDataBuilder.getCustomHitLogic().accept(attackDataBuilder, livingEntity);
                } else {
                    CombatUtils.basicAttackLogic(attackDataBuilder, livingEntity);
                }
            }
        }

        if (!hasPlayedSound && attackDataBuilder.getMissSound() != null) {
            player.getWorld().playSound(null, heroAction.getBlockPos(), attackDataBuilder.getMissSound(), SoundCategory.PLAYERS, 1f, 1f);
        }
    }

    public static void basicAttackLogic(AttackDataBuilder attack, LivingEntity target) {
        DamageSource source = attack.getDamageSource();
        if (source == null) source = target.getDamageSources().generic();
        PlayerEntity attacker = attack.getPlayer();

        Vec3d attackerRotation = attacker.getRotationVector();
        Vec3d effectPos = target.getPos().add(attackerRotation.normalize().multiply(-target.getWidth()).x, target.getHeight() / 2f, attackerRotation.normalize().multiply(-target.getWidth()).z);
        Vec3d effectScale =  new Vec3d(target.getScale() * 0.5f, target.getScale() * 0.5f, target.getScale() * 0.5f);
        Vec3d effectRotation = new Vec3d(attacker.getPitch(), attacker.getYaw() * -1, 0);

        EffekUtils.playRotatedEffect(BoundlessAPI.identifier("melee_impact"), attacker, effectPos, effectScale, effectRotation);
        target.damage(source, attack.getDamage());
        target.takeKnockback(attack.getKnockbackStrength(), attackerRotation.x * -1, attackerRotation.z * -1);
        target.velocityModified = true;
    }

    public static void uppercutLogic(AttackDataBuilder attack, LivingEntity target) {
        DamageSource source = attack.getDamageSource();
        if (source == null) source = target.getDamageSources().generic();
        target.damage(source, attack.getDamage());
        target.setVelocity(attack.getPlayer().getRotationVector().x * 1.2, 1, attack.getPlayer().getRotationVector().z * 1.2);
        target.velocityModified = true;
    }

    public static boolean canAttack(PlayerEntity player) {
        return player.getWorld().getTime() >= HeroUtils.getHeroStack(player).getOrDefault(ATTACK_END, 0L) && !HeroUtils.getHeroStack(player).getOrDefault(DataComponentRegistry.VANILLA_MECHANICS, false);
    }
}
