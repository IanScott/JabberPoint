package nl.ou.jp.domain;

import nl.ou.jp.domain.core.SlideShowControllerFactory;
import nl.ou.jp.domain.core.model.SlideShowController;
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
		SlideShowBuilder slideshowBuilder = new SlideShowBuilderImp();
		SlideShowController slideShowController = SlideShowControllerFactory.getInstance().create();
		return new SlideShowServiceImp(slideshowBuilder, new SlideShowEventDispatcher(), new SlideEventDispatcher(), slideShowController);
	}
}