package nl.ou.jp.domain.core.implementation;

import java.util.ArrayList;
import java.util.List;

import nl.ou.jp.domain.core.model.AnnotationLine;
import nl.ou.jp.domain.core.model.AnnotationPoint;
import nl.ou.jp.domain.core.model.SlideShowComponant;

public class AnnotationLineImp extends SlideShowCompositeTemplate implements AnnotationLine{
	
	private final int lineWeight;
	private final int lineColor;
	
	private int sequenceNumber = -1;
	
	public AnnotationLineImp(String title, List<SlideShowComponant> componants,int lineWeight, int lineColor)
	{ 
		super(title, componants);
		
		this.lineWeight = lineWeight;
		this.lineColor = lineColor;
	}
	
	public int getLineWeight() 
	{
		return this.lineWeight;
	}
	
	public int getLineColor() 
	{
		return this.lineColor;
	}

	@Override
	protected SlideShowComponant createComponant(String title, List<SlideShowComponant> componants) {
		return new AnnotationLineImp(title, componants,lineWeight, lineColor);
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