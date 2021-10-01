package nl.ou.jp.gui.implementation;

import java.awt.MenuBar;
import java.awt.event.*;

import nl.ou.jp.gui.model.DrawStrategy;
import nl.ou.jp.gui.model.ProjectorConfiguration;

public class ProjectorGUIBuilder {
	private DrawStrategy strategy;
	private MenuBar menubar;
	private WindowListener windowListener;
	private KeyListener keyListener;
	private ProjectorConfiguration configurationDefault;

	public ProjectorGUIBuilder withStrategy(DrawStrategy strategy) {
		this.strategy = strategy;
		return this;
	}
	
	public ProjectorGUIBuilder withMenuBar(MenuBar menubar) {
		this.menubar = menubar;
		return this;
	}
	
	public ProjectorGUIBuilder withWindowListener(WindowListener windowListener) {
		this.windowListener = windowListener;
		return this;
	}
	
	public ProjectorGUIBuilder withKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
		return this;
	}
	
	public ProjectorGUIBuilder withConfiguration(ProjectorConfiguration configurationDefault) {
		this.configurationDefault = configurationDefault;
		return this;
	}
			
	public ProjectorGUIImp build() {
		return new ProjectorGUIImp(
				this.strategy,
				this.menubar,
				this.windowListener,
				this.keyListener,
				this.configurationDefault
				);
	}
}