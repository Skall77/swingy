package com.gmail.aaudevar.model.hero;

public abstract class HeroFactory {
    public static Hero createHeroFromDatabase(String name, String type, String weapon,
                                              String armor, String helm, int level, int xp) {
        return switch (type) {
            case "Halfling" -> new Halfling(name, type, weapon, armor, helm, level, xp);
            case "Higher Vampire" -> new HigherVampire(name, type, weapon, armor, helm, level, xp);
            case "Sorceress" -> new Sorceress(name, type, weapon, armor, helm, level, xp);
            case "Witcher" -> new Witcher(name, type, weapon, armor, helm, level, xp);
            default -> null;
        };
    }

    public static Hero createNewHero(String name, String type) {
        return switch (type) {
            case "Halfling" -> new Halfling(name);
            case "Higher Vampire" -> new HigherVampire(name);
            case "Sorceress" -> new Sorceress(name);
            case "Witcher" -> new Witcher(name);
            default -> null;
        };
    }
}
