package nl.ou.jp.gui.implementation;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.ProjectorGUI;
import nl.ou.jp.gui.model.ProjectorConfiguration;
import nl.ou.jp.gui.model.ProjectorContext;

public class ProjectorContextImp implements ProjectorContext {

	private ProjectorController projectorController = null;
	private ProjectorGUI projectorGUI = null;
	private ProjectorConfiguration configurationDefault = null;
	
	@Override
	public ProjectorController getController() {
		return this.projectorController;
	}

	@Override
	public void setController(ProjectorController projectorController) {
		this.projectorController = projectorController;
	}

	@Override
	public ProjectorGUI getMainGUI() {
		return this.projectorGUI;
	}

	@Override
	public void setMainGUI(ProjectorGUI projectorGUI) {
		this.projectorGUI = projectorGUI;
	}

	@Override
	public ProjectorConfiguration getConfiguration() {
		return this.configurationDefault;
	}
	
	@Override
	public void setConfiguration(ProjectorConfiguration configurationDefault) {
		this.configurationDefault = configurationDefault;
	}
}
