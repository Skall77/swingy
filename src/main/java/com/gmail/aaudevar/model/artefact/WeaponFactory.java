package com.gmail.aaudevar.model.artefact;

public abstract class WeaponFactory {
    public static Weapon createWeapon(int level) {
        int rng = (int) ((Math.random() * 100) % 4);
        if (level < 3) {
            switch (rng) {
                case 0 -> {
                    return new Weapon("Wooden spoon", 1 + level);
                }
                case 1 -> {
                    return new Weapon("Simple Dagger", 2 + level);
                }
                case 2 -> {
                    return new Weapon("Curved Sword", 3 + level);
                }
                case 3 -> {
                    return new Weapon("Throwing Axe", 4 + level);
                }
                default -> {
                    return new Weapon("Something is wrong", 0);
                }
            }
        }
        else if (level < 6) {
            switch (rng) {
                case 0 -> {
                    return new Weapon("Magical Golden Hammer", 5 + level);
                }
                case 1 -> {
                    return new Weapon("Toussaint's Silver Sword", 6 + level);
                }
                case 2 -> {
                    return new Weapon("Leshen's Wood Quarterstaff", 7 + level);
                }
                case 3 -> {
                    return new Weapon("Giant Fucking Club", 8 + level);
                }
                default -> {
                    return new Weapon("Something is wrong", 0);
                }
            }
        }
        else if (level < 9) {
            switch (rng) {
                case 0 -> {
                    return new Weapon("Kusanagi-no-Tsurugi", 9 + level);
                }
                case 1 -> {
                    return new Weapon("Zireael", 10 + level);
                }
                case 2 -> {
                    return new Weapon("Aerondight", 11 + level);
                }
                case 3 -> {
                    return new Weapon("Mjölnir", 12 + level);
                }
                default -> {
                    return new Weapon("Something is wrong", 0);
                }
            }
        }
        return null;
    }

    public static int getWeaponModifier(String weapon) {
        return switch (weapon) {
            case "Wooden spoon" -> 1;
            case "Simple Dagger" -> 2;
            case "Curved Sword" -> 3;
            case "Throwing Axe" -> 4;
            case "Magical Golden Hammer" -> 5;
            case "Toussaint's Silver Sword" -> 6;
            case "Leshen's Wood Quarterstaff" -> 7;
            case "Giant Fucking Club" -> 8;
            case "Kusanagi-no-Tsurugi" -> 9;
            case "Zireael" -> 10;
            case "Aerondight" -> 11;
            case "Mjölnir" -> 12;
            default -> -100;
        };
    }
}
