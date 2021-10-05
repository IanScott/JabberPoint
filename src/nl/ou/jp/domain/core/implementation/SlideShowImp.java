package nl.ou.jp.domain.core.implementation;

import java.util.*;
import nl.ou.jp.domain.core.model.*;
import nl.ou.jp.gui.implementation.drawstrategies.AnnotationLineDrawStrategy;

public class SlideShowImp extends SlideShowCompositeTemplate implements SlideShow {

	public SlideShowImp(String title, List<SlideShowComponant> slides) {
		super(title, slides);
	}
	
	//Delete
	public Slide getSlide(int index) {
		if(this.componants != null) {
			Slide slide =  (Slide)this.componants.get(index);
			if(slide != null) {
				System.out.println("before:"+slide.size());
				slide.add(AnnotationLineDrawStrategy.getTestItem());
				System.out.println("after:"+slide.size());
				return (Slide)slide.copy();					
			}
		}
		return null;
	}

	@Override
	protected SlideShowComponant createComponant(String title, List<SlideShowComponant> componants) {
		return new SlideShowImp(title, componants);
	}
	
	@Override
	public int getSequenceNumber() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setSequenceNumber(int seqnr) {
		throw new UnsupportedOperationException();
	}
}