package nl.ou.jp.gui.implementation.uicontrollers;

import static nl.ou.jp.gui.implementation.uicontrollers.ControllerConstants.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import nl.ou.jp.gui.ProjectorGUIException;
import nl.ou.jp.gui.model.ProjectorCommand;

public class ProjectorGUIWindowListener extends WindowAdapter {

	Map<String, ProjectorCommand> projectorCommands = null;
	
	public ProjectorGUIWindowListener(Map<String, ProjectorCommand> projectorCommands) {
		this.projectorCommands = projectorCommands;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		ProjectorCommand exit = projectorCommands.get(EXITCOMMANDNAME);
		if(exit != null) {
			exit.execute();
		}else {
			throw new ProjectorGUIException("Error loading Command");
		}
	}	
	
}