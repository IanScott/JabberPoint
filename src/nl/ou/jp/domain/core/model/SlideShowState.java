package nl.ou.jp.domain.core.model;

import java.util.List;

public interface SlideShowState {
	boolean canAnnotate();
	void add(List<SlideShowComponant> componants, SlideShowComponant componant);
	void setTitle(String oldTitle, String newTitle);
	void startLineAnnotation(SlideShow slideShow, int index, AnnotationLine line);
	void addToLineAnnotation(SlideShow slideShow, int index, AnnotationPoint point);
}
