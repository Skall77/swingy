package com.gmail.aaudevar.view.gui;

import com.gmail.aaudevar.controller.ControllerGUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SelectScreen extends JPanel implements ActionListener {
    JLabel screenTitle;
    JLabel heroStatsLabel;
    JButton selectHeroButton;
    int heroId;
    private List<List<String>> heroList;

    public SelectScreen(List<List<String>> heroList) {
        super(new BorderLayout());
        this.heroList = heroList;
        String[] heroTitles = new String[this.heroList.size()];
        final String[] heroIds = new String[this.heroList.size()];
        StringBuilder heroString = new StringBuilder();

        for (int i = 0; i < this.heroList.size(); i++) {
            heroString.append(this.heroList.get(i).get(1));
            heroIds[i] = this.heroList.get(i).get(0);
            heroTitles[i] = heroString.toString();
            heroString.delete(0, heroString.length());
        }

        JComboBox<String> selectHeroMenu = new JComboBox<>(heroTitles);
        selectHeroMenu.setSelectedIndex(0);
        heroId = selectHeroMenu.getSelectedIndex();
        selectHeroMenu.addActionListener(this);
        selectHeroMenu.setBackground(new Color(130, 0, 0));
        selectHeroMenu.setForeground(new Color(220,220,220));

        screenTitle = new JLabel();
        selectHeroButton = new JButton();
        heroStatsLabel = new JLabel();

        JPanel subPanelCenter = new JPanel();
        JPanel menuPanel = new JPanel();
        subPanelCenter.setLayout(new BoxLayout(subPanelCenter, BoxLayout.Y_AXIS));

        screenTitle.setText("Choose Your Hero !");
        screenTitle.setFont(screenTitle.getFont().deriveFont(Font.BOLD));
        screenTitle.setPreferredSize(new Dimension(600,50));
        screenTitle.setForeground(new Color(220,220,220));
        selectHeroButton.setText("Select Hero");
        selectHeroButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectHeroButton.setBackground(new Color(130, 0, 0));
        selectHeroButton.setForeground(new Color(220,220,220));
        screenTitle.setHorizontalAlignment(JLabel.CENTER);
        displayHeroStats(heroId);

        heroStatsLabel.setPreferredSize(new Dimension(100, 150));
        heroStatsLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        heroStatsLabel.setForeground(new Color(220,220,220));
        heroStatsLabel.setBackground(new Color(17, 17, 17));
        menuPanel.add(selectHeroMenu);
        menuPanel.setBackground(new Color(17, 17, 17));

        subPanelCenter.add(menuPanel);
        subPanelCenter.add(heroStatsLabel);
        subPanelCenter.add(selectHeroButton);
        subPanelCenter.setBackground(new Color(17, 17, 17));

        add(screenTitle, BorderLayout.PAGE_START);
        add(subPanelCenter, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        setBackground(new Color(17, 17, 17));

        selectHeroButton.addActionListener((e -> ControllerGUI.handler("SELECT_HERO_BY_ID", heroIds[heroId])));
    }

    public void actionPerformed(ActionEvent e) {
        @SuppressWarnings("unchecked")
        JComboBox<String> cb = (JComboBox<String>) e.getSource();
        heroId = cb.getSelectedIndex();
        displayHeroStats(heroId);
    }

    protected void displayHeroStats(int index) {
        String heroStatsString = heroList.get(index).get(1)
                + " Level " + heroList.get(index).get(6)
                + " " + heroList.get(index).get(2)
                + " \nAttack: " + heroList.get(index).get(8)
                + " \nDefense: " + heroList.get(index).get(9)
                + " \nMax HP: " + heroList.get(index).get(10)
                + "\nxp: " + heroList.get(index).get(7);
        heroStatsLabel.setText("<html>" + heroStatsString.replaceAll("<","&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\n", "<br/>")
                + "</html>");
    }
}
