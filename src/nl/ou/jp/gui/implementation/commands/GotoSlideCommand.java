package nl.ou.jp.gui.implementation.commands;

import javax.swing.JOptionPane;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorConfiguration;
import nl.ou.jp.gui.model.ProjectorContext;

public class GotoSlideCommand implements ProjectorCommand {
	private static final String NAME = "GOTO";
	private static final String PAGENUMBERID = "PAGENUMBER";
	private ProjectorContext projectorContext;
	
	public GotoSlideCommand(ProjectorContext projectorContext) {
		this.projectorContext = projectorContext;
	}

	@Override
	public void execute() {
		ProjectorController projectorController = this.projectorContext.getController();
		ProjectorConfiguration config = this.projectorContext.getConfiguration();
		String message = config.getMessage(PAGENUMBERID);
		
		if(projectorController.getSlideShowSize() > 0) {
			String pageNumberStr = JOptionPane.showInputDialog((Object)message);
			int pageNumber = Integer.parseInt(pageNumberStr);
			projectorController.gotoSlideNumber(pageNumber);			
		}
	}

	@Override
	public String getName() {
		return NAME;
	}
}