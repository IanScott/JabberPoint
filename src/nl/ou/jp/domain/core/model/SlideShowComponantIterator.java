package nl.ou.jp.domain.core.model;

public interface SlideShowComponantIterator {
	boolean hasNext();
	void gotoNext();
	
	boolean hasPrevious();
	void gotoPrevious();
	
	void setIndex(int index);
	
	int getCurrentIndex();
	SlideShowComponant getCurrentItem();
}
