package nl.ou.jp.gui.implementation.commands;

public class PreviousSlideCommand extends ProjectorCommandTemplate {
	private static final String NAME = "PREVIOUSSLIDE";

	@Override
	public void execute() {
		this.mediator.previousSlide();
	}

	@Override
	public String getName() {
		return NAME;
	}
}