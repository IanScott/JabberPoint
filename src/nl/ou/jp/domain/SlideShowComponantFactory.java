package nl.ou.jp.domain;

import nl.ou.jp.domain.core.implementation.*;
import nl.ou.jp.domain.core.model.*;

public class SlideShowComponantFactory {
	
	public static final String FIGUREITEM_TYPE = "IMAGE";
	public static final String TEXTITEM_TYPE = "TEXT";
	public static final String ANNOTATIONLINE_TYPE = "ANNOTATIONLINE";
	public static final String ANNOTATIONPOINT_TYPE = "ANNOTATIONPOINT";
	
	private static SlideShowComponantFactory instance = null;
	
	public static SlideShowComponantFactory getInstance() {
		if(instance == null) {
			instance = new SlideShowComponantFactory();
		}
		return instance;
	}
	
	private SlideShowComponantFactory() {
		//singleton
	}

	public SlideShowComponant createSlideShowComponant(String type) {		
		switch(type.toUpperCase()) {
			case TEXTITEM_TYPE : return createTextItem();
			case FIGUREITEM_TYPE : return createFigureItem();
			case ANNOTATIONLINE_TYPE : return createAnnotationLine();
			case ANNOTATIONPOINT_TYPE : return createAnnotationPoint();
			default: throw new IllegalArgumentException();
		}
	}
	
	private SlideShowItem createTextItem() {	
		return new TextItemImp();
	}
	
	private SlideShowItem createFigureItem() {
		return new FigureItemImp();
	}
	
	private AnnotationLine createAnnotationLine() {
		return new AnnotationLineImp();
	}

	private AnnotationPoint createAnnotationPoint() {
		return new AnnotationPointImp();
	}	
}