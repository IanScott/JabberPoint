package nl.ou.jp.domain.core;

import nl.ou.jp.domain.core.implementation.AnnotationPointImp;
import nl.ou.jp.domain.core.model.AnnotationPoint;
import nl.ou.jp.domain.core.model.RelativePosition;

public class AnnotationPointFactory {
	private static AnnotationPointFactory instance = null;
	
	public static AnnotationPointFactory getInstance() 
	{
		if(instance == null) 
		{
			instance = new AnnotationPointFactory();
		}
		return instance;
	}
	
	private AnnotationPointFactory() {
		
	}
	
	public AnnotationPoint createAnnotationPoint(RelativePosition relativePosition) 
	{
		return new AnnotationPointImp(relativePosition);
	}
}
