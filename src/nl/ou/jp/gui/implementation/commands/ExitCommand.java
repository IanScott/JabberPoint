package nl.ou.jp.gui.implementation.commands;

public class ExitCommand extends ProjectorCommandTemplate {
	private static final String NAME = "EXIT";

	@Override
	public void execute() {		
		mediator.exit();
	}

	@Override
	public String getName() {
		return NAME;
	}
}
