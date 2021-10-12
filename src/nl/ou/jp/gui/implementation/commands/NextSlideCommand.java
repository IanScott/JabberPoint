package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorMediator;

public class NextSlideCommand implements ProjectorCommand {
	private static final String NAME = "NEXTSLIDE";
	private ProjectorMediator mediator = null;
	
	public NextSlideCommand(ProjectorMediator mediator) {
		this.mediator = mediator;
	}
	
	@Override
	public void execute() {
		this.mediator.nextSlide();
	}

	@Override
	public String getName() {
		return NAME;
	}
}