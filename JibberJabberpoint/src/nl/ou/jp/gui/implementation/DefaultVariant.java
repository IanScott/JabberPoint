package nl.ou.jp.gui.implementation;

import java.awt.MenuBar;
import java.awt.event.*;
import java.util.*;

import javax.swing.event.MouseInputListener;

import nl.ou.jp.gui.implementation.commands.*;
import nl.ou.jp.gui.implementation.drawstrategies.*;
import nl.ou.jp.gui.implementation.uicontrollers.*;
import nl.ou.jp.gui.model.*;

public class DefaultVariant implements ProjectorVariant {
	private Map<String,ProjectorCommand> commands = null;
	private ProjectorConfiguration configuration = null;
	private ProjectorMediator projectorMediator = null;
	
	public DefaultVariant() {
		projectorMediator = new ProjectorMediatorImp();
		
		configuration = new DefaultConfiguration();
		commands = intializeCommands(projectorMediator, configuration);
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
	
	private Map<String,ProjectorCommand> intializeCommands(ProjectorMediator projectorMediator, ProjectorConfiguration configuration) {
		ProjectorCommand[] commandlist = new ProjectorCommand[] {
				new GotoSlideCommand(),
				new NextSlideCommand(),
				new PreviousSlideCommand(),
				new OpenCommand(),
				new ExitCommand(),
				new SaveCommand(),
				new AboutCommand(),
				new ResetCommand(),
				new AddDataToLineAnnotationCommand(),
				new StartLineAnnotationCommand(),
				new SetAnnotationLineColorCommand(),
				new SetAnnotationLineWeightCommand(),
				new SetAnnotationModeCommand()
		};
		
		for(ProjectorCommand com: commandlist) {
			com.setProjectorMediator(projectorMediator);
			com.setProjectorConfiguration(configuration);
		}
		
		Map<String,ProjectorCommand>  projectorCommands = new HashMap<>();
		for(ProjectorCommand pc: commandlist) {
			projectorCommands.put(pc.getName(), pc);
		}
		return projectorCommands;
	}

	@Override
	public ProjectorMediator getMediator() {
		return this.projectorMediator;
	}

	@Override
	public MouseInputListener getMouseInputListener() {
		return new ProjectorGUIMouseInputListener(commands, projectorMediator);
	}

	@Override
	public MouseListener getMouseListener() {
		return new ProjectorGUIMouseListener(commands);
	}
}
