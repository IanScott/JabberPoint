package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorContext;

public class SetAnnotationModeCommand  implements ProjectorCommand{
	private ProjectorContext context = null;
	
	public SetAnnotationModeCommand(ProjectorContext context) {
		this.context = context;
	}
	
	@Override
	public void execute() {
		if(this.context.getController().getCurrentSlideNumber()>-1) {
			boolean canAnnotate = this.context.getController().canAnnotate();		
			String message = (canAnnotate)?"Turn off Annotations?":"Turn on Annotations?";
			
			int value = this.context.getMainGUI().showItemSelectorDialog(message, "Annotation Mode", new String[] {"YES","NO"});
			if(canAnnotate && value == 0) {
				this.context.getController().makeSlideShowReadOnly();
			}
			if(!canAnnotate && value == 0){
				this.context.getController().enableSlideShowAnnotations();
			}			
		}else {
			this.context.getMainGUI().showErrorMessageDialog("Please Loade a Slide Show.","Annotation Mode");
		}
		
	}
	
	
	@Override
	public String getName() {
		return "SETANNOTATIONMODE";
	}

}