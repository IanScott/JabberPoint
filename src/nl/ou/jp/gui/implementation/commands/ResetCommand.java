package nl.ou.jp.gui.implementation.commands;

public class ResetCommand extends ProjectorCommandTemplate {
	private static final String NAME = "RESET";
	
	@Override
	public void execute() {
		this.mediator.reset();
	}
	
	@Override
	public String getName() {
		return NAME;
	}
}