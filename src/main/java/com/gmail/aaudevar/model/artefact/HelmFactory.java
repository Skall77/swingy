package com.gmail.aaudevar.model.artefact;

public abstract class HelmFactory {
    public static Helm createHelm(int level) {
        int rng = (int) ((Math.random() * 100) % 4);
        if (level < 3) {
            switch (rng) {
                case 0 -> {
                    return new Helm("Peasant Hat", 10 + level * 10);
                }
                case 1 -> {
                    return new Helm("Feathered Hat", 20 + level * 10);
                }
                case 2 -> {
                    return new Helm("Witch Hat", 30 + level * 10);
                }
                case 3 -> {
                    return new Helm("Vampire Velvet Top Hat", 40 + level * 10);
                }
                default -> {
                    return new Helm("Something is wrong", 0);
                }
            }
        }
        else if (level < 6) {
            switch (rng) {
                case 0 -> {
                    return new Helm("Iron Helmet", 50 + level * 10);
                }
                case 1 -> {
                    return new Helm("Goblin Helm", 60 + level * 10);
                }
                case 2 -> {
                    return new Helm("Ogre Headware", 70 + level * 10);
                }
                case 3 -> {
                    return new Helm("Dragon-plate Helmet", 80 + level * 10);
                }
                default -> {
                    return new Helm("Something is wrong", 0);
                }
            }
        }
        else if (level < 9) {
            switch (rng) {
                case 0 -> {
                    return new Helm("N7 Helmet", 90 + level * 10);
                }
                case 1 -> {
                    return new Helm("T-45 Helmet", 100 + level * 10);
                }
                case 2 -> {
                    return new Helm("Varia Suit Helmet", 110 + level * 10);
                }
                case 3 -> {
                    return new Helm("Mjolnir Mark VI Helmet", 120 + level * 10);
                }
                default -> {
                    return new Helm("Something is wrong", 0);
                }
            }
        }
        return null;
    }

    public static int getHelmModifier(String helm) {
        return switch (helm) {
            case "Peasant Hat" -> 10;
            case "Feathered Hat" -> 20;
            case "Witch Hat" -> 30;
            case "Vampire Velvet Top Hat" -> 40;
            case "Iron Helmet" -> 50;
            case "Goblin Helm" -> 60;
            case "Ogre Headware" -> 70;
            case "Dragon-plate Helmet" -> 80;
            case "N7 Helmet" -> 90;
            case "T-45 Helmet" -> 100;
            case "Varia Suit Helmet" -> 110;
            case "Mjolnir Mark VI Helmet" -> 120;
            default -> -100;
        };
    }
}
