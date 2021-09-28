package nl.ou.jp.controller;

import nl.ou.jp.controller.implementation.ProjectorControllerImp;
import nl.ou.jp.domain.SlideShowService;
import nl.ou.jp.infra.ProjectorInfra;

public class ProjectorControllerFactory {
	private static ProjectorControllerFactory instance = null;

	public static ProjectorControllerFactory getInstance() {
		if(instance == null) {
			instance = new ProjectorControllerFactory();
		}
		return instance;
	}
	
	private ProjectorControllerFactory() {
		//singleton
	}
	
	public ProjectorController create(ProjectorInfra projectorInfra, SlideShowService service) {
		return new ProjectorControllerImp(projectorInfra, service);
	}
}