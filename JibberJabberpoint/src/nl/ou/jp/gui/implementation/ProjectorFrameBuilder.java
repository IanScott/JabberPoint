package nl.ou.jp.gui.implementation;

import java.awt.MenuBar;
import java.awt.event.*;

import nl.ou.jp.gui.model.ProjectorConfiguration;

public class ProjectorFrameBuilder {
	private SwingDrawStrategy strategy;
	private MenuBar menubar;
	private WindowListener windowListener;
	private KeyListener keyListener;
	private ProjectorConfiguration configurationDefault;

	public ProjectorFrameBuilder withStrategy(SwingDrawStrategy strategy) {
		this.strategy = strategy;
		return this;
	}
	
	public ProjectorFrameBuilder withMenuBar(MenuBar menubar) {
		this.menubar = menubar;
		return this;
	}
	
	public ProjectorFrameBuilder withWindowListener(WindowListener windowListener) {
		this.windowListener = windowListener;
		return this;
	}
	
	public ProjectorFrameBuilder withKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
		return this;
	}
	
	public ProjectorFrameBuilder withConfiguration(ProjectorConfiguration configurationDefault) {
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