package nl.ou.jp.gui.implementation.uicontrollers;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.event.MouseInputAdapter;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorContext;

public class ProjectorGUIMouseInputListener extends MouseInputAdapter{
	private Map<String, ProjectorCommand> projectorCommands = null; 
	private ProjectorContext context = null;
	
	public ProjectorGUIMouseInputListener(Map<String, ProjectorCommand> projectorCommands, ProjectorContext context) {
		this.projectorCommands = projectorCommands;
		this.context = context;
	}
		
	@Override
	public void mouseDragged(MouseEvent event) {
		Dimension canvas = this.context.getMainGUI().getCanvasDimension();
		this.context.setMouseRelativeXLocation((double)event.getX()/(double)canvas.width);
		this.context.setMouseRelativeYLocation((double)event.getY()/(double)canvas.height);
		this.projectorCommands.get("ADDDATATOLINEANNOTATION").execute();
	}
}