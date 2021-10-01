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


public class DefaultVariant implements ProjectorVariant {
	private Map<CommandNames,ProjectorCommand> commands = null;
	private ProjectorConfiguration configuration = null;
	private ProjectorContext projectorContext = null;
	
	public DefaultVariant(ProjectorController projectorController) {
		projectorContext = new ProjectorContextImp();
		projectorContext.setController(projectorController);
		
		configuration = new DefaultConfiguration();
		commands = intializeCommands(projectorContext);
	}
	
	@Override
	public MenuBar createMenubar() {
		return ProjectorViewMenuBarFactory.getInstance().create(commands, configuration);
	}

	@Override
	public WindowListener createWindowlistener() {
		return ProjectorViewWindowListenerFactory.getInstance().create(commands);
	}

	@Override
	public KeyListener createKeylistener() {
		return ProjectorViewKeyListenerFactory.getInstance().create(commands);
	}

	@Override
	public DrawStrategy createDrawStrategy() {
		return new SlideDrawStrategy(new TextDrawStrategy(new FigureDrawStrategy(null)));
	}

	@Override
	public ProjectorConfiguration createConfiguration() {
		return this.configuration;
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

	@Override
	public ProjectorContext createContext() {
		return this.projectorContext;
	}

}
