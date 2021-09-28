package nl.ou.jp.gui.model;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.ProjectorGUI;

public interface ProjectorContext { 
	public ProjectorController getProjector();
	public void setProjector(ProjectorController projectorController);
	
	public ProjectorGUI getMainGUI();
	public void setMainGUI(ProjectorGUI gui);
	
	public ProjectorConfiguration getConfiguration();
	public void setConfiguration(ProjectorConfiguration projectorConfiguration);
}