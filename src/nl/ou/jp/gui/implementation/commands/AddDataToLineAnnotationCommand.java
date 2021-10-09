package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorContext;

public class AddDataToLineAnnotationCommand implements ProjectorCommand {

	private static final String NAME = "ADDDATATOLINEANNOTATION";
	private ProjectorContext context = null;
	
	public AddDataToLineAnnotationCommand(ProjectorContext context) {
		this.context = context;
	}
	
	@Override
	public void execute() {
		double x = this.context.getMouseRelativeXLocation();
		double y = this.context.getMouseRelativeYLocation();
		this.context.getController().addToLineAnnotation(x, y);				
	}

	@Override
	public String getName() {
		return NAME;
	}
}
