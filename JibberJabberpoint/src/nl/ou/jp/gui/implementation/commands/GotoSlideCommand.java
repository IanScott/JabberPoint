package nl.ou.jp.gui.implementation.commands;

public class GotoSlideCommand extends ProjectorCommandTemplate implements GetMessageMixin {
	private static final String NAME = "GOTOSLIDE";
	private static final String PAGENUMBERID = "PAGENUMBER";

	@Override
	public void execute() {
		String message = getMessage(configuration, PAGENUMBERID);
		mediator.gotoSlideNumber(message);			
	}

	@Override
	public String getName() {
		return NAME;
	}
}