package com.boundless.registry;

import com.boundless.hero.MeleeHero;
import com.boundless.hero.api.Hero;

import java.util.ArrayList;

public class HeroRegistry {
    public static ArrayList<Hero> HEROES = new ArrayList<>();
    public static Hero MELEE_HERO = new MeleeHero();
    public static void initialize() {}
}
