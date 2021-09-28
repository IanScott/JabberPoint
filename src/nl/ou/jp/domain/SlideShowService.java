package nl.ou.jp.domain;

import nl.ou.jp.domain.core.model.*;

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
}