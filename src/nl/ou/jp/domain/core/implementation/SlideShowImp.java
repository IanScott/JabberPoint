package nl.ou.jp.domain.core.implementation;

import java.util.*;
import nl.ou.jp.domain.core.model.*;

public class SlideShowImp extends SlideShowCompositeTemplate implements SlideShow {

	private SlideShowState slideShowState;

	
	 public SlideShowImp(String title, List<SlideShowComponant> slides) {
		 super(title, slides); 
	 }
	 

	public SlideShowImp() {
	}

	@Override
	public Slide getSlide(int index) {
		if (this.componants != null) {
			return (Slide) this.componants.get(index);
		}
		return null;
	}

	@Override
	protected SlideShowComponant createComponant(String title, List<SlideShowComponant> componants) {
		// return new SlideShowImp(title, componants);
		return new SlideShowImp();

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
	public void setState(SlideShowState state) {
		this.slideShowState = state;
	}

	@Override
	public boolean canAnnotate() {
		return slideShowState.canAnnotate();
	}

	@Override
	public void startLineAnnotation(int index, AnnotationLine line) {
		slideShowState.startLineAnnotation(this, index, line);
	}

	@Override
	public void addToLineAnnotation(int index, AnnotationPoint point) {
		slideShowState.addToLineAnnotation(this, index, point);
	}

	@Override
	public void add(SlideShowComponant componant) {
		if (this.componants == null) {
			this.componants = new ArrayList<>();
		}

		slideShowState.add(this.componants, componant);
	}

	public void setTitle(String title) {
		slideShowState.setTitle(this.title, title);
	}
}