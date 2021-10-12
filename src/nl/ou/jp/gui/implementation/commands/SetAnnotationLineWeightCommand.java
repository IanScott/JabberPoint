package nl.ou.jp.gui.implementation.commands;

import nl.ou.jp.gui.model.*;

public class SetAnnotationLineWeightCommand implements ProjectorCommand {
	
	private static final String LINEWEIGHTTITLEID = "LINEWEIGHTTITLE";
	private static final String CURRENTVALUEID = "CURRENTVALUE";
	private static final String CHOOSELINEID = "CHOOSELINE";
	private static final String NAME = "SETANNOTATIONLINEWEIGHT";
	
	private ProjectorContext context = null;
	
	public SetAnnotationLineWeightCommand(ProjectorContext context) {
		this.context = context;
	}
	
	@Override
	public void execute() {
		int currentvalue = this.context.getAnnotationLineWeight();
		
		int[] lineweights = this.context.getConfiguration().getLineWeights();
		String[] options = toStrings(lineweights);
		
		String message = getMessage(CHOOSELINEID)+" "+getMessage(CURRENTVALUEID)+" "+currentvalue;
		String title = getMessage(LINEWEIGHTTITLEID);

		int value = this.context.getMainGUI().showItemSelectorDialog(message, title, options);
		if(value > -1) {
			int weight = Integer.parseInt(options[value]);
			this.context.setAnnotationLineWeight(weight);
		}
	}
	
	String[] toStrings(int[] ints) {
		String[] strings = new String[ints.length];
		for(int i=0; i<ints.length; i++) {
			strings[i] = ints[i]+"";
		}
		return strings;
	}
	
	private String getMessage(String id) {
		return this.context.getConfiguration().getMessage(id);
	}
	
	@Override
	public String getName() {
		return NAME;
	}
}