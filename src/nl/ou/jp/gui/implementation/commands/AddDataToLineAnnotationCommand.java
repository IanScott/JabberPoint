package nl.ou.jp.gui.implementation.commands;

public class AddDataToLineAnnotationCommand extends ProjectorCommandTemplate {

	private static final String NAME = "ADDDATATOLINEANNOTATION";

	@Override
	public void execute() {
		this.mediator.addToLineAnnotation();				
	}

	@Override
	public String getName() {
		return NAME;
	}
}
