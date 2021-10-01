package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorContext;

public class PreviousSlideCommand implements ProjectorCommand {
	private ProjectorContext projectorContext = null;
	
	public PreviousSlideCommand(ProjectorContext projectorContext) {
		this.projectorContext = projectorContext;
	}
	
	@Override
	public void execute() {
		ProjectorController projectorController = projectorContext.getController();
		projectorController.previousSlide();
	}
}
