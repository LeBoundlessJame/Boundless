package com.boundless.ability.combat;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import net.minecraft.util.Identifier;

import java.util.SortedMap;

@Builder
@Getter
public class Attack {

    @Singular
    private final SortedMap<Integer, Hit> hits;
}
