package com.gmail.aaudevar.controller;

import com.gmail.aaudevar.model.Model;
import com.gmail.aaudevar.view.ViewConsole;
import com.gmail.aaudevar.view.gui.MainView;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ControllerConsole {

    Model model;
    private boolean viewStartScreen;
    private boolean viewSelectHeroScreen;
    private boolean viewChooseAName;
    private boolean viewChooseHeroClass;
    private boolean viewGameBeginScreen;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public ControllerConsole(Model model) throws Exception {
        this.model = model;
        List<String> userInput = new ArrayList<>();
        String exitString = "";
        viewStartScreen = false;
        viewSelectHeroScreen = false;
        viewChooseHeroClass = false;
        viewChooseAName = false;

        try {
            this.model.modelInit();
            viewStartScreen = true;
            ViewConsole.viewLogo();
            ViewConsole.viewStartScreen();
        } catch (Exception e) {
            ViewConsole.viewMessage("FATAL ERROR1: Database corrupted.");
            ViewConsole.viewException(e.getMessage());
            ViewConsole.viewMessage("Exiting game");
            exitString = "exit";
        }

        while (!exitString.equals("exit")) {
            if (viewStartScreen) {
                ViewConsole.viewSelectHeroScreen();
                exitString = getNextLine();
                if (exitString.equals("exit"))
                    break;
                if (this.runHeroSelection(exitString))
                    break;
            }
            else if (viewSelectHeroScreen) {
                ViewConsole.viewHeroes(this.model.getStoredHeroes());
                exitString = getNextLine();
                if (exitString.equals("exit"))
                    break;
                this.runSelectHeroFromDatabase(exitString);
            }
            else if (viewChooseAName) {
                ViewConsole.viewChooseAName();
                exitString = getNextLine();
                if (exitString.equals("exit"))
                    break;
                this.runChooseAName(exitString, userInput);
            }
            else if (viewChooseHeroClass) {
                ViewConsole.viewChooseHeroClass();
                exitString = getNextLine();
                if (exitString.equals("exit"))
                    break;
                this.runChooseHeroClass(exitString, userInput);
            }
            else if(viewGameBeginScreen) {
                ViewConsole.viewMessage("This is the hero you picked :");
                ViewConsole.viewHeroStat(model.getHero());
                ViewConsole.viewMessage("You are ready to play.");
                ViewConsole.viewStory();
                ViewConsole.viewMessage("Good luck. Hit Enter to start the game.");
                exitString = getNextLine();
                if (exitString.equals("exit"))
                    break;
                GameplayControllerConsole.gameBegin(model);
                exitString = "exit";
            }
        }
        ViewConsole.viewMessage("Thanks for playing the game");
        try {
            reader.close();
        } catch (IOException e) {
            ViewConsole.viewException(e.getMessage());
        }
    }

    static String getNextLine() {
        String line;
        try {
            line = reader.readLine();
            if (line == null) {
                return "exit";
            }
            return line;
        }
        catch (IOException e) {
            ViewConsole.viewException(e.getMessage());
            return "exit";
        }
    }

    private boolean runHeroSelection(String str) throws UnsupportedLookAndFeelException {
        switch (str) {
            case "1" -> {
                if (!this.model.getStoredHeroes().isEmpty()) {
                    viewStartScreen = false;
                    viewSelectHeroScreen = true;
                } else
                    ViewConsole.viewMessage("There is no heroes in the database, create a new one.");
            }
            case "2" -> {
                viewStartScreen = false;
                viewChooseAName = true;
            }
            case "3" -> {
                ViewConsole.viewMessage("GUI view is open!");
                UIManager.setLookAndFeel(new NimbusLookAndFeel());
                MainView mainView = new MainView();
                ControllerGUI controllerGUI = new ControllerGUI(mainView, new Model(), false);
                return true;
            }
            default -> ViewConsole.viewInputNotRecognized(str);
        }
        return false;
    }

    private boolean checkIfIndexExist(String index) throws Exception {
        try {
            int indexInt = Integer.parseInt(index);
            return indexInt > 0 && indexInt <= this.model.getStoredHeroes().size() && index.indexOf(0) != '+';
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void runSelectHeroFromDatabase(String str) throws Exception {
        if (this.checkIfIndexExist(str)) {
            try {
                this.model.selectHero(str);
                if (this.model.getHero() != null) {
                    viewSelectHeroScreen = false;
                    viewGameBeginScreen = true;
                }
                else
                    ViewConsole.viewMessage("Error: can't select this hero.");
            } catch (Exception e) {
                ViewConsole.viewMessage("FATAL ERROR2: Database corrupted.");
                ViewConsole.viewException(e.getMessage());
            }
        } else {
            ViewConsole.viewInputNotRecognized(str);
        }
    }

    private void runChooseAName(String str, List<String> userInput) throws Exception {
        userInput.add(str);
        int size;
        try {
            size = this.model.getHeroViaName(userInput.get(0)).size();
            if (size == 0) {
                viewChooseAName = false;
                viewChooseHeroClass = true;
            }
            else {
                userInput.clear();
                ViewConsole.viewMessage("This hero name is already taken, please pick a new one");
            }
        } catch (Exception e) {
            userInput.clear();
            ViewConsole.viewMessage("FATAL ERROR3: Database corrupted.");
            ViewConsole.viewException(e.getMessage());
        }
    }
    private void runChooseHeroClass(String str, List<String> userInput) throws Exception {
        int indexClass;
        if (checkIfIndexClassExist(str)) {
            indexClass = Integer.parseInt(str);
            switch (indexClass) {
                case 1:
                    userInput.add("Halfling");
                case 2:
                    userInput.add("Higher Vampire");
                case 3:
                    userInput.add("Sorceress");
                case 4:
                    userInput.add("Witcher");
                default:
                    userInput.add("");
            }

            try {
                this.model.createHero(userInput.get(0), userInput.get(1));
                if (this.model.getHero() != null) {
                    this.model.storeHero();
                    viewChooseHeroClass = false;
                    viewGameBeginScreen = true;
                }
                else {
                    ViewConsole.viewMessage("ERROR: Can't create the new hero.");
                    viewChooseHeroClass = false;
                    viewChooseAName = true;
                }
                userInput.clear();
            } catch (Exception e) {
                ViewConsole.viewMessage("ERROR: Can't create the new hero.");
                ViewConsole.viewException(e.getMessage());
                userInput.clear();
                viewChooseHeroClass = false;
                viewChooseAName = true;
            }
        } else {
            ViewConsole.viewInputNotRecognized(str);
        }
    }

    private boolean checkIfIndexClassExist(String str) {
        try {
            int index = Integer.parseInt(str);
            return index > 0 && index <= 4 && str.indexOf(0) != '+';
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
