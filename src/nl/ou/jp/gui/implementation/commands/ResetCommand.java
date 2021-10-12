package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorContext;

public class ResetCommand implements ProjectorCommand {
	private static final String NAME = "RESET";
	private ProjectorContext projectorContext = null;
	
	public ResetCommand(ProjectorContext projectorContext) {
		this.projectorContext = projectorContext;
	}
	
	@Override
	public void execute() {
		ProjectorController projectorController = this.projectorContext.getController();
		projectorController.reset();
	}
	
	@Override
	public String getName() {
		return NAME;
	}
}