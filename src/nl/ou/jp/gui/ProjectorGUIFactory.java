package nl.ou.jp.gui;

import nl.ou.jp.gui.implementation.*;

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
	
	public ProjectorGUI create(ProjectorVariant variant) {
		ProjectorGUI gui = new ProjectorGUIImp(
				variant.getDrawStrategy(),
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
