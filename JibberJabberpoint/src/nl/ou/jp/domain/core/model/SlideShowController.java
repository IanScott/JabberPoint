package nl.ou.jp.domain.core.model;

public interface SlideShowController {
	void makeSlideShowReadOnly(SlideShow slideShow);
	void enableSlideShowAnnotations(SlideShow slideShow);
	void startLineAnnotation(int index, AnnotationLine line);
	void addToLineAnnotation(int index, AnnotationPoint pointAnnotation);
}