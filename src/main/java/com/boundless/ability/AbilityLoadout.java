package com.boundless.ability;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.Map;

@Builder @Getter @Setter
public class AbilityLoadout {
    private final String loadoutName;
    @Singular
    private final Map<String, Ability> abilities;
}
