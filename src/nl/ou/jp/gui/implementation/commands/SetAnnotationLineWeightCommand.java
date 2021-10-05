package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorContext;

public class SetAnnotationLineWeightCommand implements ProjectorCommand {

	private ProjectorContext context = null;
	
	public SetAnnotationLineWeightCommand(ProjectorContext context) {
		this.context = context;
	}
	
	@Override
	public void execute() {
		int currentvalue = this.context.getAnnotationLineWeight();
		String[] options = new String[] {"1","2","3","4","5"};
		int value = this.context.getMainGUI().showItemSelectorDialog("Choose an Annotation Line Thickness. Current value: "+currentvalue, "Annotation Line Weigth", options);
		this.context.setAnnotationLineWeight(value+1);
	}

	@Override
	public String getName() {
		return "SETANNOTATIONLINEWEIGHT";
	}

}
