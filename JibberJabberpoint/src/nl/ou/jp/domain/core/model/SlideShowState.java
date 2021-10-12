package nl.ou.jp.domain.core.model;

import java.util.List;

public interface SlideShowState {
	boolean canAnnotate();
	void add(List<SlideShowComponant> componants, SlideShowComponant componant);
	void setTitle(String oldTitle, String newTitle);
	void startLineAnnotation(SlideShowComponant slideShowComponant, AnnotationLine line);
	void addToLineAnnotation(SlideShowComponant slideShowComponant, AnnotationPoint point);
}
