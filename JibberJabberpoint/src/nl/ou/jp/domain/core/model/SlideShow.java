package nl.ou.jp.domain.core.model;

public interface SlideShow extends SlideShowComposite {

	Slide getSlide(int index);
	void setState(SlideShowState state);
	boolean canAnnotate();
	void startLineAnnotation(int index, AnnotationLine line);
	void addToLineAnnotation(int index, AnnotationPoint point);

}