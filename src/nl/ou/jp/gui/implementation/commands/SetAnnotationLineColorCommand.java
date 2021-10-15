package nl.ou.jp.gui.implementation.commands;

import java.awt.Color;
import java.util.Collection;
import java.util.Iterator;

public class SetAnnotationLineColorCommand extends ProjectorCommandTemplate implements GetMessageMixin{

	private static final String LINECOLORTITLEID = "COLORTITLE";
	private static final String CURRENTVALUEID = "CURRENTVALUE";
	private static final String CHOOSECOLORID = "CHOOSECOLOR";
	private static final String NAME = "SETANNOTATIONLINECOLOR";
	
	@Override
	public void execute() {
		int currentvalue = this.mediator.getAnnotationLineColor();
		String currentColorName = configuration.getColorName(currentvalue);

		Collection<String> colors = configuration.getColors().keySet();
		
		String[] options = new String[colors.size()];
		
		Iterator<String> it = colors.iterator();
		int index = 0;
		while(it.hasNext()) {
			options[index] = it.next();
			index++;
		}
		String message = getMessage(configuration,CHOOSECOLORID)+" "+getMessage(configuration,CURRENTVALUEID)+" "+currentColorName;
		String title = getMessage(configuration,LINECOLORTITLEID);
		int value = this.mediator.showItemSelectorDialog(message, title, options);
		if(value < 0) {
			return;
		}
		String sv = options[value];
		Color color = configuration.getColors().get(sv);
		this.mediator.setAnnotationLineColor(color.getRGB());
	}
	
	@Override
	public String getName() {
		return NAME;
	}
}
