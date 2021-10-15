package nl.ou.jp.gui;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.implementation.*;
import nl.ou.jp.gui.model.ProjectorVariant;

public class ProjectorGUIFactory {	
	private static ProjectorGUIFactory instance = null;
	
	public static ProjectorGUIFactory getInstance() {
		if(instance == null) {
			instance = new ProjectorGUIFactory();
		}
		return instance;
	}
		
	private ProjectorGUIFactory() {
		//singleton
	}
	
	public ProjectorGUI create() {
		ProjectorVariant variant = ProjectorVariantFactory.getInstance().create(); // load gui variant data
		
		ProjectorGUI gui = new SwingProjectorGUI();
		gui.setProjectorConfiguration(variant.getConfiguration());
		gui.setProjectorMediator(variant.getMediator());
		
		gui.setDrawStrategy(variant.getDrawStrategy());
		gui.setMenubar(variant.getMenubar());
		gui.setWindowlistener(variant.getWindowlistener());
		gui.setKeylistener(variant.getKeylistener());
		gui.setMouseListener(variant.getMouseListener());
		gui.setMouseInputListener(variant.getMouseInputListener());
		
		return gui;
	}
	
	public ProjectorGUI create(ProjectorController projectorController) {
		ProjectorGUI gui = create();
		gui.setProjectorController(projectorController);
		return gui;
	}
}
