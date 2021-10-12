package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.*;

public class AddDataToLineAnnotationCommand implements ProjectorCommand {

	private static final String NAME = "ADDDATATOLINEANNOTATION";
	private ProjectorMediator mediator = null;
	
	public AddDataToLineAnnotationCommand(ProjectorMediator mediator) {
		this.mediator = mediator;
	}
	
	@Override
	public void execute() {
		this.mediator.addToLineAnnotation();				
	}

	@Override
	public String getName() {
		return NAME;
	}
}
