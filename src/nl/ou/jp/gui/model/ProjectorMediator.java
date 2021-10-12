package nl.ou.jp.gui.model;

import java.awt.Dimension;
import java.nio.file.Path;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.ProjectorGUI;

public interface ProjectorMediator { 

	public void setMainGUI(ProjectorGUI gui);
	public void setProjectorController(ProjectorController controller);
	
	void openSlideShow(Path path);
	
	void showMessageDialog(String message, String title);
	void showErrorMessageDialog(String message, String title);
	int showItemSelectorDialog(String message, String title, String[] items);
	void exit();
	Dimension getCanvasDimension();
		
	public void setMouseRelativeXLocation(double x);
	public void setMouseRelativeYLocation(double y);
	
	public void setAnnotationLineWeight(int weight);
	public int getAnnotationLineWeight();
	
	/**
	 * Set the RGB code of the Color to be used to render an AnnotationLine.
	 * @param color
	 */
	public void setAnnotationLineColor(int color);
	
	public int getAnnotationLineColor();
	
	void gotoSlideNumber(String message);
	
	void previousSlide();
	
	void nextSlide();
	
	void reset();
	
	void makeSlideShowReadOnly();
	
	void enableSlideShowAnnotations();
	
	void startLineAnnotation();
	
	void addToLineAnnotation();
	
	boolean canAnnotate();
}
