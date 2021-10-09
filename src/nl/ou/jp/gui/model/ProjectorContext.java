package nl.ou.jp.gui.model;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.ProjectorGUI;

public interface ProjectorContext { 
	public ProjectorController getController();
	public void setController(ProjectorController projectorController);
	
	public ProjectorGUI getMainGUI();
	public void setMainGUI(ProjectorGUI gui);
	
	public ProjectorConfiguration getConfiguration();
	public void setConfiguration(ProjectorConfiguration projectorConfiguration);
	
	public void setMouseRelativeXLocation(double x);
	public void setMouseRelativeYLocation(double y);
	
	public double getMouseRelativeXLocation();
	public double getMouseRelativeYLocation();
	
	public void setAnnotationLineWeight(int weight);
	public int getAnnotationLineWeight();
	
	/**
	 * Set the RGB code of the Color to be used to render an AnnotationLine.
	 * @param color
	 */
	public void setAnnotationLineColor(int color);
	public int getAnnotationLineColor();
}
