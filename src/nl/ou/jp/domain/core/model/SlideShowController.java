package nl.ou.jp.domain.core.model;

public interface SlideShowController {
	void loadSlideShow(SlideShow slideShow);
	void resetSlideShow();
	void makeSlideShowReadOnly();
	void enableSlideShowAnnotations();
	void startLineAnnotation(int index, AnnotationLine line);
	void addToLineAnnotation(int index, AnnotationPoint pointAnnotation);
	int getSlideShowSize();
	String getSlideShowTitle();
	SlideShow getSlideShow();
	boolean canAnnotate();
}