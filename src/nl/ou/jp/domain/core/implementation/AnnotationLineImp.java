package nl.ou.jp.domain.core.implementation;

import java.util.ArrayList;
import java.util.List;

import nl.ou.jp.domain.core.model.AnnotationLine;
import nl.ou.jp.domain.core.model.SlideShowComponant;

public class AnnotationLineImp extends SlideShowCompositeTemplate implements AnnotationLine{
	
	private int lineWeight = 0;
	private int lineColor = 0;
	
	private int sequenceNumber = -1;
	
	public AnnotationLineImp() { 
		super("", new ArrayList<>());
	}
	
	public AnnotationLineImp(String title, List<SlideShowComponant> componants,int lineWeight, int lineColor) { 
		super(title, componants);
		
		this.lineWeight = lineWeight;
		this.lineColor = lineColor;
	}
	
	public int getLineWeight() {
		return this.lineWeight;
	}
	
	public int getLineColor() {
		return this.lineColor;
	}

	@Override
	public void setLineWeight(int weight) {
		this.lineWeight = weight;
	}
	
	@Override
	public void setLineColor(int color) {
		this.lineColor = color;
	}
	
	@Override
	public SlideShowComponant createComponant(String title, List<SlideShowComponant> componants) {
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