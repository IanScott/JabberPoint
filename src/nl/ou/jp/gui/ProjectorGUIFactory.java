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
		return new ProjectorGUIImp(
				variant.getDrawStrategy(),
				variant.getMenubar(),
				variant.getWindowlistener(),
				variant.getKeylistener(),
				variant.getMouseInputAdapter(),
				variant.getConfiguration(),
				variant.getContext()
				);
	}
}
