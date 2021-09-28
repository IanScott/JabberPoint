package nl.ou.jp.domain;

import nl.ou.jp.domain.core.model.SlideShow;

public interface SlideShowBuilder {
	void withSlideShowTitle(String title);
	
	void appendSlide(String title);
	
	void appendSlideItemToSlide(int slideIndex, String type, String data, int level);
	
	SlideShow getSlideShow();
}