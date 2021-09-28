package nl.ou.jp.domain.implementation;

import nl.ou.jp.domain.*;
import nl.ou.jp.domain.core.model.*;

public class SlideShowServiceImp implements SlideShowService {
	private static final String EMPTYSTRING = "";
	private static final int EMPTYSIZE = -1;
	
	private SlideShow slideshow = null;
	private SlideShowComponantIterator iterator = null;
	
	private SlideShowBuilder slideShowBuilder = null;
	
	public SlideShowServiceImp(SlideShowBuilder slideShowBuilder) {
		this.slideShowBuilder= slideShowBuilder;
	}
	
	@Override
	public String getSlideShowTitle() {
		if(this.slideshow == null) {
			return EMPTYSTRING;
		}
		return this.slideshow.getTitle();
	}
	
	@Override
	public int getSlideShowSize() {
		if(this.slideshow == null) {
			return EMPTYSIZE;
		}
		return this.slideshow.size();
	}

	@Override
	public int getCurrentSlideNumber() {
		if(this.iterator == null) {
			return EMPTYSIZE;
		}
		return this.iterator.getCurrentIndex();
	}

	/*
	 * CURRENT SLIDE DATA. Methods for retrieving current slide data.
	 */
	
	@Override
	public Slide getCurrentSlide() {
		if(this.iterator !=null) {
			Slide slide = (Slide)this.iterator.getCurrentItem();
			if(slide != null) {
				slide.setSequenceNumber(this.iterator.getCurrentIndex());
				return (Slide)slide.copy();
			}			
		}
		
		return null;
	}
	
	/*
	 * NAVIGATRION. Methods for navigating between Slides in a presentation.
	 */
		
	@Override
	public void gotoSlideIndex(int number) {
		int index = number - 1; //convert from 1 starting index to 0 starting index.
		if(index > this.slideshow.size() -1 || index < 0) {
			return;
		}
		
		this.iterator.setIndex(index);
	}

	// ga naar de vorige slide tenzij je aan het begin van de presentatie bent
	@Override
	public void previousSlide() {
		if(this.iterator != null && this.iterator.hasPrevious()) {
			this.iterator.gotoPrevious();
		}
	}

	// Ga naar de volgende slide tenzij je aan het einde van de presentatie bent.
	@Override
	public void nextSlide() {
		if(this.iterator != null && this.iterator.hasNext()) {
			this.iterator.gotoNext();
		}
	}

	// Verwijder de presentatie, om klaar te zijn voor de volgende
	void clear() {
		this.slideshow = null;
		this.iterator = null;
	}
	

	@Override
	public SlideShowBuilder getSlideShowBuilder() {
		return this.slideShowBuilder;
	}

	@Override
	public void loadSlideShow(SlideShow slideshow) {
		this.slideshow = slideshow;
		this.iterator = slideshow.getIterator();
		
		this.nextSlide(); //load first availible slide.
	}

}
