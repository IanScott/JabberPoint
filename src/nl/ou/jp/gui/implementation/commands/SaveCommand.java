package nl.ou.jp.gui.implementation.commands;

public class SaveCommand extends ProjectorCommandTemplate implements GetMessageMixin {
	private static final String NAME = "SAVE";
	private static final String ERRORID = "LOADERROR";
	private static final String IOEXCEPTIONID = "IOEXCEPTION";
	
	@Override
	public void execute() {
		try {
			//stub
			mediator.showErrorMessageDialog("Action is not implemented.", "error");
		} catch (Exception exc) {
			String excp = getMessage(configuration,IOEXCEPTIONID);
			String message = getMessage(configuration, ERRORID);
			
			mediator.showErrorMessageDialog(excp + exc, message);
		}
	}

	@Override
	public String getName() {
		return NAME;
	}
}