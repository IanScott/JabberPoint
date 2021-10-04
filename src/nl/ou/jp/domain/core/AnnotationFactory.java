package nl.ou.jp.domain.core;

import nl.ou.jp.domain.core.implementation.AnnotationLineImp;
import nl.ou.jp.domain.core.implementation.AnnotationPointImp;
import nl.ou.jp.domain.core.model.AnnotationLine;
import nl.ou.jp.domain.core.model.AnnotationPoint;
import nl.ou.jp.domain.core.model.RelativePosition;

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
	
	public AnnotationLine createAnnotationLine(int lineWeight, int lineColor) {
		return new AnnotationLineImp(lineWeight, lineColor);
	}

	public AnnotationPoint createAnnotationPoint(RelativePosition relativePosition) {
		return new AnnotationPointImp(relativePosition);
	}	
}