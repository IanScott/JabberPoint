package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorMediator;

public class ExitCommand implements ProjectorCommand {
	private static final String NAME = "EXIT";
	private ProjectorMediator projectorMediator = null;
	
	public ExitCommand(ProjectorMediator projectorMediator) {
		this.projectorMediator = projectorMediator;
	}
	
	@Override
	public void execute() {		
		projectorMediator.exit();
	}

	@Override
	public String getName() {
		return NAME;
	}
}
