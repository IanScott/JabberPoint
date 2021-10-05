package nl.ou.jp.domain.core;

import java.util.List;

import nl.ou.jp.domain.core.implementation.AnnotationLineImp;
import nl.ou.jp.domain.core.implementation.AnnotationPointImp;
import nl.ou.jp.domain.core.model.AnnotationLine;
import nl.ou.jp.domain.core.model.AnnotationPoint;
import nl.ou.jp.domain.core.model.RelativePosition;
import nl.ou.jp.domain.core.model.SlideShowComponant;

public class AnnotationFactory {
	private static AnnotationFactory instance = null;
	
	public static AnnotationFactory getInstance() 
	{
		if(instance == null) 
		{
			instance = new AnnotationFactory();
		}
		return instance;
	}
	
	private AnnotationFactory(){
		
	}
	
	public AnnotationLine createAnnotationLine(String title, List<SlideShowComponant> componants, int lineWeight, int lineColor) {
		return new AnnotationLineImp(title, componants, lineWeight, lineColor);
	}

	public AnnotationPoint createAnnotationPoint(RelativePosition relativePosition) {
		return new AnnotationPointImp(relativePosition);
	}	
}