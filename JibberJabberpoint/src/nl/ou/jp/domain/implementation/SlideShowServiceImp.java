package nl.ou.jp.domain.implementation;

import nl.ou.jp.domain.*;
import nl.ou.jp.domain.core.implementation.RelativePositionImp;
import nl.ou.jp.domain.core.model.*;
import nl.ou.jp.util.EventDispatcher;

public class SlideShowServiceImp implements SlideShowService {
	
	private static final int EMPTYSIZE = -1;

	private SlideShowComponantIterator iterator = null;
	
	private SlideShowBuilder slideShowBuilder = null;
	
	private EventDispatcher slideShowEventDispatcher = null;
	private EventDispatcher slideEventDispatcher = null;
	
	private SlideShowController slideShowController = null;
	
	public SlideShowServiceImp() {
		super();
	}
	
	public SlideShowServiceImp(SlideShowBuilder slideShowBuilder, SlideShowEventDispatcher slideShowDispatcher, SlideEventDispatcher slideDispatcher, SlideShowController slideShowController) {
		this.slideShowBuilder= slideShowBuilder;
		this.slideShowEventDispatcher = slideShowDispatcher;
		this.slideEventDispatcher = slideDispatcher;
		this.slideShowController = slideShowController;
	}
	
	@Override
	public String getSlideShowTitle() {
		return slideShowController.getSlideShowTitle();
	}
	
	@Override
	public int getSlideShowSize() {
		return slideShowController.getSlideShowSize();
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
		if(index > this.slideShowController.getSlideShowSize() -1 || index < 0) {
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
		this.slideShowController.loadSlideShow(slideshow);
		this.slideShowController.makeSlideShowReadOnly();
		
		this.iterator = slideshow.getIterator();
		this.slideShowEventDispatcher.fireEvent(slideshow);
		
		this.nextSlide(); //load first available slide.
	}

	@Override
	public void resetSlideShow() {
		this.slideShowController.resetSlideShow();
		this.iterator = null;
		this.slideShowEventDispatcher.fireEvent(this.slideShowController.getSlideShow());
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
		slideShowController.makeSlideShowReadOnly();	
	}

	@Override
	public void enableSlideShowAnnotations() {
		slideShowController.enableSlideShowAnnotations();	
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
		return this.slideShowController.canAnnotate();
	}

	@Override
	public void setSlideShowBuilder(SlideShowBuilder slideShowBuilder) {
		this.slideShowBuilder = slideShowBuilder;
	}

	@Override
	public void setSlideShowController(SlideShowController slideShowController) {
		this.slideShowController = slideShowController;
	}

	@Override
	public void setSlideShowEventDispatcher(EventDispatcher eventDispatcher) {
		this.slideShowEventDispatcher = eventDispatcher;
	}

	@Override
	public void setSlideEventDispatcher(EventDispatcher eventDispatcher) {
		this.slideEventDispatcher = eventDispatcher;
	}
}