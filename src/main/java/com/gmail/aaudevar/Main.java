package com.gmail.aaudevar;


import com.gmail.aaudevar.controller.ControllerConsole;
import com.gmail.aaudevar.controller.ControllerGUI;
import com.gmail.aaudevar.model.Model;
import com.gmail.aaudevar.view.gui.MainView;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;


public class Main
{
    public static void main( String[] args ) throws Exception {
		if (args.length != 1) {
			System.out.println("Usage : java -jar swingy.jar console");
			System.out.println(("or : java -jar swingy.jar gui"));
		}
		else if (args[0].equals("console")) {
			ControllerConsole controllerConsole = new ControllerConsole(new Model());
		}
		else if (args[0].equals("gui")) {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			MainView mainView = new MainView();
			ControllerGUI controllerGUI = new ControllerGUI(mainView, new Model(), true);
		}
		else {
			System.out.println("Usage : java -jar swingy.jar console");
			System.out.println(("or : java -jar swingy.jar gui"));
		}

	}
}
