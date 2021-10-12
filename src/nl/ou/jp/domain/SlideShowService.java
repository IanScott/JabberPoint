package nl.ou.jp.domain;

import nl.ou.jp.domain.core.model.*;
import nl.ou.jp.util.EventDispatcher;

public interface SlideShowService {

	String getSlideShowTitle();

	int getSlideShowSize();

	int getCurrentSlideNumber();

	Slide getCurrentSlide();

	void gotoSlideIndex(int index);

	void previousSlide();

	void nextSlide();
	
	SlideShowBuilder getSlideShowBuilder();
	
	void loadSlideShow(SlideShow slideshow);
	
	void resetSlideShow();
	
	void makeSlideShowReadOnly();
	
	void enableSlideShowAnnotations();
	
	void startLineAnnotation(int index, int lineWeight, int color);
	
	void addToLineAnnotation(int index, double x, double y);
	
	boolean canAnnotate();
	
	EventDispatcher getSlideShowEventDispatcher();
	
	EventDispatcher getSlideEventDispatcher();
}