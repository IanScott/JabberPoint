package nl.ou.jp.controller;

import java.io.InputStream;
import java.nio.file.Path;

import nl.ou.jp.domain.core.model.Slide;
import nl.ou.jp.util.*;

public interface ProjectorController {
	
	//queries
	String getSlideShowTitle();
	
	int getSlideShowSize();

	int getCurrentSlideNumber();

	Slide getCurrentSlide();
	
	InputStream fetchFileAsStream(Path path);
	
	boolean canAnnotate();
	
	//commands
	void gotoSlideNumber(int number);
		
	void previousSlide();
	
	void nextSlide();
	
	void openSlideShow(Path path);
	
	void reset();
	
	void makeSlideShowReadOnly();
	
	void enableSlideShowAnnotations();
	
	void startLineAnnotation(int lineWeight, int color);
	
	void addToLineAnnotation(double x, double y);
	
	void registerSlideShowListeners(EventListener listener);
}