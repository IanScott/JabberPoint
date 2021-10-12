package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorConfiguration;
import nl.ou.jp.gui.model.ProjectorMediator;

public class AboutCommand implements ProjectorCommand, GetMessageMixin {	
	private static final String NAME = "ABOUT";
	private static final String ABOUTMESSAGEID = "ABOUTMESSAGE";
	private static final String ABOUTMESSAGETITLEID = "ABOUTMESSAGETITLE";
	private ProjectorMediator projectorMediator = null;
	private ProjectorConfiguration configuration = null;
	
	public AboutCommand(ProjectorMediator projectorMediator, ProjectorConfiguration configuration) {
		this.projectorMediator = projectorMediator;
		this.configuration = configuration;
	}

	@Override
	public void execute() {
		String title = getMessage(configuration,ABOUTMESSAGETITLEID);
		String message = getMessage(configuration,ABOUTMESSAGEID);
		projectorMediator.showMessageDialog(message, title);
	}

	@Override
	public String getName() {
		return NAME;
	}
}