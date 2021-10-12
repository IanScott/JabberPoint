package nl.ou.jp.gui.implementation.uicontrollers;

import java.awt.event.*;
import java.util.Map;

import nl.ou.jp.gui.model.ProjectorCommand;

public class ProjectorGUIMouseListener extends MouseAdapter {
	private Map<String, ProjectorCommand> projectorCommands = null; 
	
	public ProjectorGUIMouseListener(Map<String, ProjectorCommand> projectorCommands) {
		this.projectorCommands = projectorCommands;
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		this.projectorCommands.get("STARTLINEANNOTATION").execute();
	}
}
