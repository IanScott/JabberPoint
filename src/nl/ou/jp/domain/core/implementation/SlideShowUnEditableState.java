package nl.ou.jp.domain.core.implementation;

import java.util.List;

import nl.ou.jp.domain.core.model.*;

public class SlideShowUnEditableState implements SlideShowState {

private static SlideShowUnEditableState instance = null;
	
	public static SlideShowUnEditableState getInstance() 
	{
		if(instance == null) 
		{
			instance = new SlideShowUnEditableState();
		}
		return instance;
	}
	
	private SlideShowUnEditableState() {
		
	}
	
	@Override
	public boolean canAnnotate() {
		return false;
	}

	@Override
	public void add(List<SlideShowComponant>componants, SlideShowComponant componant) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setTitle(String oldTitle, String newTitle) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void startLineAnnotation(SlideShowComponant slideShowComponant, AnnotationLine line) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addToLineAnnotation(SlideShowComponant slideShowComponant, AnnotationPoint point) {
		throw new UnsupportedOperationException();
	}
}
