package nl.ou.jp.domain.core;

import nl.ou.jp.domain.core.implementation.SlideShowControllerImp;
import nl.ou.jp.domain.core.model.SlideShowController;

public class SlideShowControllerFactory {
	private static SlideShowControllerFactory instance = null;
	
	public static SlideShowControllerFactory getInstance() 
	{
		if(instance == null) 
		{
			instance = new SlideShowControllerFactory();
		}
		return instance;
	}
	
	private SlideShowControllerFactory() {
		//singleton
	}
	
	public SlideShowController create() 
	{
		return new SlideShowControllerImp();
	}
}