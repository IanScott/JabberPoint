package nl.ou.jp.domain.core.model;

public interface AnnotationLine extends SlideShowComposite {

	int getLineWeight();
	int getLineColor();
	
	void setLineWeight(int weight);
	void setLineColor(int color);
}
