package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorContext;

public class ExitCommand implements ProjectorCommand {
	private ProjectorContext projectorContext = null;
	public ExitCommand(ProjectorContext projectorContext) {
		this.projectorContext = projectorContext;
	}
	
	@Override
	public void execute() {		
		projectorContext.getMainGUI().exit();
	}

}
