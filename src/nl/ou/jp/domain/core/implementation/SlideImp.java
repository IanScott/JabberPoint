package nl.ou.jp.domain.core.implementation;

import java.util.ArrayList;
import java.util.List;

import nl.ou.jp.domain.core.model.*;

public class SlideImp extends SlideShowCompositeTemplate implements Slide {

	private int sequenceNumber = -1;

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
	
	@Override
	public void addToLineAnnotation(AnnotationPoint point) {
		this.componants.get(this.componants.size() - 1).add(point);
	}
}