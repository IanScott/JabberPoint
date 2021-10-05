package nl.ou.jp.domain.core.model;

import java.util.List;

public interface SlideShowState {
	boolean canAnnotate();
	void add(List<SlideShowComponant> componants, SlideShowComponant componant);
	void setTitle(String oldTitle, String newTitle);
	void startLineAnnotation(List<SlideShowComponant> componants, int index, AnnotationLine line);
	void addToLineAnnotation(List<SlideShowComponant> componants, int index, AnnotationPoint point);
}
