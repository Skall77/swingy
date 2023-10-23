package com.gmail.aaudevar.view.gui;

import com.gmail.aaudevar.model.artefact.Artefact;
import com.gmail.aaudevar.model.hero.Hero;
import com.gmail.aaudevar.model.villain.Villain;

import java.util.List;

public interface WindowInterface {
    public void setEnableStartButton(boolean b);
    public void viewStartScreen();
    public void viewSelectScreen(List<List<String>> heroList);
    public void viewCreateScreen();
    public void viewGameView(Hero hero);
    public void updateGameViewHeroStats(Hero hero);
    public void viewGameInfo(String info);
    public void viewGameInfo(String info, Villain villain);
    public void viewGameInfo(String info, Hero hero, Artefact artefact);
    public void viewErrorOnStart(String error);
    public void disableStartSelectScreen();
    public void setMovementEnabled(boolean b);
    public void setFightRunEnabled(boolean b);
    public void setPickupLeaveEnabled(boolean b);
    public void setRollDiceButtonEnabled(boolean b);
    public void setTextEncounter();
    public void setActionListenerButton();
    public void resetMovementListenerButton();
    public void setTextMovement();
    public void endGameMessage(String title, String message);
    public void quitGame();
    public void changeView();
    public void viewErrorDialog(String error);
    public void maxLevelMessage(String title, String message);

}
