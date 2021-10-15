package nl.ou.jp.domain.core.implementation;

import nl.ou.jp.domain.core.model.AnnotationLine;
import nl.ou.jp.domain.core.model.AnnotationPoint;
import nl.ou.jp.domain.core.model.SlideShow;
import nl.ou.jp.domain.core.model.SlideShowController;

public class SlideShowControllerImp implements SlideShowController {

	private static final String EMPTYSTRING = "";
	private static final int EMPTYSIZE = -1;
	
	private SlideShow slideShow;
		
	@Override
	public void makeSlideShowReadOnly() {
		if(this.slideShow != null) 
		{
			slideShow.setState(SlideShowUnEditableState.getInstance());	
		}
	}

	@Override
	public void enableSlideShowAnnotations() {
		if(this.slideShow != null) 
		{
			slideShow.setState(SlideShowAnnotationState.getInstance());
		}
	}

	@Override
	public void startLineAnnotation(int index, AnnotationLine line) {
		if(this.slideShow != null) 
		{
			this.slideShow.startLineAnnotation(index, line);				
		}
	}

	@Override
	public void addToLineAnnotation(int index, AnnotationPoint pointAnnotation) {
		if(this.slideShow != null) 
		{
			this.slideShow.addToLineAnnotation(index, pointAnnotation);	
		}
	}

	@Override
	public void loadSlideShow(SlideShow slideShow) {
		if(slideShow != null) 
		{
			this.slideShow = slideShow;
		}
	}

	@Override
	public void resetSlideShow() {
		this.slideShow = null;
		
	}

	@Override
	public int getSlideShowSize() {
		if(this.slideShow != null) 
		{
			return this.slideShow.size();
		}
		return EMPTYSIZE;
	}

	@Override
	public String getSlideShowTitle() {
		if(this.slideShow != null) 
		{
			return this.slideShow.getTitle();
		}
		return EMPTYSTRING;
	}

	@Override
	public SlideShow getSlideShow() {
		return this.slideShow;
	}

	@Override
	public boolean canAnnotate() {
		if(this.slideShow != null) 
		{
			return this.slideShow.canAnnotate();
		}
		return false;
	}
}