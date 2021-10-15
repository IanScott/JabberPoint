package nl.ou.jp.gui.model;

import java.awt.MenuBar;
import java.awt.event.*;

import javax.swing.event.MouseInputListener;

public interface ProjectorVariant {
	MenuBar getMenubar();
	WindowListener getWindowlistener();
	KeyListener getKeylistener();
	
	MouseInputListener getMouseInputListener();
	MouseListener getMouseListener();
	
	DrawStrategy getDrawStrategy();
	ProjectorConfiguration getConfiguration();
	ProjectorMediator getContext();
}
