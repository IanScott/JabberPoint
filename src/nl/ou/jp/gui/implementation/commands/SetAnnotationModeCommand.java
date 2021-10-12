package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.*;

public class SetAnnotationModeCommand  implements ProjectorCommand, GetMessageMixin {
	private static final String NAME = "SETANNOTATIONMODE";
	private ProjectorMediator mediator = null;
	private ProjectorConfiguration configuration = null;
	
	public SetAnnotationModeCommand(ProjectorMediator mediator, ProjectorConfiguration configuration) {
		this.mediator = mediator;
		this.configuration = configuration;
	}
	
	@Override
	public void execute() {
		boolean canAnnotate = this.mediator.canAnnotate();		
		String message = (canAnnotate)?getMessage(configuration, "ANNOOFF"):getMessage(configuration,"ANNOON");
		String title = getMessage(configuration, "ANNOMODE");
		String[] options = new String[] {getMessage(configuration,"YES"), getMessage(configuration,"NO")};
		
		int value = this.mediator.showItemSelectorDialog(message, title, options);
		if(canAnnotate && value == 0) {
			this.mediator.makeSlideShowReadOnly();
		}
		if(!canAnnotate && value == 0){
			this.mediator.enableSlideShowAnnotations();
		}			
	}
	
	@Override
	public String getName() {
		return NAME;
	}

}