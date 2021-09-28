package nl.ou.jp.gui.subfactories;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map;

import nl.ou.jp.gui.ProjectorGUIException;
import nl.ou.jp.gui.implementation.commands.CommandNames;
import nl.ou.jp.gui.model.ProjectorCommand;

public class ProjectorViewWindowListenerFactory {
	
	private Map<CommandNames, ProjectorCommand> projectorCommands;
	
	public ProjectorViewWindowListenerFactory(Map<CommandNames, ProjectorCommand> projectorCommands ) {
		this.projectorCommands = projectorCommands;
	}
	
	public WindowListener create() {
		return new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ProjectorCommand exit = projectorCommands.get(CommandNames.EXIT);
				if(exit != null) {
					exit.execute();
				}else {
					throw new ProjectorGUIException("Error loading Command");
				}
			}			
		};
	}
}