package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorConfiguration;
import nl.ou.jp.gui.model.ProjectorContext;

public class AboutCommand implements ProjectorCommand {
	
	private static final String ABOUTMESSAGEID = "ABOUTMESSAGE";
	private static final String ABOUTMESSAGETITLEID = "ABOUTMESSAGETITLE";
	private ProjectorContext projectorContext = null;
	
	public AboutCommand(ProjectorContext projectorContext) {
		this.projectorContext = projectorContext;
	}

	@Override
	public void execute() {
		ProjectorConfiguration config = projectorContext.getConfiguration();
		String title = config.getMessage(ABOUTMESSAGETITLEID);
		String message = config.getMessage(ABOUTMESSAGEID);
		projectorContext.getMainGUI().showMessageDialog(message, title);
	}
}