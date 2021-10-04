package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorContext;

public class NextSlideCommand implements ProjectorCommand {
	private static final String NAME = "NEXTSLIDE";
	private ProjectorContext projectorContext = null;
	
	public NextSlideCommand(ProjectorContext projectorContext) {
		this.projectorContext = projectorContext;
	}
	
	@Override
	public void execute() {
		ProjectorController projectorController = this.projectorContext.getController();
		projectorController.nextSlide();
	}

	@Override
	public String getName() {
		return NAME;
	}
}
