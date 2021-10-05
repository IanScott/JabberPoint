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
	public void startLineAnnotation(List<SlideShowComponant> componants, int index, AnnotationLine line) {
		SlideImp slide = (SlideImp)componants.get(index);
		slide.add(line);
	}

	@Override
	public void addToLineAnnotation(List<SlideShowComponant> componants, int index, AnnotationPoint point) {
		SlideImp slide = (SlideImp)componants.get(index);
		slide.addToLineAnnotation(point);
	}
}