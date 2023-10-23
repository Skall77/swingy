package com.gmail.aaudevar.model.artefact;

public abstract class ArmorFactory {
    public static Armor createArmor(int level) {
        int rng = (int) ((Math.random() * 100) % 4);
        if (level < 3) {
            switch (rng) {
                case 0 -> {
                    return new Armor("Cotton Shirt", 1 + level);
                }
                case 1 -> {
                    return new Armor("Silk dress", 2 + level);
                }
                case 2 -> {
                    return new Armor("Leather Armor", 3 + level);
                }
                case 3 -> {
                    return new Armor("Chain Mail", 4 + level);
                }
                default -> {
                    return new Armor("Something is wrong", 0);
                }
            }
        } else if (level < 6) {
            switch (rng) {
                case 0 -> {
                    return new Armor("Griffin Armor", 5 + level);
                }
                case 1 -> {
                    return new Armor("Feline Armor", 6 + level);
                }
                case 2 -> {
                    return new Armor("Ursine Armor", 7 + level);
                }
                case 3 -> {
                    return new Armor("Wolven Armor", 8 + level);
                }
                default -> {
                    return new Armor("Something is wrong", 0);
                }
            }
        } else {
            switch (rng) {
                case 0 -> {
                    return new Armor("Pridwen Shield", 9 + level);
                }
                case 1 -> {
                    return new Armor("Silken mailcoat of Örvar-Oddr", 10 + level);
                }
                case 2 -> {
                    return new Armor("Babr-e Bayan", 11 + level);
                }
                case 3 -> {
                    return new Armor("Armor of Achilles", 12 + level);
                }
                default -> {
                    return new Armor("Something is wrong", 0);
                }
            }
        }
    }

    public static int getArmorModifier(String armor) {
        return switch (armor) {
            case "Cotton Shirt" -> 1;
            case "Silk dress" -> 2;
            case "Leather Armor" -> 3;
            case "Chain Mail" -> 4;
            case "Griffin Armor" -> 5;
            case "Feline Armor" -> 6;
            case "Ursine Armor" -> 7;
            case "Wolven Armor" -> 8;
            case "Pridwen Shield" -> 9;
            case "Silken mailcoat of Örvar-Oddr" -> 10;
            case "Babr-e Bayan" -> 11;
            case "Armor of Achilles" -> 12;
            default -> -100;
        };
    }
}