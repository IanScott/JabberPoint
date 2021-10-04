package nl.ou.jp.domain.core.model;

public interface Slide extends SlideShowComposite {
	void startLineAnnotation(AnnotationLine line);
	void addToLineAnnotation(AnnotationPoint point);
}
