package nl.ou.jp.domain.core.implementation;

import nl.ou.jp.domain.core.model.AnnotationPoint;
import nl.ou.jp.domain.core.model.RelativePosition;
import nl.ou.jp.domain.core.model.SlideShowComponant;
import nl.ou.jp.domain.core.model.SlideShowComponantIterator;

public class AnnotationPointImp implements AnnotationPoint {

	private RelativePosition relativePosition; 
	private int sequenceNumber = -1;

	
	public AnnotationPointImp(RelativePosition relativePosition) 
	{
		this.relativePosition = relativePosition;
	}
	
	public RelativePosition getRelativePosition() 
	{
		return relativePosition;
	}

	@Override
	public int getSequenceNumber() {
		return this.sequenceNumber;
	}

	@Override
	public void setSequenceNumber(int seqnr) {
		this.sequenceNumber = seqnr;
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
	public SlideShowComponant copy() {
		return new AnnotationPointImp(this.relativePosition);
	}
}
