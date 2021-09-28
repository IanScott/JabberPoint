package nl.ou.jp.infra;

import nl.ou.jp.domain.SlideShowBuilder;
import nl.ou.jp.infra.implementation.*;

public class ProjectorInfraFactory {
	
	private static ProjectorInfraFactory instance = null;
	
	public static ProjectorInfraFactory getInstance() {
		if(instance == null) {
			instance = new ProjectorInfraFactory();
		}
		return instance;
	}
	
	private ProjectorInfraFactory() {
		//singleton
	}
	
	public ProjectorInfra create(SlideShowBuilder builder) {
		return new ProjectorInfraImp(new XMLAccessor(builder)); //Omzetten naar chain responsiblity
	}
}