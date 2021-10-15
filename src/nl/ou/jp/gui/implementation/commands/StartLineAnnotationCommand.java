package nl.ou.jp.gui.implementation.commands;

public class StartLineAnnotationCommand extends ProjectorCommandTemplate implements GetMessageMixin {
	private static final String NAME = "STARTLINEANNOTATION";
	
	@Override
	public void execute() {
		this.mediator.startLineAnnotation();
	}

	@Override
	public String getName() {
		return NAME;
	}
}
