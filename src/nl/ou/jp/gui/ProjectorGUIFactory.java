package nl.ou.jp.gui;

import java.awt.MenuBar;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.implementation.*;
import nl.ou.jp.gui.model.*;

public class ProjectorGUIFactory {	
	private static ProjectorGUIFactory instance = null;
	private ProjectorController projectorController = null;
	
	public static ProjectorGUIFactory getInstance() {
		if(instance == null) {
			instance = new ProjectorGUIFactory();
		}
		return instance;
	}
		
	private ProjectorGUIFactory() {
		//singleton
	}
	
	public ProjectorGUI create(ProjectorController projectorController) {
		this.projectorController = projectorController;
		return createDefaultProjectFrame();
	}
	
	private ProjectorGUI createDefaultProjectFrame() {		
		ProjectorVariant variant = new DefaultVariant(this.projectorController);
		
		ProjectorConfiguration configuration = variant.createConfiguration();
		MenuBar menubar = variant.createMenubar();
		WindowListener windowlistener = variant.createWindowlistener();
		KeyListener keylistener = variant.createKeylistener();
		DrawStrategy strategy = variant.createDrawStrategy();
		ProjectorContext projectorContext = variant.createContext();

		ProjectorGUIImp projectorView = new ProjectorGUIBuilder()
			.withStrategy(strategy)		
			.withKeyListener(keylistener) 
			.withWindowListener(windowlistener)
			.withMenuBar(menubar)
			.withConfiguration(configuration)
			.build();
		
		projectorView.initialize(projectorContext);
		
		return projectorView;
	}
	

}
