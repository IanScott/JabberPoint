package nl.ou.jp.gui.implementation.uicontrollers;

import static nl.ou.jp.gui.implementation.uicontrollers.ControllerConstants.*;

import java.awt.event.*;
import java.util.*;
import java.util.function.Consumer;

import nl.ou.jp.gui.ProjectorGUIException;
import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.logging.*;

public class ProjectorGUIKeyListener extends KeyAdapter {

	private Logger logger = LoggerManager.getLogger();
	
	private Map<String, ProjectorCommand> projectorCommands = null;
	private Map<Integer, Consumer<KeyEvent>> actionmapping = null;
	
	
	public ProjectorGUIKeyListener(Map<String, ProjectorCommand> projectorCommands) {
		this.projectorCommands = projectorCommands;
	}
	
	@Override
	public void keyPressed(KeyEvent keyEvent) {
		keyPressedImp(keyEvent);
	}
	
	private void keyPressedImp(final KeyEvent keyEvent) {
		initActionMapping();	
		this.actionmapping.getOrDefault(keyEvent.getKeyCode(), this::unSupported).accept(keyEvent);
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
	

	private void exitAction(KeyEvent keyEvent) {
		loadCommand(this.projectorCommands.get(EXITCOMMANDNAME));
	}

	private void previousAction(KeyEvent keyEvent) {
		loadCommand(this.projectorCommands.get(PREVIOUSSLIDECOMMANDNAME));
	}

	private void nextAction(KeyEvent keyEvent) {
		loadCommand(this.projectorCommands.get(NEXTSLIDECOMMANDNAME));
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