package nl.ou.jp.domain.core.implementation;

import java.util.List;

import nl.ou.jp.domain.core.model.AnnotationLine;
import nl.ou.jp.domain.core.model.AnnotationPoint;
import nl.ou.jp.domain.core.model.SlideShow;
import nl.ou.jp.domain.core.model.SlideShowComponant;
import nl.ou.jp.domain.core.model.SlideShowState;

public class SlideShowAnnotationState implements SlideShowState {

private static SlideShowAnnotationState instance = null;
	
	public static SlideShowAnnotationState getInstance() 
	{
		if(instance == null) 
		{
			instance = new SlideShowAnnotationState();
		}
		return instance;
	}
	
	private SlideShowAnnotationState() {
		
	}
	
	@Override
	public boolean canAnnotate() {
		return true;
	}

	@Override
	public void add(List<SlideShowComponant> componants, SlideShowComponant componant) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setTitle(String oldTitle, String newTitle) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void startLineAnnotation(SlideShow slideShow, int index, AnnotationLine line) {
		slideShow.getSlide(index).startLineAnnotation(line);
	}

	@Override
	public void addToLineAnnotation(SlideShow slideShow, int index, AnnotationPoint point) {
		slideShow.getSlide(index).add(point);
	}
}