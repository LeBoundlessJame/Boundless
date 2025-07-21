package com.boundless.util;

import com.boundless.ability.combat.CombatSystem;
import com.boundless.action.Action;
import com.boundless.entity.hero_action.HeroActionEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.BiConsumer;

public class AttackUtils {

    public static void triggerAttackAction(PlayerEntity player, Action action) {
        triggerAttackAction(player, action, true);
    }

    public static void triggerAttackAction(PlayerEntity player, Action action, boolean startAttackTimer) {
        List<Integer> keys = new ArrayList<>(action.scheduledTasks.keySet());
        int lifetime = keys.getLast();

        if (startAttackTimer){
            CombatSystem.startAttackTimer(player, lifetime);
        }

        ActionUtils.performAction(player, action);
        //CombatUtils.performAttack(player, action, duration);
    }
}
