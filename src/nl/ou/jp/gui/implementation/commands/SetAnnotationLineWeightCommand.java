package nl.ou.jp.gui.implementation.commands;

public class SetAnnotationLineWeightCommand extends ProjectorCommandTemplate implements GetMessageMixin {
	
	private static final String LINEWEIGHTTITLEID = "LINEWEIGHTTITLE";
	private static final String CURRENTVALUEID = "CURRENTVALUE";
	private static final String CHOOSELINEID = "CHOOSELINE";
	private static final String NAME = "SETANNOTATIONLINEWEIGHT";
	
	@Override
	public void execute() {
		int currentvalue = this.mediator.getAnnotationLineWeight();
		
		int[] lineweights = configuration.getLineWeights();
		String[] options = toStrings(lineweights);
		
		String message = getMessage(configuration,CHOOSELINEID)+" "+getMessage(configuration,CURRENTVALUEID)+" "+currentvalue;
		String title = getMessage(configuration,LINEWEIGHTTITLEID);

		int value = this.mediator.showItemSelectorDialog(message, title, options);
		if(value > -1) {
			int weight = Integer.parseInt(options[value]);
			this.mediator.setAnnotationLineWeight(weight);
		}
	}
	
	String[] toStrings(int[] ints) {
		String[] strings = new String[ints.length];
		for(int i=0; i<ints.length; i++) {
			strings[i] = ints[i]+"";
		}
		return strings;
	}
	
	@Override
	public String getName() {
		return NAME;
	}
}