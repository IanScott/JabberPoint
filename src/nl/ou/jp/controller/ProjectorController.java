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
	
	void openPresentation(Path path);
	
	void reset();
	void registerSlideShowListeners(EventListener listener);
}