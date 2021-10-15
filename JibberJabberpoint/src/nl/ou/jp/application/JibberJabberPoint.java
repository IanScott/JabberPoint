package nl.ou.jp.application;

import nl.ou.jp.controller.*;
import nl.ou.jp.domain.*;
import nl.ou.jp.gui.*;
import nl.ou.jp.infra.*;

public class JibberJabberPoint {
	
	public void start(String path) {
		SlideShowService slideShowService = SlideShowServiceFactory.getInstance().create(); // Load Domain Service layer
		
		ProjectorInfra projectorInfra = ProjectorInfraFactory.getInstance().create(); // Load Infra
		
		ProjectorController projectorController = ProjectorControllerFactory.getInstance().create(projectorInfra, slideShowService); // load Controller
		
		ProjectorGUI gui = ProjectorGUIFactory.getInstance().create(projectorController); // load GUI

		//start gui with path
		gui.start(path);

	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new JibberJabberPoint().start((args != null && args.length>0)?args[0]:null);
	}
}