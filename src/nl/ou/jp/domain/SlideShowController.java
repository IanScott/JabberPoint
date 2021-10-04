package nl.ou.jp.domain;

import nl.ou.jp.domain.core.model.AnnotationLine;
import nl.ou.jp.domain.core.model.AnnotationPoint;
import nl.ou.jp.domain.core.model.SlideShow;

public interface SlideShowController {
	void makeSlideShowReadOnly(SlideShow slideShow);
	void enableSlideShowAnnotations(SlideShow slideShow);
	void startLineAnnotation(int index, AnnotationLine line);
	void addToLineAnnotation(int index, AnnotationPoint pointAnnotation);
}