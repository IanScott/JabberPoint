package nl.ou.jp.gui.implementation;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.model.ProjectorVariant;

public class ProjectorVariantFactory {

	private static ProjectorVariantFactory instance = null;
	
	public static ProjectorVariantFactory getInstance() {
		if(instance == null) {
			instance = new ProjectorVariantFactory();
		}
		return instance;
	}
	
	private ProjectorVariantFactory() {
		//singleton
	}
	
	public ProjectorVariant create(ProjectorController projectorController) {
		return new DefaultVariant(projectorController);
	}
}
