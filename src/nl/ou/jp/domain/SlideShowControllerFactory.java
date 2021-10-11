package nl.ou.jp.domain;

import nl.ou.jp.domain.implementation.SlideShowControllerImp;

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
		
	}
	
	public SlideShowController create() 
	{
		return new SlideShowControllerImp();
	}
}