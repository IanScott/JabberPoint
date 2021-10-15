package nl.ou.jp.gui.implementation.commands;

public class AboutCommand extends ProjectorCommandTemplate implements GetMessageMixin  {	
	private static final String NAME = "ABOUT";
	private static final String ABOUTMESSAGEID = "ABOUTMESSAGE";
	private static final String ABOUTMESSAGETITLEID = "ABOUTMESSAGETITLE";

	@Override
	public void execute() {
		String title = getMessage(configuration,ABOUTMESSAGETITLEID);
		String message = getMessage(configuration,ABOUTMESSAGEID);
		mediator.showMessageDialog(message, title);
	}

	@Override
	public String getName() {
		return NAME;
	}
}