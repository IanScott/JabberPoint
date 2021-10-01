package nl.ou.jp.domain.implementation;

import nl.ou.jp.domain.*;
import nl.ou.jp.domain.core.model.*;

public class SlideShowServiceImp implements SlideShowService {
	private static final String EMPTYSTRING = "";
	private static final int EMPTYSIZE = -1;
	
	private SlideShow slideshow = null;
	private SlideShowComponantIterator iterator = null;
	
	private SlideShowBuilder slideShowBuilder = null;
	
	private SlideShowEventDispatcher slideShowEventDispatcher = null;
	private SlideEventDispatcher slideEventDispatcher = null;
	
	public SlideShowServiceImp(SlideShowBuilder slideShowBuilder, SlideShowEventDispatcher slideShowDispatcher, SlideEventDispatcher slideDispatcher) {
		this.slideShowBuilder= slideShowBuilder;
		this.slideShowEventDispatcher = slideShowDispatcher;
		this.slideEventDispatcher = slideDispatcher;
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
	
	@Override
	public void gotoSlideIndex(int number) {
		int index = number - 1; //convert from 1 starting index to 0 starting index.
		if(index > this.slideshow.size() -1 || index < 0) {
			return;
		}
		
		this.iterator.setIndex(index);
		this.slideEventDispatcher.fireEvent(iterator.getCurrentItem());
	}

	@Override
	public void previousSlide() {
		if(this.iterator != null && this.iterator.hasPrevious()) {
			this.iterator.gotoPrevious();
			this.slideEventDispatcher.fireEvent(iterator.getCurrentItem());
		}
	}

	@Override
	public void nextSlide() {
		if(this.iterator != null && this.iterator.hasNext()) {
			this.iterator.gotoNext();
			this.slideEventDispatcher.fireEvent(iterator.getCurrentItem());
		}
	}
	
	@Override
	public SlideShowBuilder getSlideShowBuilder() {
		return this.slideShowBuilder;
	}

	@Override
	public void loadSlideShow(SlideShow slideshow) {
		this.slideshow = slideshow;
		this.iterator = slideshow.getIterator();
		this.slideShowEventDispatcher.fireEvent(slideshow);
		
		this.nextSlide(); //load first available slide.
	}

	@Override
	public void resetSlideShow() {
		this.slideshow = null;
		this.iterator = null;
	}

	@Override
	public SlideShowEventDispatcher getSlideShowEventDispatcher() {
		return slideShowEventDispatcher;
	}

	@Override
	public SlideEventDispatcher getSlideEventDispatcher() {
		return slideEventDispatcher;
	}
}
