package nl.ou.jp.gui.implementation;

import java.awt.MenuBar;
import java.awt.event.*;
import java.util.*;

import javax.swing.event.MouseInputListener;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.gui.implementation.commands.*;
import nl.ou.jp.gui.implementation.drawstrategies.*;
import nl.ou.jp.gui.implementation.uicontrollers.*;
import nl.ou.jp.gui.model.*;


public class DefaultVariant implements ProjectorVariant {
	private Map<String,ProjectorCommand> commands = null;
	private ProjectorConfiguration configuration = null;
	private ProjectorContext projectorContext = null;
	
	public DefaultVariant(ProjectorController projectorController) {
		projectorContext = new ProjectorContextImp();
		projectorContext.setController(projectorController);
		
		configuration = new DefaultConfiguration();
		commands = intializeCommands(projectorContext);
	}
	
	@Override
	public MenuBar getMenubar() {
		return new ProjectorGUIMenuBar(commands, configuration);
	}

	@Override
	public WindowListener getWindowlistener() {
		return new ProjectorGUIWindowListener(commands);
	}

	@Override
	public KeyListener getKeylistener() {
		return new ProjectorGUIKeyListener(commands);
	}

	@Override
	public DrawStrategy getDrawStrategy() {
		return new SlideDrawStrategy(new TextDrawStrategy(new FigureDrawStrategy(new AnnotationLineDrawStrategy(null))));
	}

	@Override
	public ProjectorConfiguration getConfiguration() {
		return this.configuration;
	}
	
	private Map<String,ProjectorCommand> intializeCommands(ProjectorContext projectorContext) {
		ProjectorCommand[] commandlist = new ProjectorCommand[] {
				new GotoSlideCommand(projectorContext),
				new NextSlideCommand(projectorContext),
				new PreviousSlideCommand(projectorContext),
				new OpenCommand(projectorContext),
				new ExitCommand(projectorContext),
				new SaveCommand(projectorContext),
				new AboutCommand(projectorContext),
				new ResetCommand(projectorContext),
				new AddDataToLineAnnotationCommand(projectorContext),
				new StartLineAnnotationCommand(projectorContext),
				new SetAnnotationLineColorCommand(projectorContext),
				new SetAnnotationLineWeightCommand(projectorContext),
				new SetAnnotationModeCommand(projectorContext)
		};
		
		Map<String,ProjectorCommand>  projectorCommands = new HashMap<>();
		for(ProjectorCommand pc: commandlist) {
			projectorCommands.put(pc.getName(), pc);
		}
		return projectorCommands;
	}

	@Override
	public ProjectorContext getContext() {
		return this.projectorContext;
	}

	@Override
	public MouseInputListener getMouseInputListener() {
		return new ProjectorGUIMouseInputListener(commands, projectorContext);
	}

	@Override
	public MouseListener getMouseListener() {
		return new ProjectorGUIMouseListener(commands);
	}
}
