package com.gmail.aaudevar.model.villain;

public abstract class VillainFactory {
    public static int levelRNG() {
        return (int) ((Math.random() * 100) % 2);
    }
    public static Villain createVillain(int level) {
        int rng = (int) ((Math.random() * 100) % 6);
        return switch (rng) {
            case 0 -> new Villain("Bruxa", level + VillainFactory.levelRNG(), 7, 4, 40, 300);
            case 1 -> new Villain("Kikimore", level + VillainFactory.levelRNG(), 4, 7, 40, 300);
            case 2 -> new Villain("Basilisk", level + VillainFactory.levelRNG(), 5, 5, 50, 300);
            case 3 -> new Villain("Drowner", level + VillainFactory.levelRNG(), 3, 3, 30, 200);
            case 4 -> new Villain("Striga", level + VillainFactory.levelRNG(), 4, 4, 70, 300);
            case 5 -> new Villain("NightWraith", level + VillainFactory.levelRNG(), 7, 7, 70, 500);
            default -> null;
        };
    }
}
