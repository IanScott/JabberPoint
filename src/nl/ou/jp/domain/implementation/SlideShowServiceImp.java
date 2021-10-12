package nl.ou.jp.domain.implementation;

import nl.ou.jp.domain.*;
import nl.ou.jp.domain.core.implementation.RelativePositionImp;
import nl.ou.jp.domain.core.model.*;
import nl.ou.jp.util.EventDispatcher;

public class SlideShowServiceImp implements SlideShowService {
	private static final String EMPTYSTRING = "";
	private static final int EMPTYSIZE = -1;
	
	private SlideShow slideshow = null;
	private SlideShowComponantIterator iterator = null;
	
	private SlideShowBuilder slideShowBuilder = null;
	
	private EventDispatcher slideShowEventDispatcher = null;
	private EventDispatcher slideEventDispatcher = null;
	
	private SlideShowController slideShowController = null;
	
	public SlideShowServiceImp(SlideShowBuilder slideShowBuilder, SlideShowEventDispatcher slideShowDispatcher, SlideEventDispatcher slideDispatcher, SlideShowController slideShowController) {
		this.slideShowBuilder= slideShowBuilder;
		this.slideShowEventDispatcher = slideShowDispatcher;
		this.slideEventDispatcher = slideDispatcher;
		this.slideShowController = slideShowController;
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
			return (Slide)this.iterator.getCurrentItem();	 
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
		this.slideShowController.makeSlideShowReadOnly(slideshow);
		
		this.slideshow = slideshow;
		this.iterator = slideshow.getIterator();
		this.slideShowEventDispatcher.fireEvent(slideshow);
		
		this.nextSlide(); //load first available slide.
	}

	@Override
	public void resetSlideShow() {
		this.slideshow = null;
		this.iterator = null;
		this.slideShowEventDispatcher.fireEvent(slideshow);
	}

	@Override
	public EventDispatcher getSlideShowEventDispatcher() {
		return slideShowEventDispatcher;
	}

	@Override
	public EventDispatcher getSlideEventDispatcher() {
		return slideEventDispatcher;
	}

	@Override
	public void makeSlideShowReadOnly() {
		if(this.slideshow != null) 
		{
			slideShowController.makeSlideShowReadOnly(slideshow);	
		}

	}

	@Override
	public void enableSlideShowAnnotations() {
		if(this.slideshow != null) 
		{
			slideShowController.enableSlideShowAnnotations(slideshow);	
		}
	}

	@Override
	public void startLineAnnotation(int index, int lineWeight, int color) {
		var item = SlideShowComponantFactory.getInstance().createSlideShowComponant(SlideShowComponantFactory.ANNOTATIONLINE_TYPE);
		((AnnotationLine)item).setLineWeight(lineWeight);
		((AnnotationLine)item).setLineColor(color);
		slideShowController.startLineAnnotation(index, ((AnnotationLine)item));
	}

	@Override
	public void addToLineAnnotation(int index, double x, double y) {		
		int minCoordinateValue = 0;
		int maxCoordinateValue = 1;
		
		if(x > minCoordinateValue && 
		   x < maxCoordinateValue && 
		   y > minCoordinateValue && 
		   y < maxCoordinateValue)
		{
			
			var item = SlideShowComponantFactory.getInstance().createSlideShowComponant(SlideShowComponantFactory.ANNOTATIONPOINT_TYPE);
			((AnnotationPoint)item).setRelativePosition(new RelativePositionImp(x,y));
			slideShowController.addToLineAnnotation(index, ((AnnotationPoint)item));

			this.slideEventDispatcher.fireEvent(iterator.getCurrentItem());
		}
	}

	@Override
	public boolean canAnnotate() {
		if(this.slideshow != null) 
		{
			return this.slideshow.canAnnotate();
		}
		return false;
	}
}