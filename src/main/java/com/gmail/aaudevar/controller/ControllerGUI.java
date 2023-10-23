package com.gmail.aaudevar.controller;

import com.gmail.aaudevar.model.Model;
import com.gmail.aaudevar.view.gui.WindowInterface;

import java.util.Random;


public class ControllerGUI {
    static Model model;
    static WindowInterface windowInterface;
    static Random rng = new Random();
    static boolean b;

    public ControllerGUI(WindowInterface windowInterface, Model model, boolean b) {
        ControllerGUI.model = model;
        ControllerGUI.b = b;
        try {
            ControllerGUI.model.modelInit();
            ControllerGUI.windowInterface = windowInterface;
            windowInterface.setEnableStartButton(true);
        } catch (Exception e) {
            ControllerGUI.windowInterface.disableStartSelectScreen();
            ControllerGUI.windowInterface.viewErrorOnStart("FATAL error: Corrupted Database.\n" +e.getMessage());
        }
    }

    public static void handler(String input) {
        switch (input) {
            case "VIEW_START_SCREEN" -> windowInterface.viewStartScreen();
            case "VIEW_SELECT" -> windowInterface.viewSelectScreen(model.getStoredHeroes());
            case "VIEW_CREATE_SCREEN" -> windowInterface.viewCreateScreen();
            case "VIEW_GAME_VIEW" -> {
                model.generateMap();
                windowInterface.viewGameView(model.getHero());
                enableMovementButtons();
                windowInterface.viewGameInfo(model.getCurrentMap());
            }
            case "CHANGE_VIEW" -> {
                try {
                    windowInterface.changeView();
                    ControllerConsole controllerConsole = new ControllerConsole(new Model());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            case "PICKUP_ITEM" -> {
                try {
                    model.equipNewArtefact(model.getArtefact());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                windowInterface.viewGameInfo("Artefact equipped!\n\n" + model.getCurrentMap());
                model.setArtefact(null);
                windowInterface.updateGameViewHeroStats(model.getHero());
                enableMovementButtons();
            }
            case "LEAVE_ITEM" -> {
                model.setArtefact(null);
                windowInterface.viewGameInfo("You didn't equip the Artefact!\n\n" + model.getCurrentMap());
                enableMovementButtons();
            }
            case "EXIT" -> windowInterface.quitGame();
            case "PLAY_AGAIN" -> {
                try {
                    model = new Model();
                    model.modelInit();
                    windowInterface.viewStartScreen();
                } catch (Exception e) {
                    windowInterface.viewErrorDialog(e.getMessage());
                }
            }
        }
    }

    public static void handler(String input, String value) {
        if (input.equals("SELECT_HERO_BY_ID")) {
            try {
                model.selectHero(value);
                handler("VIEW_GAME_VIEW");
            } catch (Exception e) {
                windowInterface.viewErrorDialog(e.getMessage());
            }
        }
    }

    public static void handler(String input, String valueOne, String valueTwo) {
        if (input.equals("CREATE_NEW_HERO")) {
            try {
                if (model.getHeroViaName(valueOne).isEmpty()) {
                    model.createHero(valueOne, valueTwo);
                    if (model.getHero() != null) {
                        model.storeHero();
                        handler("VIEW_GAME_VIEW");
                    }
                    else
                        windowInterface.viewErrorDialog("Can't create hero.\n");
                }
                else
                    windowInterface.viewErrorDialog("Hero name already exist.\n");
            } catch (Exception e) {
                windowInterface.viewErrorDialog(e.getMessage());
            }
        }
    }

    public static void movementHandler(String input) {
        windowInterface.viewGameInfo("");
        switch (input) {
            case "MOVE_NORTH" -> model.getMap().goNorth();
            case "MOVE_SOUTH" -> model.getMap().goSouth();
            case "MOVE_EAST" -> model.getMap().goEast();
            case "MOVE_WEST" -> model.getMap().goWest();
        }
        runCoordinateChecks();
    }

    public static boolean firstStart() {
        return b;
    }

    public static void fightHandler(String input) {
        switch (input) {
            case "START_FIGHT" -> {
                windowInterface.viewGameInfo("The fight start! Roll the Dice!");
                enableAttackButton();
            }
            case "RUN" -> {
                if (rng.nextInt(100) >= 49) {
                    windowInterface.viewGameInfo("You can't run from this fight!\nRoll the Dice!");
                    enableAttackButton();
                } else {
                    model.getMap().runAwayUpdateMap();
                    windowInterface.viewGameInfo("You ran away!\n\n" + model.getCurrentMap());
                    enableMovementButtons();
                }
            }
            case "ROLL DICE" -> runFight();
        }

    }

    private static void runFight() {
        windowInterface.viewGameInfo(model.fightVillainTurn() + "\n" + model.fightHeroTurn());

        if (model.getHero().getCurrentHP() <= 0) {
            disableAllButtons();
            windowInterface.endGameMessage("Lost Window", "YOU DIED");
        }
        else if (model.getVillain().getCurrentHP() <= 0) {
            ControllerGUI.WinFight();
        }
    }

    private static void WinFight() {
        StringBuilder s = new StringBuilder();
        s.append("You won the fight!\nYou win ").append(model.getVillain().getXp()).append(" experience points.");
        model.getHero().setXp(model.getHero().getXp() + model.getVillain().getXp());
        if (model.isLevelUp()) {
            model.getHero().levelUp();
            if (model.getHero().getLevel() == 8) {
                s.append(" Congratulation! You reached the max level! You won the game!");
                windowInterface.maxLevelMessage("Win Window", s.toString());
            } else {
                model.generateMap();
                s.append("\nYou won a level!");
            }
        }
        if (model.isArtefactWon()) {
            model.loot();
            enablePickupButtons();
            windowInterface.viewGameInfo(s.toString(),model.getHero(), model.getArtefact());
        } else {
            enableMovementButtons();
            windowInterface.viewGameInfo(s.toString() + "\n\n" + model.getCurrentMap());
        }
        windowInterface.updateGameViewHeroStats(model.getHero());

    }

    private static void runCoordinateChecks() {
        if (model.getMap().NotinBound(model.getMap().getX(), model.getMap().getY())) {
            disableAllButtons();
            try {
                model.updateStatistics();
                windowInterface.endGameMessage("You reach the end of the labyrinth! You Win.", "Your hero will be saved in the database.");
            } catch (Exception e){
                windowInterface.viewErrorDialog(e.getMessage());
            }
        } else if (model.getMap().updateMap()) {
            model.selectVillain();
            windowInterface.viewGameInfo("", model.getVillain());
            enableFightStartButtons();
        } else if (rng.nextInt(100) >= 66) {
            model.selectEncounter();
            windowInterface.viewGameInfo(model.getEncounter().getOptions());
            windowInterface.setTextEncounter();
            windowInterface.setActionListenerButton();
        }
        else
            windowInterface.viewGameInfo(model.getCurrentMap());
    }

    private static void enableMovementButtons() {
        windowInterface.setMovementEnabled(true);
        windowInterface.setFightRunEnabled(false);
        windowInterface.setPickupLeaveEnabled(false);
        windowInterface.setRollDiceButtonEnabled(false);
    }

    private static void enableFightStartButtons() {
        windowInterface.setMovementEnabled(false);
        windowInterface.setFightRunEnabled(true);
        windowInterface.setPickupLeaveEnabled(false);
        windowInterface.setRollDiceButtonEnabled(false);
    }

    private static void enableAttackButton() {
        windowInterface.setMovementEnabled(false);
        windowInterface.setFightRunEnabled(false);
        windowInterface.setPickupLeaveEnabled(false);
        windowInterface.setRollDiceButtonEnabled(true);
    }


    private static void enablePickupButtons() {
        windowInterface.setMovementEnabled(false);
        windowInterface.setFightRunEnabled(false);
        windowInterface.setPickupLeaveEnabled(true);
        windowInterface.setRollDiceButtonEnabled(false);
    }

    private static void disableAllButtons() {
        windowInterface.setMovementEnabled(false);
        windowInterface.setFightRunEnabled(false);
        windowInterface.setPickupLeaveEnabled(false);
        windowInterface.setRollDiceButtonEnabled(false);
    }

    public static void encounterHandler(String number) {
        switch (number) {
            case "1" -> {
                windowInterface.viewGameInfo(model.getEncounter().getResult("1", model.getHero()) +
                        "\n\n" + model.getCurrentMap());
                windowInterface.resetMovementListenerButton();
            }
            case "2" -> {
                windowInterface.viewGameInfo(model.getEncounter().getResult("2", model.getHero()) +
                        "\n\n" + model.getCurrentMap());
                windowInterface.resetMovementListenerButton();
            }
            case "3" -> {
                windowInterface.viewGameInfo(model.getEncounter().getResult("3", model.getHero()) +
                        "\n\n" + model.getCurrentMap());
                windowInterface.resetMovementListenerButton();
            }
            case "4" -> {
                String s = model.getEncounter().getResult("4", model.getHero());
                if (s != null){
                    windowInterface.viewGameInfo(s +
                            "\n\n" + model.getCurrentMap());
                    windowInterface.resetMovementListenerButton();
                }
                else
                    windowInterface.viewGameInfo("You can't pick this option.\n\n" + model.getCurrentMap());
            }
        }
    }
}
