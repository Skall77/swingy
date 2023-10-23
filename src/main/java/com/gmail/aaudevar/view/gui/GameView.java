package com.gmail.aaudevar.view.gui;

import com.gmail.aaudevar.controller.ControllerGUI;
import com.gmail.aaudevar.model.hero.Hero;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.BorderFactory.createEmptyBorder;

public class GameView extends JPanel {

    private JButton leaveItemButton;
    private JButton moveNorthButton;
    private JButton moveSouthButton;
    private JButton moveEastButton;
    private JButton moveWestButton;
    private JButton fightButton;
    private JButton runButton;
    private JButton pickItemButton;
    private JButton rollDiceButton;
    private JLabel gameInfoLabel;
    private JLabel heroStatsLabel;
    private JLabel gameViewTitleLabel;
    private JLabel errorLabel;
    private JScrollPane scroller;

    GameView(Hero hero) {
        InitGameView(hero);
    }

    private void InitGameView(Hero hero) {
        gameInfoLabel = new JLabel();
        heroStatsLabel = new JLabel();
        moveNorthButton = new JButton();
        moveSouthButton = new JButton();
        moveEastButton = new JButton();
        moveWestButton = new JButton();
        fightButton = new JButton();
        runButton = new JButton();
        gameViewTitleLabel = new JLabel();
        errorLabel = new JLabel();
        pickItemButton = new JButton();
        leaveItemButton = new JButton();
        rollDiceButton = new JButton();

        setBackground(new Color(17, 17, 17));
        gameInfoLabel.setBackground(new Color(130, 0, 0));
        gameInfoLabel.setForeground(new Color(220,220,220));
        gameInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gameInfoLabel.setVerticalAlignment(SwingConstants.CENTER);
        gameInfoLabel.setAutoscrolls(true);
        gameInfoLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

        scroller = new JScrollPane(gameInfoLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroller.getViewport().setBackground(new Color(17, 17, 17));
        scroller.getViewport().setForeground(new Color(220,220,220));
        scroller.getVerticalScrollBar().setBackground(new Color(130, 0, 0));
        scroller.getHorizontalScrollBar().setBackground(new Color(130, 0, 0));
        scroller.setBorder(createEmptyBorder());

        scroller.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(17, 17, 17);
            }
        });

        scroller.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(17, 17, 17);
            }
        });


        heroStatsLabel.setBackground(new Color(130, 0, 0));
        heroStatsLabel.setForeground(new Color(220,220,220));
        setHeroStatsLabel(hero);
        heroStatsLabel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

        moveNorthButton.setText("N");
        moveSouthButton.setText("S");
        moveEastButton.setText("E");
        moveWestButton.setText("W");
        fightButton.setText("Fight");
        runButton.setText("Run");
        rollDiceButton.setText("Roll Dice");
        setButtonColor(moveNorthButton);
        setButtonColor(moveSouthButton);
        setButtonColor(moveEastButton);
        setButtonColor(moveWestButton);
        setButtonColor(fightButton);
        setButtonColor(runButton);
        setButtonColor(rollDiceButton);
        gameViewTitleLabel.setText("What to do next...");
        gameViewTitleLabel.setForeground(new Color(220,220,220));
        pickItemButton.setText("Pick the item");
        leaveItemButton.setText("Leave the item");
        setButtonColor(pickItemButton);
        setButtonColor(leaveItemButton);

        GroupLayout jPanel2Layout = new GroupLayout(this);
        this.setLayout(jPanel2Layout);

        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(242)
                                .addComponent(gameViewTitleLabel)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(17)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(heroStatsLabel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(39)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                        .addGap(50)
                                                                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                .addComponent(moveSouthButton)
                                                                                .addComponent(moveNorthButton)))
                                                                .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                                        .addComponent(moveWestButton)
                                                                        .addGap(56)
                                                                        .addComponent(moveEastButton)))
                                                        .addComponent(fightButton))
                                                .addGap(27)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(runButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(rollDiceButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(pickItemButton)
                                                        .addGap(18)
                                                        .addComponent(leaveItemButton)))
                                        .addComponent(scroller, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE))
                                .addGap(22))
        );

        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16)
                                .addComponent(gameViewTitleLabel)
                                .addGap(54)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(heroStatsLabel, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                                                .addGap(35)
                                                .addComponent(moveNorthButton)
                                                .addGap(3)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(moveEastButton)
                                                        .addComponent(moveWestButton))
                                                .addGap(4)
                                                .addComponent(moveSouthButton))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(306)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(fightButton)
                                                        .addComponent(runButton))
                                                .addGap(18)
                                                .addComponent(errorLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(scroller, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
                                                .addGap(32)
                                                .addComponent(rollDiceButton)
                                                .addGap(18)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(pickItemButton)
                                                        .addComponent(leaveItemButton))))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        moveNorthButton.addActionListener(e -> ControllerGUI.movementHandler("MOVE_NORTH"));
        moveSouthButton.addActionListener(e -> ControllerGUI.movementHandler("MOVE_SOUTH"));
        moveEastButton.addActionListener(e -> ControllerGUI.movementHandler("MOVE_EAST"));
        moveWestButton.addActionListener(e -> ControllerGUI.movementHandler("MOVE_WEST"));

        fightButton.addActionListener(e -> ControllerGUI.fightHandler("START_FIGHT"));

        runButton.addActionListener(e ->  ControllerGUI.fightHandler("RUN"));

        rollDiceButton.addActionListener(e ->  ControllerGUI.fightHandler("ROLL DICE"));

        pickItemButton.addActionListener(e -> ControllerGUI.handler("PICKUP_ITEM"));

        leaveItemButton.addActionListener(e -> ControllerGUI.handler("LEAVE_ITEM"));
    }

    public void setActionListenerButton() {
        moveNorthButton.removeActionListener(moveNorthButton.getActionListeners()[0]);
        moveSouthButton.removeActionListener(moveSouthButton.getActionListeners()[0]);
        moveEastButton.removeActionListener(moveEastButton.getActionListeners()[0]);
        moveWestButton.removeActionListener(moveWestButton.getActionListeners()[0]);
        moveNorthButton.addActionListener(e -> ControllerGUI.encounterHandler("1"));
        moveSouthButton.addActionListener(e -> ControllerGUI.encounterHandler("4"));
        moveEastButton.addActionListener(e -> ControllerGUI.encounterHandler("3"));
        moveWestButton.addActionListener(e -> ControllerGUI.encounterHandler("2"));
    }

    public void resetMovementListenerButton() {
        setTextMovementButtons();
        moveNorthButton.removeActionListener(moveNorthButton.getActionListeners()[0]);
        moveSouthButton.removeActionListener(moveSouthButton.getActionListeners()[0]);
        moveEastButton.removeActionListener(moveEastButton.getActionListeners()[0]);
        moveWestButton.removeActionListener(moveWestButton.getActionListeners()[0]);
        moveNorthButton.addActionListener(e -> ControllerGUI.movementHandler("MOVE_NORTH"));
        moveSouthButton.addActionListener(e -> ControllerGUI.movementHandler("MOVE_SOUTH"));
        moveEastButton.addActionListener(e -> ControllerGUI.movementHandler("MOVE_EAST"));
        moveWestButton.addActionListener(e -> ControllerGUI.movementHandler("MOVE_WEST"));
    }

    public void setHeroStatsLabel(Hero hero) {
        String s = "<html><b>" + hero.getName() + "</b> " + hero.getType() + "<br/>Attack: " + hero.getAttack()
                + "<br/>Defense: " + hero.getDefense() + "<br/>Max HP: " + hero.getMaxHP()
                + "<br/>Current HP: " + hero.getCurrentHP() +
                "<br/>Level : " + hero.getLevel() + "<br/>XP : " + hero.getXp() + "</html>";
        heroStatsLabel.setText(s);
    }

    private void setButtonColor(JButton button) {
        button.setBackground(new Color(130, 0, 0));
        button.setForeground(new Color(220,220,220));
    }

    public void setMovementButtonsEnabled(boolean b) {
        moveNorthButton.setEnabled(b);
        moveSouthButton.setEnabled(b);
        moveEastButton.setEnabled(b);
        moveWestButton.setEnabled(b);
    }

    public void setTextEncounterButtons() {
        moveNorthButton.setText("1");
        moveSouthButton.setText("4");
        moveEastButton.setText("3");
        moveWestButton.setText("2");
    }

    public void setTextMovementButtons() {
        moveNorthButton.setText("N");
        moveSouthButton.setText("S");
        moveEastButton.setText("E");
        moveWestButton.setText("W");
    }

    public void setFightRunButtonsEnabled(boolean b) {
        fightButton.setEnabled(b);
        runButton.setEnabled(b);
    }

    public void setPickUpLeaveButtonsEnabled(boolean b) {
        pickItemButton.setEnabled(b);
        leaveItemButton.setEnabled(b);
    }

    public void setRollDiceButtonEnabled(boolean b) {
        rollDiceButton.setEnabled(b);
    }

    public void setGameInfoLabel(String info) {

        gameInfoLabel.setText(info);
    }

    public void setGameViewErrorLabel(String error) {
        errorLabel.setText(error);
    }


}
