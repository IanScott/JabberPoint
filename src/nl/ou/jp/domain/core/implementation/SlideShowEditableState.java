package nl.ou.jp.domain.core.implementation;

import java.util.List;

import nl.ou.jp.domain.core.model.*;

public class SlideShowEditableState implements SlideShowState {

	private static SlideShowEditableState instance = null;
	
	public static SlideShowEditableState getInstance() 
	{
		if(instance == null) 
		{
			instance = new SlideShowEditableState();
		}
		return instance;
	}
	
	private SlideShowEditableState() {
		
	}
	
	@Override
	public boolean canAnnotate() {
		return false;
	}

	@Override
	public void add(List<SlideShowComponant> componants, SlideShowComponant componant) {
		componants.add(componant);
	}

	@Override
	public void setTitle(String oldTitle, String newTitle) {
		oldTitle = newTitle;
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
