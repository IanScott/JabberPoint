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
	
	public ProjectorGUI create(ProjectorController projectorController) {
		ProjectorVariant variant = ProjectorVariantFactory.getInstance().create(); // load gui variant data
		
		ProjectorGUI gui = new SwingProjectorGUI(
				variant.getDrawStrategy(),
				projectorController,
				variant.getConfiguration(),
				variant.getContext()
				);
		
		
		gui.setMenubar(variant.getMenubar());
		gui.setWindowlistener(variant.getWindowlistener());
		gui.setKeylistener(variant.getKeylistener());
		gui.setMouseListener(variant.getMouseListener());
		gui.setMouseInputListener(variant.getMouseInputListener());
		
		return gui;
	}
}
