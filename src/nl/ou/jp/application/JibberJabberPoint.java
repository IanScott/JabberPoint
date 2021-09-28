package nl.ou.jp.application;

import nl.ou.jp.controller.*;
import nl.ou.jp.domain.*;
import nl.ou.jp.gui.*;
import nl.ou.jp.infra.*;

public class JibberJabberPoint {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		SlideShowService slideShowService = SlideShowServiceFactory.getInstance().create(); // Load Domain Service layer
		
		ProjectorInfra projectorInfra = ProjectorInfraFactory.getInstance().create(slideShowService.getSlideShowBuilder()); // Load Infra
		
		ProjectorController projectorController = ProjectorControllerFactory.getInstance().create(projectorInfra, slideShowService); // load Controller
		
		ProjectorGUI gui = ProjectorGUIFactory.getInstance().create(projectorController); // load View (GUI)
		
		gui.start((args != null && args.length>0)?args[0]:null);
	}
}