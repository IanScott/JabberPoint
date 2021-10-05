package nl.ou.jp.gui.implementation.commands;

import java.awt.Color;
import java.util.Collection;
import java.util.Iterator;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorContext;

public class SetAnnotationLineColorCommand implements ProjectorCommand {

	private ProjectorContext context = null;
	
	public SetAnnotationLineColorCommand(ProjectorContext context) {
		this.context = context;
	}
	
	@Override
	public void execute() {
		int currentvalue = this.context.getAnnotationLineColor();
		String currentColorName = this.context.getConfiguration().getColorName(currentvalue);

		Collection<String> colors = this.context.getConfiguration().getColors().keySet();
		
		String[] options = new String[colors.size()];
		
		Iterator<String> it = colors.iterator();
		int index = 0;
		while(it.hasNext()) {
			options[index] = it.next();
			index++;
		}
		
		int value = this.context.getMainGUI().showItemSelectorDialog("Choose an Annotation Color. Current value: "+currentColorName, "Annotation Line Weigth", options);
		if(value < 0) {
			return;
		}
		String sv = options[value];
		Color color = this.context.getConfiguration().getColors().get(sv);
		this.context.setAnnotationLineColor(color.getRGB());
	}

	@Override
	public String getName() {
		return "SETANNOTATIONLINECOLOR";
	}

}
