package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorContext;

public class StartLineAnnotationCommand implements ProjectorCommand {
	private static final String NAME = "STARTLINEANNOTATION";
	private ProjectorContext context = null;
	
	public StartLineAnnotationCommand(ProjectorContext context) {
		this.context = context;
	}
	
	@Override
	public void execute() {
		int weight = this.context.getAnnotationLineWeight();
		int color = this.context.getAnnotationLineColor();
		this.context.getController().startLineAnnotation(weight, color);
	}

	@Override
	public String getName() {
		return NAME;
	}
}
