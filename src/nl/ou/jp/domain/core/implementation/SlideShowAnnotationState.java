package nl.ou.jp.domain.core.implementation;

import java.util.List;

import nl.ou.jp.domain.core.model.*;

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
	public void startLineAnnotation(SlideShowComponant slideShowComponant, AnnotationLine line) {
		slideShowComponant.add(line);
	}

	@Override
	public void addToLineAnnotation(SlideShowComponant slideShowComponant, AnnotationPoint point) {
		SlideShowComponant lastAnnotation = slideShowComponant.get(slideShowComponant.size() - 1);
		lastAnnotation.add(point);
	}
}