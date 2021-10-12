package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorConfiguration;
import nl.ou.jp.gui.model.ProjectorMediator;

public class GotoSlideCommand implements ProjectorCommand, GetMessageMixin {
	private static final String NAME = "GOTO";
	private static final String PAGENUMBERID = "PAGENUMBER";
	
	private ProjectorMediator mediator;
	private ProjectorConfiguration configuration;
	
	public GotoSlideCommand(ProjectorMediator mediator, ProjectorConfiguration configuration) {
		this.mediator = mediator;
		this.configuration = configuration;
	}

	@Override
	public void execute() {
		String message = getMessage(configuration, PAGENUMBERID);
		mediator.gotoSlideNumber(message);			
	}

	@Override
	public String getName() {
		return NAME;
	}
}