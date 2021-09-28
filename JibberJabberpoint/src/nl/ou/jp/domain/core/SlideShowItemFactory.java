package nl.ou.jp.domain.core;

import nl.ou.jp.domain.core.implementation.*;
import nl.ou.jp.domain.core.model.*;

public class SlideShowItemFactory {
	
	private static final String FIGUREITEM_TYPE = "IMAGE";
	private static final String TEXTITEM_TYPE = "TEXT";
	
	private static SlideShowItemFactory instance = null;
	
	public static SlideShowItemFactory getInstance() {
		if(instance == null) {
			instance = new SlideShowItemFactory();
		}
		return instance;
	}
	
	private SlideShowItemFactory() {
		//singleton
	}

	public SlideShowItem createSlideShowItem(String type, String data, Integer level) {
		
		switch(type.toUpperCase()) {
			case TEXTITEM_TYPE : return createTextItem(data, level);
			case FIGUREITEM_TYPE : return createFigureItem(data, level);
			default: throw new IllegalArgumentException();
		}
	}
	
	private SlideShowItem createTextItem(String text, Integer level) {	
		return new TextItemImp(text, new SimpleLevel(level));
	}
	
	private SlideShowItem createFigureItem(String source, Integer level) {
		return new FigureItemImp(source, new SimpleLevel(level));
	}
}