package nl.ou.jp.domain.core.implementation;

import nl.ou.jp.domain.core.model.*;

public abstract class SlideShowLeaf implements SlideShowComponant {
	
	private int sequenceNumber = -1;
	
	public SlideShowLeaf() 
	{
	}
	
	@Override
	public void add(SlideShowComponant componant) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SlideShowComponantIterator getIterator() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public int getSequenceNumber() {
		return this.sequenceNumber;
	}

	@Override
	public void setSequenceNumber(int seqnr) {
		this.sequenceNumber = seqnr;
	}
}
