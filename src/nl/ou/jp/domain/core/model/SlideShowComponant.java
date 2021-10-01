package nl.ou.jp.domain.core.model;

public interface SlideShowComponant {
	int getSequenceNumber();
	void setSequenceNumber(int seqnr);
	
	void add(SlideShowComponant componant);
	SlideShowComponantIterator getIterator();
	
	SlideShowComponant copy();
}