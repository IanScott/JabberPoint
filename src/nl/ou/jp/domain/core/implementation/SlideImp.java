package nl.ou.jp.domain.core.implementation;

import java.util.ArrayList;
import java.util.List;

import nl.ou.jp.domain.core.model.*;

public class SlideImp extends SlideShowCompositeTemplate implements Slide {
	
	private int sequenceNumber = -1;
	private List<AnnotationLine> lines;
	private AnnotationLine currentLine;
	
	public SlideImp(String title, List<SlideShowComponant> componants) {
		super(title, componants);

	}

	public SlideImp(String title, int sequenceNumber, List<SlideShowComponant> componants) {
		super(title, componants);

		this.sequenceNumber = sequenceNumber;
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
	protected SlideShowComponant createComponant(String title, List<SlideShowComponant> componants) {
		return new SlideImp(title, sequenceNumber, componants);
	}	
	
	public void add(SlideShowComponant componant) {
		if(this.componants == null) {
			this.componants = new ArrayList<>();
		}
		this.componants.add(componant);
	}

	@Override
	public void startLineAnnotation(AnnotationLine line) {
		if(this.lines == null) 
		{
			this.lines = new ArrayList<>();
		}
		
		AnnotationLine newLine = line;
		currentLine = newLine;
		this.lines.add(currentLine);
	}

	@Override
	public void addToLineAnnotation(AnnotationPoint point) {
		currentLine.add(point);
	}
}