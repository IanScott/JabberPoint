package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorMediator;

public class PreviousSlideCommand implements ProjectorCommand {
	private static final String NAME = "PREVIOUSSLIDE";
	private ProjectorMediator mediator;
	
	public PreviousSlideCommand(ProjectorMediator mediator) {
		this.mediator = mediator;
	}
	
	@Override
	public void execute() {
		this.mediator.previousSlide();
	}

	@Override
	public String getName() {
		return NAME;
	}
}