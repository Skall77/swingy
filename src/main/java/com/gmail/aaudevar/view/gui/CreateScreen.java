package com.gmail.aaudevar.view.gui;

import com.gmail.aaudevar.controller.ControllerGUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateScreen extends JPanel {
    private JLabel titleLabel;
    private JLabel menuLabel;
    private JLabel userTextLabel;
    private JLabel errorLabel;
    private JComboBox<String> heroClassesMenu;
    private JButton createHeroButton;
    private JTextField userTextInputField;
    private JPanel subPanelCenter;
    private JPanel subPanelOne;
    private JPanel subPanelTwo;
    private String userInput = "";
    private String userClassChoice;
    private final String[] heroClasses = {"Halfling", "Higher Vampire", "Sorceress", "Witcher"};

    CreateScreen() {
        super(new BorderLayout());

        titleLabel = new JLabel("Create A New Hero");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD));
        titleLabel.setPreferredSize(new Dimension(600, 50));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setBackground(new Color(17, 17, 17));
        titleLabel.setForeground(new Color(220,220,220));

        menuLabel = new JLabel("Choose your class:");
        menuLabel.setBackground(new Color(17, 17, 17));
        menuLabel.setForeground(new Color(220,220,220));
        userTextLabel = new JLabel("Enter your name:");
        userTextLabel.setBackground(new Color(17, 17, 17));
        userTextLabel.setForeground(new Color(220,220,220));
        errorLabel = new JLabel();
        errorLabel.setPreferredSize(new Dimension(500, 80));
        errorLabel.setBackground(new Color(17, 17, 17));
        errorLabel.setForeground(new Color(220,220,220));

        userTextInputField = new JTextField(14);
        userTextInputField.setBackground(new Color(130, 0, 0));
        userTextInputField.setForeground(new Color(220,220,220));
        heroClassesMenu = new JComboBox<>(heroClasses);
        heroClassesMenu.setSelectedIndex(0);
        heroClassesMenu.setBackground(new Color(130, 0, 0));
        heroClassesMenu.setForeground(new Color(220,220,220));
        userClassChoice = heroClasses[heroClassesMenu.getSelectedIndex()];

        createHeroButton = new JButton("Create Hero");
        createHeroButton.setBackground(new Color(130, 0, 0));
        createHeroButton.setForeground(new Color(220,220,220));
        createHeroButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        subPanelOne = new JPanel();
        subPanelOne.setBackground(new Color(17, 17, 17));
        subPanelTwo = new JPanel();
        subPanelTwo.setBackground(new Color(17, 17, 17));
        subPanelCenter = new JPanel();
        subPanelCenter.setBackground(new Color(17, 17, 17));

        subPanelOne.add(userTextLabel);
        subPanelOne.add(userTextInputField);

        subPanelTwo.add(menuLabel);
        subPanelTwo.add(heroClassesMenu);

        subPanelCenter.add(subPanelOne);
        subPanelCenter.add(subPanelTwo);
        subPanelCenter.add(createHeroButton);

        add(titleLabel, BorderLayout.PAGE_START);
        add(subPanelCenter, BorderLayout.CENTER);
        add(errorLabel, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(17, 17, 17));

        createHeroButton.addActionListener(e -> ControllerGUI.handler("CREATE_NEW_HERO",userInput,userClassChoice));

        heroClassesMenu.addActionListener(this::ListenCheckBox);

        userTextInputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                userInput = userTextInputField.getText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                userInput = userTextInputField.getText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                userInput = userTextInputField.getText();
            }
        });
    }

    private void ListenCheckBox(ActionEvent e) {
        @SuppressWarnings("unchecked")
        JComboBox<String> cb = (JComboBox<String>) e.getSource();
        userClassChoice = heroClasses[cb.getSelectedIndex()];
    }

    public void setCreateScreenError(String str) {
        errorLabel.setText(str);
    }
}
