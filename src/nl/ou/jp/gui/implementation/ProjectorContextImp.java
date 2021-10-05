package nl.ou.jp.gui.implementation;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.ProjectorGUI;
import nl.ou.jp.gui.model.ProjectorConfiguration;
import nl.ou.jp.gui.model.ProjectorContext;

public class ProjectorContextImp implements ProjectorContext {

	private ProjectorController projectorController = null;
	private ProjectorGUI projectorGUI = null;
	private ProjectorConfiguration configurationDefault = null;
	
	private double mouseRelativeXLocation = 0;
	private double mouseRelativeYLocation = 0;	
	
	private int annotationLineWeight = 0;
	private int annotationLineColor = 0;
	
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

	@Override
	public void setMouseRelativeXLocation(double x) {
		this.mouseRelativeXLocation = x;
	}

	@Override
	public void setMouseRelativeYLocation(double y) {
		this.mouseRelativeYLocation = y;
	}

	@Override
	public double getMouseRelativeXLocation() {
		return this.mouseRelativeXLocation;
	}

	@Override
	public double getMouseRelativeYLocation() {
		return this.mouseRelativeYLocation;
	}

	@Override
	public void setAnnotationLineWeight(int weight) {
		this.annotationLineWeight = weight;
	}

	@Override
	public int getAnnotationLineWeight() {
		return this.annotationLineWeight;
	}

	@Override
	public void setAnnotationLineColor(int color) {
		this.annotationLineColor = color;
	}

	@Override
	public int getAnnotationLineColor() {
		return this.annotationLineColor;
	}
}