package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorConfiguration;
import nl.ou.jp.gui.model.ProjectorContext;

public class SaveCommand implements ProjectorCommand {
	private static final String ERRORID = "LOADERROR";
	private static final String IOEXCEPTIONID = "IOEXCEPTION";
	private ProjectorContext projectorContext = null;

	public SaveCommand(ProjectorContext projectorContext) {
		this.projectorContext = projectorContext;
	}
	
	@Override
	public void execute() {
		try {
			//stub
			projectorContext.getMainGUI().showErrorMessage("Action is not implemented.", "error");
		} catch (Exception exc) {
			ProjectorConfiguration config = this.projectorContext.getConfiguration();
			String excp = config.getMessage(IOEXCEPTIONID);
			String message = config.getMessage(ERRORID);
			
			projectorContext.getMainGUI().showErrorMessage(excp + exc, message);
		}
	}
}