package com.gmail.aaudevar.view;

import com.gmail.aaudevar.model.artefact.Artefact;
import com.gmail.aaudevar.model.hero.Hero;
import com.gmail.aaudevar.model.villain.Villain;

import java.util.List;

public class ViewConsole {
    private static final String logo = """
                
                                                 __          _                                           \s
                                                / _\\_      _(_)_ __   __ _ _   _                         \s
                                                \\ \\\\ \\ /\\ / / | '_ \\ / _` | | | |                        \s
                                                _\\ \\\\ V  V /| | | | | (_| | |_| |                        \s
                                                \\__/ \\_/\\_/ |_|_| |_|\\__, |\\__, |                        \s
                                                                     |___/ |___/                         \s
                             _____ _            __    __ _ _       _                       __    ___  ___\s
                            /__   \\ |__   ___  / / /\\ \\ (_) |_ ___| |__   ___ _ __   _    /__\\  / _ \\/ _ \\
                              / /\\/ '_ \\ / _ \\ \\ \\/  \\/ / | __/ __| '_ \\ / _ \\ '__| (_)  / \\// / /_)/ /_\\/
                             / /  | | | |  __/  \\  /\\  /| | || (__| | | |  __/ |     _  / _  \\/ ___/ /_\\\\\s
                             \\/   |_| |_|\\___|   \\/  \\/ |_|\\__\\___|_| |_|\\___|_|    (_) \\/ \\_/\\/   \\____/\s
                                                                                                         \s
            """;

    public static void viewLogo() {
        System.out.print(logo);
    }

    public static void viewStartScreen() {
        System.out.println("This is Swingy, a The Witcher® themed text based RPG.  (Type (exit) to quit)");
        System.out.println("Would you like to select an old hero or to create a new one ?");
    }

    public static void viewSelectHeroScreen() {
        System.out.println("(1) Select an old hero.");
        System.out.println("(2) Create a new hero.");
        System.out.println("(3) change View (GUI).");
    }

    public static void viewHeroes(List<List<String>> Heroes) {
        System.out.println("Pick a hero: ");
        for (List<String> hero : Heroes) {
            System.out.println("(" + hero.get(0) + ")");
            System.out.println(hero.get(1));
            System.out.println("class : " + hero.get(2));
            System.out.println("weapon : " + hero.get(3));
            System.out.println("armor : " + hero.get(4));
            System.out.println("helm : " + hero.get(5));
            System.out.println("level : " + hero.get(6));
            System.out.println("experience points : " + hero.get(7));
            System.out.println("attack : " + hero.get(8));
            System.out.println("defense : " + hero.get(9));
            System.out.println("max HP : " + hero.get(10));
            System.out.println("|----------------------------|");
        }
    }

    public static void viewHeroStat(Hero hero) {
        hero.summary();
        hero.viewEquipment();
    }

    public static void viewCurrentHp(Hero hero) {
        System.out.println("You have " + hero.getCurrentHP() + " Health points.");
    }

    public static void viewVillainStat(Villain villain) {
        System.out.println(villain.getName() + " have " + villain.getAttack() + " attack, "
                + villain.getDefense() + " defense and " + villain.getCurrentHP() + " health points.");
    }

    public static void viewChooseAName() {
        System.out.println("Choose the name of your hero: ");
    }

    public static void viewChooseHeroClass() {
        System.out.println("Choose the class of your hero: ");
        System.out.println("(1) Halfling (Fast, peaceful and cunning hero who get dragged into adventure)\n" +
                        "(2) Higher Vampire (Old and smart hero who possess unique abilities)\n" +
                        "(3) Sorceress (Skilled and educated user of magic, who love power and politics)\n" +
                        "(4) Witcher (Mutant who've been trained to kill monsters)");
    }

    public static void viewUserInput(String input) {
        System.out.println(input);
    }
    public static void viewInputNotRecognized(String error) {
        System.out.println("Input not recognized: " + error);
    }

    public static void viewException(String exceptionMessage) {
        System.out.print("Error: ");
        System.out.println(exceptionMessage);
    }

    public static void viewStory() {
        System.out.println("You are stuck in a labyrinth on Nilfgaardians lands." +
                " Your only two way to get out is to make your way toward the exit or to get to level 8," +
                " while fighting for your life and making weird encounters.");
    }

    public static void viewMovements() {
        System.out.println("In which direction would you like to go ? \n" +
                "(N) North \n" +
                "(S) South \n" +
                "(W) West \n" +
                "(E) East");
    }

    public static void viewWin() {
        System.out.println("You reach the end of the labyrinth! You Win. Your hero will be saved in the database.");
    }

    public static void viewArtefact(Artefact artefact) {
        switch (artefact.getType()) {
            case "weapon" -> System.out.println("This weapon named " + artefact.getName() + " give you "
                    + artefact.getModifier() + " attack.");
            case "helm" -> System.out.println("This helm named " + artefact.getName() + " give you "
                    + artefact.getModifier() + " health.");
            case "armor" -> System.out.println("This armor named " + artefact.getName() + " give you "
                    + artefact.getModifier() + " armor.");
        }
    }

    public static void viewCompareArtefact(Artefact artefact, Hero hero) {
        switch (artefact.getType()) {
            case "weapon" -> System.out.println("You are currently equipped with " + hero.getWeapon().getName()
                    + " which give you " + hero.getWeapon().getModifier() + " attack.");
            case "helm" -> System.out.println("You are currently equipped with " + hero.getHelm().getName()
                    + " which give you " + hero.getHelm().getModifier() + " health.");
            case "armor" -> System.out.println("You are currently equipped with " + hero.getArmor().getName()
                    + " which give you " + hero.getArmor().getModifier() + " armor.");
        }
    }

    public static void viewMessage(String message) {
        System.out.println(message);
    }

}
