package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.*;

public class StartLineAnnotationCommand implements ProjectorCommand {
	private static final String NAME = "STARTLINEANNOTATION";
	private ProjectorMediator mediator = null;
	
	public StartLineAnnotationCommand(ProjectorMediator mediator) {
		this.mediator = mediator;
	}
	
	@Override
	public void execute() {
		this.mediator.startLineAnnotation();
	}

	@Override
	public String getName() {
		return NAME;
	}
}
