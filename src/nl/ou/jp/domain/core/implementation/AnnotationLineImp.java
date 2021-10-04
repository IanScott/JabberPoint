package nl.ou.jp.domain.core.implementation;

import java.util.ArrayList;
import java.util.List;

import nl.ou.jp.domain.core.model.AnnotationLine;
import nl.ou.jp.domain.core.model.AnnotationPoint;
import nl.ou.jp.domain.core.model.SlideShowComponant;

public class AnnotationLineImp extends SlideShowCompositeTemplate implements AnnotationLine{
	
	private final int lineWeight;
	private final int lineColor;
	
	private List<AnnotationPoint> points;
	
	public AnnotationLineImp(int lineWeight, int lineColor)
	{ 
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
		return new AnnotationLineImp(lineWeight, lineColor);
	}
	
	@Override
	public int getSequenceNumber() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setSequenceNumber(int seqnr) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void add(SlideShowComponant componant) {
		if(this.points == null) 
		{
			points = new ArrayList<>();
		}
		
		if(componant instanceof AnnotationPoint) 
		{
			points.add((AnnotationPoint)componant);
		}else 
		{
			throw new IllegalArgumentException();
		}
		
	}
}