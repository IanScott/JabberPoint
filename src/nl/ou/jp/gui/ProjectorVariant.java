package nl.ou.jp.gui;

import java.awt.MenuBar;
import java.awt.event.*;

import javax.swing.event.MouseInputAdapter;

import nl.ou.jp.gui.model.DrawStrategy;
import nl.ou.jp.gui.model.ProjectorConfiguration;
import nl.ou.jp.gui.model.ProjectorContext;

public interface ProjectorVariant {
	MenuBar getMenubar();
	WindowListener getWindowlistener();
	KeyListener getKeylistener();
	MouseInputAdapter getMouseInputAdapter();
	DrawStrategy getDrawStrategy();
	ProjectorConfiguration getConfiguration();
	ProjectorContext getContext();
}
