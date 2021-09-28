package nl.ou.jp.gui.subfactories;

import java.awt.event.*;
import java.util.*;
import java.util.function.Consumer;

import nl.ou.jp.gui.ProjectorGUIException;
import nl.ou.jp.gui.implementation.commands.CommandNames;
import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.logging.*;

public class ProjectorViewKeyListenerFactory {
	private Logger logger = LoggerManager.getLogger();
	private Map<CommandNames, ProjectorCommand> projectorCommands = null;
	private Map<Integer, Consumer<KeyEvent>> actionmapping = null;
	
	public ProjectorViewKeyListenerFactory(Map<CommandNames, ProjectorCommand> projectorCommands) {
		this.projectorCommands = projectorCommands;
	}
	
	public KeyListener create() {
		return new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent keyEvent) {
				keyPressedImp(keyEvent);
			}
		};
	}
	
	private void initActionMapping(){
		if(this.actionmapping == null) {
			this.actionmapping = new HashMap<>();
		
			this.actionmapping.put(KeyEvent.VK_PAGE_DOWN, this::nextAction);
			this.actionmapping.put(KeyEvent.VK_DOWN, this::nextAction);
			this.actionmapping.put(KeyEvent.VK_ENTER, this::nextAction);
		
			this.actionmapping.put(KeyEvent.VK_PAGE_UP, this::previousAction);
			this.actionmapping.put(KeyEvent.VK_UP, this::previousAction);
		
			this.actionmapping.put(Integer.valueOf('q'), this::exitAction);
			this.actionmapping.put(Integer.valueOf('Q'), this::exitAction);
		}
	}
	
	private void keyPressedImp(final KeyEvent keyEvent) {
		initActionMapping();
		
		this.actionmapping.getOrDefault(keyEvent.getKeyCode(), this::unSupported).accept(keyEvent);
	}

	private void exitAction(KeyEvent keyEvent) {
		loadCommand(this.projectorCommands.get(CommandNames.EXIT));
	}

	private void previousAction(KeyEvent keyEvent) {
		loadCommand(this.projectorCommands.get(CommandNames.PREVIOUSSLIDE));
	}

	private void nextAction(KeyEvent keyEvent) {
		loadCommand(this.projectorCommands.get(CommandNames.NEXTSLIDE));
	}

	private void unSupported(KeyEvent keyEvent) {
		logger.logError("The following keyCode was detected: "+keyEvent.getKeyCode());
		logger.logError("Event not supported by KeyListener.");
	}
	
	private void loadCommand(ProjectorCommand projectorCommand) {
		if(projectorCommand == null) {
			throw new ProjectorGUIException("Failed Loading Command.");
		}
		projectorCommand.execute();
	}
}