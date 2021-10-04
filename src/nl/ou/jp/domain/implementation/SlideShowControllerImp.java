package nl.ou.jp.domain.implementation;

import nl.ou.jp.domain.SlideShowController;
import nl.ou.jp.domain.core.implementation.SlideShowAnnotationState;
import nl.ou.jp.domain.core.implementation.SlideShowUnEditableState;
import nl.ou.jp.domain.core.model.AnnotationLine;
import nl.ou.jp.domain.core.model.AnnotationPoint;
import nl.ou.jp.domain.core.model.SlideShow;

public class SlideShowControllerImp implements SlideShowController {

	private SlideShow slideShow;
	
	public SlideShowControllerImp() 
	{
	}
	
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
		this.slideShow.startLineAnnotation(index, line);	
	}

	@Override
	public void addToLineAnnotation(int index, AnnotationPoint pointAnnotation) {
		this.slideShow.addToLineAnnotation(index, pointAnnotation);			
	}
}