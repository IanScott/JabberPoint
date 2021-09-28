package nl.ou.jp.domain.core.model;

public interface SlideShowComponant {
	void add(SlideShowComponant componant);
	SlideShowComponantIterator getIterator();
	
	SlideShowComponant copy();
}