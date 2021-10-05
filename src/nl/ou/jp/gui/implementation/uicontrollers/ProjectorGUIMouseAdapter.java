package nl.ou.jp.gui.implementation.uicontrollers;

import java.awt.Container;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorContext;

public class ProjectorGUIMouseAdapter extends MouseInputAdapter {
	private Map<String, ProjectorCommand> projectorCommands = null; 
	private ProjectorContext context = null;
	
	public ProjectorGUIMouseAdapter(Map<String, ProjectorCommand> projectorCommands, ProjectorContext context) {
		this.projectorCommands = projectorCommands;
		this.context = context;
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		this.projectorCommands.get("STARTLINEANNOTATION").execute();
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		Container container = ((JFrame)this.context.getMainGUI()).getContentPane();

		this.context.setMouseRelativeXLocation((double)event.getX()/(double)container.getWidth());
		this.context.setMouseRelativeYLocation((double)event.getY()/(double)container.getHeight());
		this.projectorCommands.get("ADDDATATOLINEANNOTATION").execute();
	}
}
