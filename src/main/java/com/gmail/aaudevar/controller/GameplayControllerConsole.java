package com.gmail.aaudevar.controller;

import com.gmail.aaudevar.model.Model;
import com.gmail.aaudevar.view.ViewConsole;

import javax.swing.text.View;
import java.util.Random;

public class GameplayControllerConsole {

    private static final Random rng = new Random();
    private static boolean viewMapScreen;
    private static boolean viewFightOrRunScreen;
    private static boolean viewFightLoopScreen;
    private static boolean viewArtefactChoiceScreen;
    private static boolean viewGameOverScreen;
    private static boolean viewEncounterScreen;
    private static Model model;

    public static void gameBegin(Model model) throws Exception {
        GameplayControllerConsole.model = model;
        String exitString = "";
        viewMapScreen = true;
        viewFightOrRunScreen = false;
        viewFightLoopScreen = false;
        viewArtefactChoiceScreen = false;
        viewGameOverScreen = false;
        viewEncounterScreen = false;

        model.generateMap();

        while(!viewGameOverScreen) {
            if (viewMapScreen) {
                ViewConsole.viewCurrentHp(GameplayControllerConsole.model.getHero());
                GameplayControllerConsole.model.getMap().printMaps();
                ViewConsole.viewMovements();
                exitString = ControllerConsole.getNextLine();
                if (exitString.equals("exit"))
                    break;
                runMovement(exitString);
            }
            else if (viewFightOrRunScreen) {
                ViewConsole.viewVillainStat(model.getVillain());
                ViewConsole.viewMessage("(F) if you want to fight. \n"
                + "(R) if you want ro run (50% chance).");
                exitString = ControllerConsole.getNextLine();
                if (exitString.equals("exit"))
                    break;
                runFightOrRun(exitString);
            }
            else if (viewFightLoopScreen) {
                ViewConsole.viewMessage("Press ENTER to start the fight.");
                exitString = ControllerConsole.getNextLine();
                if (exitString.equals("exit"))
                    break;
                runFightLoop();
            }
            else if (viewArtefactChoiceScreen) {
                ViewConsole.viewMessage("The villain dropped an artefact!");
                model.loot();
                ViewConsole.viewArtefact(model.getArtefact());
                ViewConsole.viewCompareArtefact(model.getArtefact(), model.getHero());
                ViewConsole.viewMessage("Would you like to equip the new Artefact ? \n"
                                        + "(Y) Yes.\n"
                                        + "(N) No. (Keep your current artefact).");
                exitString = ControllerConsole.getNextLine();
                if (exitString.equals("exit"))
                    break;
                runEquipNewArtefact(exitString);
            } else if (viewEncounterScreen) {
                ViewConsole.viewMessage(model.getEncounter().getOptions());
                exitString = ControllerConsole.getNextLine();
                if (exitString.equals("exit"))
                    break;
                runEncounter(exitString);
            }
        }

    }

    private static void runEncounter(String exitString) {
        String s = model.getEncounter().getResult(exitString, model.getHero());
        if (s == null) {
            ViewConsole.viewInputNotRecognized(exitString);
            exitString = ControllerConsole.getNextLine();
            runEncounter(exitString);
        }
        else {
            ViewConsole.viewMessage(s + "\n");
            viewEncounterScreen = false;
            viewMapScreen = true;
        }
    }

    private static void runFightLoop() {
        model.fightSimulation();
        if (model.getHero().getCurrentHP() <= 0) {
            ViewConsole.viewMessage("YOU DIED.");
            viewGameOverScreen = true;
        }
        else {
            ViewConsole.viewMessage("You won the fight! You win " + model.getVillain().getXp() + " experience points.");
            model.getHero().setXp(model.getHero().getXp() + model.getVillain().getXp());
            if (model.isLevelUp()){
                model.getHero().levelUp();
                if (model.getHero().getLevel() == 8) {
                    ViewConsole.viewMessage("Congratulation! You reached the max level!");
                    viewMapScreen = false;
                    viewGameOverScreen = true;
                }
                else {
                    model.generateMap();
                    ViewConsole.viewMessage("You won a level! This is your new stats : ");
                    model.getHero().summary();
                }
            }
            if (model.isArtefactWon())
                viewArtefactChoiceScreen = true;
            else
                viewMapScreen = true;
        }
        viewFightLoopScreen = false;
    }

    private static void runFightOrRun(String str) {
        switch (str) {
            case "F" -> {
                ViewConsole.viewMessage("FIGHT!");
                viewFightOrRunScreen = false;
                viewFightLoopScreen = true;
            }
            case "R" -> {
                if (rng.nextInt(100) >= 49) {
                    ViewConsole.viewMessage("You didn't managed to run... FIGHT!");
                    runFightOrRun("F");
                } else {
                    ViewConsole.viewMessage("You successfully ran away!");
                    model.getMap().runAwayUpdateMap();
                    viewFightOrRunScreen = false;
                    viewMapScreen = true;
                }
            }
            default -> ViewConsole.viewInputNotRecognized(str);
        }
    }

    private static void runMovement(String exitString) {
        switch (exitString) {
            case "N" -> {
                if (checkIfWin("N")) {
                    runWin();
                } else {
                    model.getMap().goNorth();
                    runMovementHelper();
                }
            }
            case "S" -> {
                if (checkIfWin("S")) {
                    runWin();
                } else {
                    model.getMap().goSouth();
                    runMovementHelper();
                }
            }
            case "W" -> {
                if (checkIfWin("W")) {
                    runWin();
                } else {
                    model.getMap().goWest();
                    runMovementHelper();
                }
            }
            case "E" -> {
                if (checkIfWin("E")) {
                    runWin();
                } else {
                    model.getMap().goEast();
                    runMovementHelper();
                }
            }
            default -> ViewConsole.viewInputNotRecognized(exitString);
        }
    }

    private static void runMovementHelper() {
        if (model.getMap().updateMap()) {
            viewFightOrRunScreen = true;
            viewMapScreen = false;
            model.selectVillain();
        } else if( rng.nextInt(100) >= 66) {
            viewEncounterScreen = true;
            viewMapScreen = false;
            model.selectEncounter();
        }
    }

    private static boolean checkIfWin(String direction) {
        return switch (direction) {
            case "N" -> model.getMap().NotinBound((model.getMap().getX() - 1), model.getMap().getY());
            case "S" -> model.getMap().NotinBound((model.getMap().getX() + 1), model.getMap().getY());
            case "W" -> model.getMap().NotinBound(model.getMap().getX(), model.getMap().getY() - 1);
            case "E" -> model.getMap().NotinBound(model.getMap().getX(), model.getMap().getY() + 1);
            default -> true;
        };
    }

    private static void runWin() {
        ViewConsole.viewWin();
        try {
            model.updateStatistics();
        } catch (Exception e) {
            ViewConsole.viewException(e.getMessage());
        }
        viewMapScreen = false;
        viewGameOverScreen = true;
    }
    private static void runEquipNewArtefact(String str) throws Exception {
        switch (str) {
            case "Y" -> {
                model.equipNewArtefact(model.getArtefact());
                ViewConsole.viewMessage("Artefact equipped!");
            }
            case "N" -> {
                model.setArtefact(null);
                ViewConsole.viewMessage("You didn't equip the Artefact!");
            }
            default -> ViewConsole.viewInputNotRecognized(str);
        }
        viewArtefactChoiceScreen = false;
        viewMapScreen = true;
    }

}
