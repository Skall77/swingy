package com.gmail.aaudevar.model.encounter;

import com.gmail.aaudevar.model.hero.Hero;

public abstract class EncounterFactory {
    public static Encounter createEncounter(Hero hero) {
        int rng = (int) ((Math.random() * 100) % 8);
        switch (rng) {
            case 0 -> {
                return new Encounter(1, hero, "You found a fountain, full of a green liquid.\n" +
                        "It smell nice but you can see a little fish swimming inside.",
                        "Drink from the fountain.", "Eat the fish",
                        "(Sorceress) You recognise this liquid, it's a magical health potion." +
                                "\nYou drink from it until your belly is full.", "Sorceress");
            }
            case 1 -> {
                return new Encounter(2, hero, "A wrinkled Crone await you. at a corner of the Labyrinth. \nShe ask you :" +
                        "What is greater than Melitele, more evil than the Man of Glass ?\n" +
                        "The poor have it, the rich need it, and if you eat it, you’ll die?",
                        "You answer \"You ?\"", "You answer \"Nothing ?\"",
                        "(Higher Vampire) You are old enough to recognise those name, you answer" +
                                "\n\"I'm wise enough to know that Melitele is nothing more than a three-faced mother and \n" +
                                "that the Man of Glass is a liar full of himself. Keep your riddle old woman.", "Higher Vampire");
            }
            case 2 -> {
                return new Encounter(3, hero, "There is a giant crumbling wall who block your path.",
                        "Examine the wall closely.", "Try to rush into it to destroy it",
                        "(Halfling) You're small enough to pass through the crack in it!", "Halfling");
            }
            case 3 -> {
                return new Encounter(4, hero, "A ghost block your path, It's pretty clear that if you want to " +
                        "progress,\nyou're gonna need to go through him.", "Give him a wack with your weapon",
                        "Try to talk to him",
                        "(Witcher) It's a Wraith, you know exactly how to get rid of it." +
                                "\nFound his body and cut his head.", "Witcher");
            }
            case 4 -> {
                return new Encounter(5, hero, "You encounter a woman. She's beautiful, lightly dressed.\n" +
                        "Her voice is but a whisper, almost submissive.\nShe beg you you to kiss her.", "Kiss her",
                        "Attack her",
                        "(Witcher) It's a Succubus, you break her spell by casting Aard.", "Witcher");
            }
            case 5 -> {
                return new Encounter(6, hero, "You saw a big tree with what look like Apple on it.\n" +
                        "They are red blood, shinny and look absolutely delicious.",
                        "You kick the tree.", "You eat an apple.",
                        "(Halfling) You know this tree.\n" +
                                "There is a lot of them in Mahakam, where you grew up.", "Halfling");
            }
            case 6 -> {
                return new Encounter(7, hero, "A man interrupt you. He want to play a game of dice.\n" +
                        "If you win, you will receive magical gloves.\n" +
                        "If you lose, you'll have to pay with your blood.",
                        "Play, fair and square.", "Try to cheat.",
                        "(Sorceress) You sense that the dice are full of magic.\n" +
                                "Reverse the spell.", "Sorceress");
            }
            case 7 -> {
                return new Encounter(8, hero, "An imposing Armor block your path.\n" +
                        "A voice withing the metal ground : \"You shall not pass\"",
                        "You attack the armor with your weapon.",
                        "You ask kindly if you can pass.",
                        "(Higher Vampire) You notice that the armor doesn't protect the neck.\n" +
                                "You try to suck the blood of what is inside the armor.", "Higher Vampire");
            }
            default -> {return null;}
        }
    }
}
