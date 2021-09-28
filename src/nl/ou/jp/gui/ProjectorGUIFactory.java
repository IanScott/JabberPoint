package nl.ou.jp.gui;

import java.awt.MenuBar;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.util.EnumMap;
import java.util.Map;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.implementation.*;
import nl.ou.jp.gui.implementation.commands.*;
import nl.ou.jp.gui.model.*;
import nl.ou.jp.gui.subfactories.*;

public class ProjectorGUIFactory {	
	
	private static ProjectorGUIFactory instance = null;
	private ProjectorController projectorController = null;
	
	public static ProjectorGUIFactory getInstance() {
		if(instance == null) {
			instance = new ProjectorGUIFactory();
		}
		return instance;
	}
		
	private ProjectorGUIFactory() {
		//singleton
	}
	
	public ProjectorGUI create(ProjectorController projectorController) {
		this.projectorController = projectorController;
		return createDefaultProjectFrame();
	}
	
	private ProjectorGUI createDefaultProjectFrame() {		
		ProjectorConfiguration configurationDefault = new DefaultConfiguration();
		
		ProjectorContext projectorContext = new ProjectorContextImp();
		projectorContext.setProjector(this.projectorController);
		
		var commands = intializeCommands(projectorContext);
		
		MenuBar menubar =   new ProjectorViewMenuBarFactory(commands,configurationDefault).create();
		WindowListener windowlistener = new ProjectorViewWindowListenerFactory(commands).create();
		KeyListener keylistener = new ProjectorViewKeyListenerFactory(commands).create();
		
		SwingDrawStrategy strategy = new SlideDrawStrategy(new TextDrawStrategy(new FigureDrawStrategy(null)));
		
		ProjectorGUIImp projectorView = new ProjectorFrameBuilder()
			.withStrategy(strategy)		
			.withKeyListener(keylistener) 
			.withWindowListener(windowlistener)
			.withMenuBar(menubar)
			.withConfiguration(configurationDefault)
			.build();
		
		projectorView.initialize(projectorContext);
		
		return projectorView;
	}
	
	private Map<CommandNames,ProjectorCommand> intializeCommands(ProjectorContext projectorContext) {
		Map<CommandNames,ProjectorCommand>  projectorCommands = new EnumMap<>(CommandNames.class);
		projectorCommands.put(CommandNames.GOTOSLIDE, new GotoSlideCommand(projectorContext));
		projectorCommands.put(CommandNames.NEXTSLIDE, new NextSlideCommand(projectorContext));
		projectorCommands.put(CommandNames.PREVIOUSSLIDE, new PreviousSlideCommand(projectorContext));
		projectorCommands.put(CommandNames.OPEN, new OpenCommand(projectorContext));
		projectorCommands.put(CommandNames.EXIT, new ExitCommand(projectorContext));
		projectorCommands.put(CommandNames.SAVE, new SaveCommand(projectorContext));
		projectorCommands.put(CommandNames.ABOUT, new AboutCommand(projectorContext));
		projectorCommands.put(CommandNames.RESET, new ResetCommand(projectorContext));
		
		return projectorCommands;
	}
}
