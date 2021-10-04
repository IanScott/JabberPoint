package nl.ou.jp.gui.implementation.uicontrollers;

import static nl.ou.jp.gui.implementation.uicontrollers.ControllerConstants.*;

import java.awt.*;
import java.util.Map;

import nl.ou.jp.gui.ProjectorGUIException;
import nl.ou.jp.gui.model.*;


/** <p>De controller voor het menu</p>
 */
public class ProjectorGUIMenuBar extends MenuBar {
	private static final long serialVersionUID = -3949417015554658852L;
	private MenuBar mainMenuBar = this;
	private transient ProjectorConfiguration projectorConfiguration;
	
	public ProjectorGUIMenuBar(Map<String, ProjectorCommand> projectorCommands, ProjectorConfiguration projectorConfiguration) {
		this.projectorConfiguration = projectorConfiguration;
		initializeFileMenu(projectorCommands);
		initializaViewMenu(projectorCommands);
		initializeHelpMenu(projectorCommands);
	}
	

	
	private String getName(String id) {
		return this.projectorConfiguration.getMenuName(id);
	}
	
	private void initializeFileMenu(Map<String, ProjectorCommand> projectorCommands) {
		Menu fileMenu = new Menu(getName(FILEMENUNAME));
		addActionMenu(fileMenu, getName(OPENCOMMANDNAME), projectorCommands.get(OPENCOMMANDNAME));
		addActionMenu(fileMenu, getName(SAVECOMMANDNAME), projectorCommands.get(SAVECOMMANDNAME));
		addActionMenu(fileMenu, getName(NEWMENUNAME), projectorCommands.get(RESETCOMMANDNAME));
		fileMenu.addSeparator();
		addActionMenu(fileMenu, getName(EXITCOMMANDNAME), projectorCommands.get(EXITCOMMANDNAME));

		addMenu(fileMenu);
	}

	private void initializaViewMenu(Map<String, ProjectorCommand> projectorCommands) {
		Menu viewMenu = new Menu(getName(VIEWMENUNAME));
		addActionMenu(viewMenu, getName(NEXTMENUNAME), projectorCommands.get(NEXTSLIDECOMMANDNAME));
		addActionMenu(viewMenu, getName(PREVIOUSMENUNAME), projectorCommands.get(PREVIOUSSLIDECOMMANDNAME));
		addActionMenu(viewMenu, getName(GOTOMENUNAME), projectorCommands.get(GOTOSLIDECOMMANDNAME));
		addMenu(viewMenu);
	}
	
	private void initializeHelpMenu(Map<String, ProjectorCommand> projectorCommands) {
		Menu helpMenu = new Menu(getName(HELPMENUNAME));
		addActionMenu(helpMenu, getName(ABOUTCOMMANDNAME), projectorCommands.get(ABOUTCOMMANDNAME));
		this.setDefaultHelpMenu(helpMenu);	
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
	
	private void addMenu(Menu menu) {
		if(this.mainMenuBar == null) {
			this.mainMenuBar = new MenuBar();
		}
		this.mainMenuBar.add(menu);
	}
	
	private void setDefaultHelpMenu(Menu menu) {
		if(this.mainMenuBar == null) {
			this.mainMenuBar = new MenuBar();
		}
		this.mainMenuBar.setHelpMenu(menu);
	}
}