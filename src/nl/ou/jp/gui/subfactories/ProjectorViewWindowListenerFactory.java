package nl.ou.jp.gui.subfactories;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Map;

import nl.ou.jp.gui.ProjectorGUIException;
import nl.ou.jp.gui.implementation.commands.CommandNames;
import nl.ou.jp.gui.model.ProjectorCommand;

public class ProjectorViewWindowListenerFactory {
	private static ProjectorViewWindowListenerFactory instance = null;
	
	public static ProjectorViewWindowListenerFactory getInstance() {
		if(instance == null) {
			instance = new ProjectorViewWindowListenerFactory();
		}
		return instance;
	}
	
	public ProjectorViewWindowListenerFactory() {
		//singleton
	}
	
	public WindowListener create(Map<CommandNames, ProjectorCommand> projectorCommands) {
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