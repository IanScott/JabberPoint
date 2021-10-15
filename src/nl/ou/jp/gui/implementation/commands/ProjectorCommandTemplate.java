package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.*;

public abstract class ProjectorCommandTemplate implements ProjectorCommand {
	protected ProjectorMediator mediator = null;
	protected ProjectorConfiguration configuration = null;

	@Override
	public void setProjectorMediator(ProjectorMediator mediator) {
		this.mediator = mediator;
	}

	@Override
	public void setProjectorConfiguration(ProjectorConfiguration configuration) {
		this.configuration = configuration;
	}
}
