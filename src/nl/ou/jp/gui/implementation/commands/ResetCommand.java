package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorMediator;

public class ResetCommand implements ProjectorCommand {
	private static final String NAME = "RESET";
	private ProjectorMediator mediator;
	
	public ResetCommand(ProjectorMediator mediator) {
		this.mediator = mediator;
	}
	
	@Override
	public void execute() {
		this.mediator.reset();
	}
	
	@Override
	public String getName() {
		return NAME;
	}
}