package nl.ou.jp.gui.implementation.commands;

public class NextSlideCommand extends ProjectorCommandTemplate {
	private static final String NAME = "NEXTSLIDE";
	
	@Override
	public void execute() {
		this.mediator.nextSlide();
	}

	@Override
	public String getName() {
		return NAME;
	}
}