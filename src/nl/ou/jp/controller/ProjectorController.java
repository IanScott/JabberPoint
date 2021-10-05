package nl.ou.jp.controller;

import java.io.InputStream;
import java.nio.file.Path;

import nl.ou.jp.domain.core.model.Slide;
import nl.ou.jp.util.*;

public interface ProjectorController {
	
	String getSlideShowTitle();
	
	int getSlideShowSize();

	int getCurrentSlideNumber();

	Slide getCurrentSlide();
	
	void gotoSlideNumber(int number);
		
	void previousSlide();
	
	void nextSlide();
	
	InputStream fetchFileAsStream(Path path);
	
	void openSlideShow(Path path);
	
	void reset();
	
	void registerSlideShowListeners(EventListener listener);
	
	void makeSlideShowReadOnly();
	
	void enableSlideShowAnnotations();
	
	boolean canAnnotate();
	
	void startLineAnnotation(int lineWeight, int color);
	
	void addToLineAnnotation(double x, double y);
}