package nl.ou.jp.domain.core.implementation;

import nl.ou.jp.domain.core.model.AnnotationLine;
import nl.ou.jp.domain.core.model.AnnotationPoint;
import nl.ou.jp.domain.core.model.SlideShow;
import nl.ou.jp.domain.core.model.SlideShowController;

public class SlideShowControllerImp implements SlideShowController {

	private SlideShow slideShow;
		
	@Override
	public void makeSlideShowReadOnly(SlideShow slideShow) {
		slideShow.setState(SlideShowUnEditableState.getInstance());
	}

	@Override
	public void enableSlideShowAnnotations(SlideShow slideShow) {
		slideShow.setState(SlideShowAnnotationState.getInstance());
		this.slideShow = slideShow;
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
}