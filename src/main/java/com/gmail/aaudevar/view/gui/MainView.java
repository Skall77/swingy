package com.gmail.aaudevar.view.gui;


import com.gmail.aaudevar.controller.ControllerGUI;
import com.gmail.aaudevar.model.artefact.Artefact;
import com.gmail.aaudevar.model.hero.Hero;
import com.gmail.aaudevar.model.villain.Villain;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.List;

public class MainView extends JFrame implements WindowInterface {
    private JPanel mainPanel;
    private JLabel errorOnStart;
    private JButton startScreenButton;
    private StartScreen startScreen;
    private SelectScreen selectScreen;
    private CreateScreen createScreen;
    private GameView gameView;

    public MainView() {
        initView();
    }

    private void initView() {
        mainPanel = new JPanel(new BorderLayout());
        JPanel subPanelCenter = new JPanel();
        subPanelCenter.setLayout(new BoxLayout(subPanelCenter, BoxLayout.Y_AXIS));

        errorOnStart = new JLabel();
        errorOnStart.setBackground(new Color(17, 17, 17));
        startScreenButton = new JButton();
        startScreenButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel logo = new JLabel();
        ImageIcon logoImage = new ImageIcon("src/main/ressource/logo.png");
        logo.setIcon(logoImage);
        logo.setHorizontalAlignment(JLabel.CENTER);
        logo.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setTitle("Swingy: a Witcher RPG");
        this.setBackground(new Color(17, 17, 17));
        startScreenButton.setText("Let the game begin!");
        startScreenButton.setBorderPainted(true);
        startScreenButton.setBackground(new Color(130, 0, 0));
        startScreenButton.setForeground(new Color(220,220,220));
        startScreenButton.setEnabled(false);


        startScreenButton.addActionListener(e -> ControllerGUI.handler("VIEW_START_SCREEN"));

        subPanelCenter.add(startScreenButton);
        subPanelCenter.setBackground(new Color(17, 17, 17));
        mainPanel.add(logo, BorderLayout.PAGE_START);
        mainPanel.add(subPanelCenter, BorderLayout.CENTER);
        mainPanel.add(errorOnStart, BorderLayout.PAGE_END);
        mainPanel.setBackground(new Color(17, 17, 17));
        this.add(mainPanel);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setBackground(Color.black);
    }

    public void setEnableStartButton(boolean b) {
        startScreenButton.setEnabled(b);
    }

    public void setActionListenerButton(){ gameView.setActionListenerButton();}

    public void resetMovementListenerButton(){ gameView.resetMovementListenerButton();}

    public void viewStartScreen() {
        startScreen = new StartScreen();
        setContentPane(startScreen);
        pack();
    }

    public void viewSelectScreen(List<List<String>> heroList) {
        selectScreen = new SelectScreen(heroList);
        setContentPane(selectScreen);
        pack();
    }

    public void viewCreateScreen() {
        createScreen = new CreateScreen();
        setContentPane(createScreen);
        pack();
    }

    public void viewCreateScreenError(String str) {
        String errorReason = "There was an error creating your hero.\n"
                + str
                + "Please try again";
        createScreen.setCreateScreenError("<html>" + errorReason.replaceAll("<","&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\n", "<br/>")
                + "</html>");
    }

    public void viewGameView(Hero hero) {
        gameView = new GameView(hero);
        setContentPane(gameView);
        pack();
    }

    public void updateGameViewHeroStats(Hero hero) {
        gameView.setHeroStatsLabel(hero);
    }

    private static String labelFormatter(String input) {
        return "<html>" + input.replaceAll("<","&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\n", "<br/>")
                + "</html>";
    }

    public void viewGameInfo(String info) {
        gameView.setGameInfoLabel(labelFormatter(info));
    }

    public void viewGameInfo(String info, Villain villain) {
        String s= "You encounter a " + villain.getName() + "\nAttack :" + villain.getAttack()
                + " Defense :" + villain.getDefense() + " \nHP: " + villain.getCurrentHP()
                + "\nFight or Run?";
        gameView.setGameInfoLabel(labelFormatter(s));
    }

    public void viewGameInfo(String info, Hero hero, Artefact artefact) {
        StringBuilder artefactInfo = new StringBuilder();
        if (artefact != null) {
            switch (artefact.getType()) {
                case "weapon" -> artefactInfo.append("The villain dropped an artefact :" + "\n")
                        .append(artefact.getName()).append(" which give an extra ").append(artefact.getModifier())
                        .append(" attack.");
                case "armor" -> artefactInfo.append("The villain dropped an artefact :" + "\n")
                        .append(artefact.getName()).append(" which give an extra ")
                        .append(artefact.getModifier()).append(" defense.");
                case "helm" -> artefactInfo.append("The villain dropped an artefact :" + "\n")
                        .append(artefact.getName()).append(" which give an extra ")
                        .append(artefact.getModifier()).append(" health point.");
            }

            artefactInfo.append("\nYou currently are equipped with :\n");

            switch (artefact.getType()) {
                case "weapon" -> artefactInfo.append(hero.getWeapon().getName()).append(" which give an extra ")
                        .append(hero.getWeapon().getModifier()).append(" attack.");
                case "armor" -> artefactInfo.append(hero.getArmor().getName()).append(" which give an extra ")
                        .append(hero.getArmor().getModifier()).append(" defense.");
                case "helm" -> artefactInfo.append(hero.getHelm().getName()).append(" which give an extra ")
                        .append(hero.getHelm().getModifier()).append(" health point.");
            }

            artefactInfo.append("\nPick the new Artefact of keep the old one ?");

        }
        else
            artefactInfo.append(" ");
        gameView.setGameInfoLabel(labelFormatter(info + "\n" + artefactInfo.toString()));
    }

    public void viewErrorOnStart(String error) {
        errorOnStart.setText(
                "<html>" + error.replaceAll("<","&lt;")
                        .replaceAll(">", "&gt;")
                        .replaceAll("\n", "<br/>")
                        + "</html>"
        );
    }

    public void disableStartSelectScreen() {
        startScreenButton.setEnabled(false);
    }

    public void setMovementEnabled(boolean b) {
        gameView.setMovementButtonsEnabled(b);
    };

    public void setFightRunEnabled(boolean b) {
        gameView.setFightRunButtonsEnabled(b);
    };

    public void setPickupLeaveEnabled(boolean b) {
        gameView.setPickUpLeaveButtonsEnabled(b);
    }

    @Override
    public void setRollDiceButtonEnabled(boolean b) { gameView.setRollDiceButtonEnabled(b);}

    public void setTextEncounter() { gameView.setTextEncounterButtons();}

    public void setTextMovement() { gameView.setTextMovementButtons(); }

    public void endGameMessage(String title, String message) {
        UIManager.put("OptionPane.messageForeground", new Color(130, 0, 0));
        UIManager.put("OptionPane.background", new Color(17, 17, 17));
        UIManager.put("Panel.background", new Color(17, 17, 17));
        ImageIcon icon = new ImageIcon("src/main/ressource/icon.png");
        String question = message + "\nPlay again?";
        Object[] options = {"Yes!", "Exit"};
        int n = JOptionPane.showOptionDialog(
                this,
                labelFormatter(question),
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon,
                options,
                options[0]
        );
        if (n == JOptionPane.NO_OPTION) {
            ControllerGUI.handler("EXIT");
        }
        else if (n == JOptionPane.YES_OPTION) {
            ControllerGUI.handler("PLAY_AGAIN");
        }
    }

    public void maxLevelMessage(String title, String message) {
        UIManager.put("OptionPane.messageForeground", new Color(130, 0, 0));
        UIManager.put("OptionPane.background", new Color(17, 17, 17));
        UIManager.put("Panel.background", new Color(17, 17, 17));
        ImageIcon icon = new ImageIcon("src/main/ressource/icon.png");
        String question = message + "\nYou played for so long! Did you have fun ?";
        Object[] options = {"Yes!", "No"};
        int option = JOptionPane.showOptionDialog(
                this,
                labelFormatter(question),
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon,
                options,
                options[0]
        );
        if (option == JOptionPane.NO_OPTION) {
            ControllerGUI.handler("EXIT");
        }
        else if (option == JOptionPane.YES_OPTION) {
            ControllerGUI.handler("EXIT");
        }
    }


    public void quitGame() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public void changeView() {
        this.dispose();
    }

    public void viewErrorDialog(String error) {
        UIManager.put("OptionPane.messageForeground", new Color(130, 0, 0));
        UIManager.put("OptionPane.background", new Color(17, 17, 17));
        UIManager.put("Panel.background", new Color(17, 17, 17));
        JOptionPane op = new JOptionPane(labelFormatter(error), JOptionPane.ERROR_MESSAGE, JOptionPane.DEFAULT_OPTION);
        getComponents(op);
        op.createDialog(this, "Error Message").setVisible(true);
    }

    private void getComponents(Container c){
        Component[] m = c.getComponents();
        for (Component component : m) {
            if (component.getClass().getName().equals("javax.swing.JPanel"))
                component.setBackground(new Color(17, 17, 17));
            if (c.getClass().isInstance(component))
                getComponents((Container) component);
        }
    }

}
