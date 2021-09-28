package nl.ou.jp.domain;

import nl.ou.jp.domain.core.implementation.SlideBuilderImp;
import nl.ou.jp.domain.implementation.*;

public class SlideShowServiceFactory {
	
	private static SlideShowServiceFactory instance = null;
	
	public static SlideShowServiceFactory getInstance() {
		if(instance == null) {
			instance = new SlideShowServiceFactory();
		}
		return instance;
	}
	
	private SlideShowServiceFactory() {
		//singleton
	}
	
	public SlideShowService create() {
		return new SlideShowServiceImp(new SlideShowBuilderImp(new SlideBuilderImp()));
	}
}