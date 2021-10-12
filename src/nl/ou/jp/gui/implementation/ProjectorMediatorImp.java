package nl.ou.jp.gui.implementation;

import java.awt.Dimension;
import java.nio.file.Path;

import javax.swing.JOptionPane;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.ProjectorGUI;
import nl.ou.jp.gui.model.ProjectorMediator;

public class ProjectorMediatorImp implements ProjectorMediator {

	private ProjectorGUI projectorGUI = null;
	private ProjectorController controller = null;
	
	private double mouseRelativeXLocation = 0;
	private double mouseRelativeYLocation = 0;	
	
	private int annotationLineWeight = 0;
	private int annotationLineColor = 0;
	
	@Override
	public void setMainGUI(ProjectorGUI projectorGUI) {
		this.projectorGUI = projectorGUI;
	}
	
	@Override
	public void setProjectorController(ProjectorController controller) {
		this.controller = controller;
	}
	
	@Override
	public void openSlideShow(Path path) {
		this.controller.openSlideShow(path);		
	}
	
	@Override
	public void showMessageDialog(String message, String title) {
		this.projectorGUI.showMessageDialog(message, title);
	}

	@Override
	public void showErrorMessageDialog(String message, String title) {
		this.projectorGUI.showErrorMessageDialog(message, title);
	}

	@Override
	public int showItemSelectorDialog(String message, String title, String[] items) {
		return this.projectorGUI.showItemSelectorDialog(message, title, items);
	}
	
	@Override
	public Dimension getCanvasDimension() {
		return this.projectorGUI.getCanvasDimension();
	}
	
	@Override
	public void exit() {
		this.projectorGUI.exit();
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
	
	@Override
	public void gotoSlideNumber(String message) {
		String pageNumberStr = JOptionPane.showInputDialog(message);
		int pageNumber = Integer.parseInt(pageNumberStr);
		this.controller.gotoSlideNumber(pageNumber);
	}
	@Override
	public void previousSlide() {
		this.controller.previousSlide();
	}
	@Override
	public void nextSlide() {
		this.controller.nextSlide();
	}

	@Override
	public void reset() {
		this.controller.reset();
	}
	@Override
	public void makeSlideShowReadOnly() {
		this.controller.makeSlideShowReadOnly();
	}
	@Override
	public void enableSlideShowAnnotations() {
		this.controller.enableSlideShowAnnotations();
	}
	@Override
	public void startLineAnnotation() {
		this.controller.startLineAnnotation(this.annotationLineWeight, this.annotationLineColor);
	}
	@Override
	public void addToLineAnnotation() {
		this.controller.addToLineAnnotation(this.mouseRelativeXLocation, this.mouseRelativeYLocation);
		
	}
	@Override
	public boolean canAnnotate() {
		return this.controller.canAnnotate();
	}
}