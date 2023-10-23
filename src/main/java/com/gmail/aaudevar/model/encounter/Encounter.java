package com.gmail.aaudevar.model.encounter;

import com.gmail.aaudevar.model.hero.Hero;
import lombok.Getter;

import java.util.Random;

public class Encounter {
    private int id;
    @Getter
    private String options;
    @Getter
    private String type = "";
    private final Random rng = new Random();

    Encounter(int id, Hero hero, String story, String firstOption, String secondOption,
              String typeOption, String type) {
        this.id = id;
        StringBuilder s = new StringBuilder();
        s.append(story).append("\n(1) ").append(firstOption).append("\n(2) ").append(secondOption)
                .append("\n(3) Leave.");
        if (hero.getType().equals(type)) {
            this.type = type;
            s.append("\n(4) ").append(typeOption).append("\n");
        }
        this.options = s.toString();
    }

    public int rollDice() {
        return rng.nextInt(1,20);
    }

    public String getResult(String answer, Hero hero) {
        return switch (id) {
            case 1 -> getResultOne(answer, hero);
            case 2 -> getResultTwo(answer, hero);
            case 3 -> getResultThree(answer, hero);
            case 4 -> getResultFour(answer, hero);
            case 5 -> getResultFive(answer, hero);
            case 6 -> getResultSix(answer, hero);
            case 7 -> getResultSeven(answer, hero);
            case 8 -> getResultEight(answer, hero);
            default -> null;
        };
    }

    private String getResultOne(String answer, Hero  hero) {
        return switch (answer) {
            case "1" -> {
                if (hero.getCurrentHP() + 25 <= hero.getMaxHP())
                    hero.setCurrentHP(hero.getCurrentHP() + 25);
                else
                    hero.setCurrentHP(hero.getMaxHP());
                yield "The taste is really nice,\nYou regain 25 hp.";
            }
            case "2" -> {
                if (rollDice() <= 10) {
                    if (hero.getCurrentHP() - 5 <= 0)
                        hero.setCurrentHP(1);
                    else
                        hero.setCurrentHP(hero.getCurrentHP() - 5);
                    yield "The fish bit you! You lost 5 HP.";
                }
                yield "The fish wasn't really good.\nYou split it on the ground and continue your route.";
            }
            case "3" -> "Who care about a fountain,\nYou need to find a way out of this place.";
            case "4" -> {
                if (hero.getType().equals("Sorceress")) {
                    hero.setCurrentHP(hero.getMaxHP());
                    yield "You feel your body fuelled with a warmth you never knew existed.\nYou are back to full Health";
                } else
                    yield null;
            }
            default -> null;
        };
    }

    private String getResultTwo(String answer, Hero  hero) {
        return switch (answer) {
            case "1" -> {
                hero.setAttack(hero.getAttack() - 2);
                yield """
                        "Me ? You fool!" She start to sing a threatening sound.
                        You feel your body losing strength, you just lose two attack points.\s
                        And the Crone disappeared\s""";
            }
            case "2" -> {
                hero.setAttack(hero.getAttack() + 2);
                    yield """
                            "Nothing indeed." She start to sing a strange sound.
                            You feel like you are getting stronger, you just won two attack points.\s
                            You thanks the Crone before getting back to your escape.""";
                }
            case "3" -> "You ignore the old woman, who look at you with disgust.";
            case "4" -> {
                if (hero.getType().equals("Higher Vampire")) {
                    hero.setAttack(hero.getAttack() + 2);
                    hero.setDefense(hero.getDefense() + 2);
                    yield """
                        You gained the respect of the Crone. She sing an ancient song.
                        You feel you are quicker, stronger, smarter than a few seconds ago
                        You just won two attack and two defense points.""";
                }
                else
                    yield null;
            }
            default -> null;
        };
    }

    private String getResultThree(String answer, Hero  hero) {
        return switch (answer) {
            case "1" -> {
                if (hero.getCurrentHP() - 10 <= 0)
                    hero.setCurrentHP(1);
                else
                    hero.setCurrentHP(hero.getCurrentHP() - 10);
                yield "The wall start to vibrate, you take a big rock on the foot.\nYou lose 10 HP.";
            }
            case "2" -> {
                if (rollDice() <= 10) {
                    if (hero.getCurrentHP() - 15 <= 0)
                        hero.setCurrentHP(1);
                    else
                        hero.setCurrentHP(hero.getCurrentHP() - 15);
                    yield "Are you crazy ? You hit your head into the wall and lose 15 hp.";
                }
                else {
                    hero.setDefense(hero.getDefense() + 2);
                    yield "It somehow work! The wall crumble around you.\nYou even found a broken shield inside\n" +
                            "you win 2 defense point.";
                }
            }
            case "3" -> "You just ignore the wall and take another way toward the exit.";
            case "4" -> {
                if (hero.getType().equals("Halfling")) {
                    hero.setDefense(hero.getDefense() + 5);
                    yield "On the other side of the wall you found a sturdy shield,\nyou win 5 defense point.";
                } else
                    yield null;
            }
            default -> null;
        };
    }

    private String getResultFour(String answer, Hero  hero) {
        return switch (answer) {
            case "1" -> {
                hero.setAttack(hero.getAttack() - 2);
                yield """
                        Your weapon cut through the ghost, without doing any damage.
                        You hear him laugh before disappearing.
                        You dented your weapon on the ground, losing 2 attack point.""";
            }
            case "2" -> {
                if (rollDice() <= 10) {
                    if (hero.getCurrentHP() - 10 <= 0)
                        hero.setCurrentHP(1);
                    else
                        hero.setCurrentHP(hero.getCurrentHP() - 10);
                    yield "You rapidly realise that he is not a nice ghost.\nHe penetrate your body and you feel" +
                            "incredibly weak. You lose 10 hp before running away.";
                }
                else {
                    hero.setAttack(hero.getAttack() + 2);
                    yield "It's actually a pretty nice ghost. A bit too chatty maybe.\n" +
                            "He teach you an ancien wrestling technique.\nYou win two attack points.";
                }
            }
            case "3" -> "You ignore the ghost, who ignore you back. Yep. That easy.";
            case "4" -> {
                if (hero.getType().equals("Witcher")) {
                    hero.setAttack(hero.getAttack() + 2);
                    hero.setDefense(hero.getDefense() + 2);
                    yield """
                            The Wraith beg you to let him survive. He promise you he is a good ghost
                            You decide to believe him. He give you an old amulet in exchange.
                            The amulet give you two attack and two defense point.""";
                } else
                    yield null;
            }
            default -> null;
        };
    }

    private String getResultFive(String answer, Hero  hero) {
        return switch (answer) {
            case "1" -> {
                hero.setAttack(hero.getAttack() + 2);
                if (hero.getCurrentHP() - 10 <= 0)
                    hero.setCurrentHP(1);
                else
                    hero.setCurrentHP(hero.getCurrentHP() - 10);
                yield """
                        When your lips touch, you feel your life energy draining off your body into her.
                        You lose 10 health points. She's a succubus and she thank you for giving her
                        your energy. She give you a ring who grant you 2 attack.""";
            }
            case "2" -> {
                if (hero.getCurrentHP() - 5 <= 0)
                    hero.setCurrentHP(1);
                else
                    hero.setCurrentHP(hero.getCurrentHP() - 5);
                yield "She scream in agony, piercing your eardrums. She die on your weapon.\n" +
                            "You lose 10 health point.";
            }
            case "3" -> "You ignore the woman, maybe you are already married. Maybe you aren't into women.\n" +
                    "Or maybe you are just on that sigma grindset.";
            case "4" -> {
                if (hero.getType().equals("Witcher")) {
                    hero.setAttack(hero.getAttack() + 2);
                    yield "You don't kill intelligent beings, unless they deserve it.\n" +
                            "The succubus thank you by giving you an ring.\n" +
                            "You win 2 attack.";
                } else
                    yield null;
            }
            default -> null;
        };
    }

    private String getResultSix(String answer, Hero  hero) {
        return switch (answer) {
            case "1" -> {
                hero.setDefense(hero.getDefense() -2);
                yield "You feel like you shouldn't have done that.\n" +
                        "There is nothing but silence around you and yet...\n" +
                        "You lost 2 defense points.";
            }
            case "2" -> {
                hero.setMaxHP(hero.getMaxHP() + 2);
                if (hero.getCurrentHP() + 15 <= hero.getMaxHP())
                    hero.setCurrentHP(hero.getCurrentHP() + 15);
                else
                    hero.setCurrentHP(hero.getMaxHP());
                yield "The fruit is sweet and quite frankly delicious.\n" +
                        "You win 2 max HP and heal 15 health points.";
            }
            case "3" -> "You ignore the dumb tree and his even dumber fruits.";
            case "4" -> {
                if (hero.getType().equals("Halfling")) {
                    hero.setMaxHP(hero.getMaxHP() + 5);
                    hero.setCurrentHP(hero.getMaxHP());
                    yield "This is with nostalgia that you eat an half-dozen fruits.\n" +
                            "You win 5 max HP and get healed to full.";
                } else
                    yield null;
            }
            default -> null;
        };
    }

    private String getResultSeven(String answer, Hero  hero) {
        return switch (answer) {
            case "1" -> {
                if (rollDice() > 15) {
                    hero.setDefense(hero.getDefense() + 2);
                    yield "You won ! Incredible! You get some protective glove.\n" +
                            "You win 2 defense points.";
                } else {
                    if (hero.getCurrentHP() - 10 <= 0)
                        hero.setCurrentHP(1);
                    else
                        hero.setCurrentHP(hero.getCurrentHP() - 10);
                    yield """
                            Oh no, you lost.
                            The man take a dagger and extract a bit of your blood.
                            You lost 10 health points.""";
                }
            }
            case "2" -> {
                if (rollDice() > 10) {
                    hero.setDefense(hero.getDefense() + 2);
                    yield """
                            You won !
                            You feel like you would have lost if you didn't cheat!
                            You get some protective glove.
                            You win 2 defense points.""";
                } else {
                    if (hero.getCurrentHP() - 10 <= 0)
                        hero.setCurrentHP(1);
                    else
                        hero.setCurrentHP(hero.getCurrentHP() - 10);
                    yield """
                            You lost despite cheating!
                            This guy was definitely cheating but you can't prove it.
                            He take a dagger and take some of your blood.
                            You lost 10 health points.""";
                }
            }
            case "3" -> "Gambling is bad. You just ignore him.";
            case "4" -> {
                if (hero.getType().equals("Sorceress")) {
                    if (rollDice() >= 5) {
                        hero.setDefense(hero.getDefense() + 2);
                        hero.setAttack(hero.getAttack() + 2);
                        yield """
                            You won !
                            To teach him a lesson you take the gloves and the dice..
                            You win 2 attack and 2 defense points.""";
                    } else {
                        if (hero.getCurrentHP() - 10 <= 0)
                            hero.setCurrentHP(1);
                        else
                            hero.setCurrentHP(hero.getCurrentHP() - 10);
                        yield """
                            You somehow still managed to lose, how unlucky!
                            He take a dagger and take some of your blood.
                            You lost 10 health points.""";
                    }
                } else
                    yield null;
            }
            default -> null;
        };
    }

    private String getResultEight(String answer, Hero  hero) {
        return switch (answer) {
            case "1" -> {
                hero.setAttack(hero.getAttack() - 2);
                yield """
                        You attack the armor and broke your weapon.
                        You hear a laugh and the Armor finally let you pass.
                        you lost 2 attack points.""";
            }
            case "2" -> {
                hero.setAttack(hero.getAttack() + 2);
                yield """
                        The thing inside the armor laugh.
                        He think you are cute
                        And let you pass after sharpening your weapon
                        You win 2 attack points""";
            }
            case "3" -> "You ignore the armor.\n" +
                    "It's not large enough to block completely your path.";
            case "4" -> {
                if (hero.getType().equals("Higher Vampire")) {
                    hero.setAttack(hero.getAttack() + 5);
                    yield """
                            It's Elven Blood.
                            It taste better than the best wine.
                            You feel way stronger
                            You win 5 attack point""";
                }
                else
                    yield null;
            }
            default -> null;
        };
    }
}
