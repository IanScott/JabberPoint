package nl.ou.jp.domain;

public interface SlideShowBuilder {
	void withSlideShowTitle(String title);
	
	void appendSlide(String title);
	
	void appendTextItemToSlide(String text, int level);
	
	void appendFigureItemToSlide(String source, int level);
}