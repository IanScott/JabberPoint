package nl.ou.jp.domain;

import nl.ou.jp.domain.core.model.SlideShow;

public interface SlideShowBuilder {
	void withSlideShowTitle(String title);
	
	void appendSlide(String title);
	
	void appendTextItemToSlide(String text, int level);
	
	void appendFigureItemToSlide(String source, int level);
	
	SlideShow getSlideShow();
}