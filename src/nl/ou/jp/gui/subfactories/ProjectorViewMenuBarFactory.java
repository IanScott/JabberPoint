package nl.ou.jp.gui.subfactories;

import java.awt.*;
import java.util.Map;

import nl.ou.jp.gui.ProjectorGUIException;
import nl.ou.jp.gui.implementation.commands.CommandNames;
import nl.ou.jp.gui.model.*;


/** <p>De controller voor het menu</p>
 */
public class ProjectorViewMenuBarFactory {
	private MenuBar mainMenuBar;
	private ProjectorConfiguration projectorConfiguration;
	
	private static ProjectorViewMenuBarFactory instance = null;
	
	public static ProjectorViewMenuBarFactory getInstance() {
		if(instance == null) {
			instance = new ProjectorViewMenuBarFactory();
		}
		return instance;
	}
	
	private ProjectorViewMenuBarFactory() {
		//singleton
	}
	
	public MenuBar create(Map<CommandNames, ProjectorCommand> projectorCommands, ProjectorConfiguration projectorConfiguration) {
		this.projectorConfiguration = projectorConfiguration;
		initializeFileMenu(projectorCommands);
		initializaViewMenu(projectorCommands);
		initializeHelpMenu(projectorCommands);
		return this.mainMenuBar;
	}
	
	private String getName(String id) {
		return this.projectorConfiguration.getMenuName(id);
	}
	
	private void initializeFileMenu(Map<CommandNames, ProjectorCommand> projectorCommands) {
		Menu fileMenu = new Menu(getName("FILE"));
		addActionMenu(fileMenu, getName("OPEN"), projectorCommands.get(CommandNames.OPEN));
		addActionMenu(fileMenu, getName("SAVE"), projectorCommands.get(CommandNames.SAVE));
		addActionMenu(fileMenu, getName("NEW"), projectorCommands.get(CommandNames.RESET));
		fileMenu.addSeparator();
		addActionMenu(fileMenu, getName("EXIT"), projectorCommands.get(CommandNames.EXIT));

		add(fileMenu);
	}

	private void initializaViewMenu(Map<CommandNames, ProjectorCommand> projectorCommands) {
		Menu viewMenu = new Menu(getName("VIEW"));
		addActionMenu(viewMenu, getName("NEXT"), projectorCommands.get(CommandNames.NEXTSLIDE));
		addActionMenu(viewMenu, getName("PREVIOUS"), projectorCommands.get(CommandNames.PREVIOUSSLIDE));
		addActionMenu(viewMenu, getName("GOTO"), projectorCommands.get(CommandNames.GOTOSLIDE));
		add(viewMenu);
	}
	
	private void initializeHelpMenu(Map<CommandNames, ProjectorCommand> projectorCommands) {
		Menu helpMenu = new Menu(getName("HELP"));
		addActionMenu(helpMenu, getName("ABOUT"), projectorCommands.get(CommandNames.ABOUT));
		this.setHelpMenu(helpMenu);	
	}
	
	private void addActionMenu(Menu menu, String name, ProjectorCommand projectorCommand) {
		MenuItem menuItem = mkMenuItem(name);
		menuItem.addActionListener(x -> {
				if(projectorCommand == null) {
					throw new ProjectorGUIException("Failed Loading Command.");
				}
				projectorCommand.execute();
			});
		menu.add(menuItem);
	}
	
	private MenuItem mkMenuItem(String name) {
		return new MenuItem(name, new MenuShortcut(name.charAt(0)));
	}
	
	private void add(Menu menu) {
		if(this.mainMenuBar == null) {
			this.mainMenuBar = new MenuBar();
		}
		this.mainMenuBar.add(menu);
	}
	
	private void setHelpMenu(Menu menu) {
		if(this.mainMenuBar == null) {
			this.mainMenuBar = new MenuBar();
		}
		this.mainMenuBar.setHelpMenu(menu);
	}
}