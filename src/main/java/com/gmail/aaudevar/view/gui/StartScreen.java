package com.gmail.aaudevar.view.gui;

import com.gmail.aaudevar.controller.ControllerGUI;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JPanel {
    private JButton selectHeroButton;
    private JButton createHeroButton;
    private JButton changeViewButton;
    private JLabel startScreenTitle;
    private final boolean b = ControllerGUI.firstStart();


    StartScreen() {
        super(new BorderLayout());

        startScreenTitle = new JLabel();
        selectHeroButton = new JButton();
        createHeroButton = new JButton();
        changeViewButton = new JButton();
        JPanel subPanelOne = new JPanel();
        JPanel subPanelTwo = new JPanel();

        startScreenTitle.setText("Select An Old Hero Or Create A New One ?");
        startScreenTitle.setFont(startScreenTitle.getFont().deriveFont(Font.BOLD));
        startScreenTitle.setForeground(new Color(220,220,220));
        startScreenTitle.setHorizontalAlignment(JLabel.CENTER);
        startScreenTitle.setBorder(BorderFactory.createEmptyBorder(10,10,0,10));
        startScreenTitle.setPreferredSize(new Dimension(600,50));

        selectHeroButton.setText("Select old Hero");
        selectHeroButton.setForeground(new Color(220,220,220));
        selectHeroButton.setBackground(new Color(130, 0, 0));
        subPanelOne.add(selectHeroButton);
        subPanelOne.setBackground(new Color(17, 17, 17));
        createHeroButton.setText("Create a new Hero");
        createHeroButton.setForeground(new Color(220,220,220));
        createHeroButton.setBackground(new Color(130, 0, 0));
        changeViewButton.setText("Change View (Console)");
        changeViewButton.setForeground(new Color(220,220,220));
        changeViewButton.setBackground(new Color(130, 0, 0));
        subPanelTwo.add(createHeroButton);
        if (b)
            subPanelTwo.add(changeViewButton);
        subPanelTwo.setBackground(new Color(17, 17, 17));

        add(startScreenTitle,BorderLayout.PAGE_START);
        add(subPanelOne, BorderLayout.CENTER);
        add(subPanelTwo, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setBackground(new Color(17, 17, 17));

        selectHeroButton.addActionListener((e -> ControllerGUI.handler("VIEW_SELECT")));

        createHeroButton.addActionListener(e -> ControllerGUI.handler("VIEW_CREATE_SCREEN"));

        changeViewButton.addActionListener(e -> ControllerGUI.handler("CHANGE_VIEW"));
    }
}
