package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorConfiguration;
import nl.ou.jp.gui.model.ProjectorMediator;

public class SaveCommand implements ProjectorCommand, GetMessageMixin {
	private static final String NAME = "SAVE";
	private static final String ERRORID = "LOADERROR";
	private static final String IOEXCEPTIONID = "IOEXCEPTION";
	private ProjectorMediator projectorMediator = null;
	private ProjectorConfiguration configuration = null;

	public SaveCommand(ProjectorMediator projectorMediator, ProjectorConfiguration configuration) {
		this.projectorMediator = projectorMediator;
		this.configuration = configuration;
	}
	
	@Override
	public void execute() {
		try {
			//stub
			projectorMediator.showErrorMessageDialog("Action is not implemented.", "error");
		} catch (Exception exc) {
			String excp = getMessage(configuration,IOEXCEPTIONID);
			String message = getMessage(configuration, ERRORID);
			
			projectorMediator.showErrorMessageDialog(excp + exc, message);
		}
	}

	@Override
	public String getName() {
		return NAME;
	}
}