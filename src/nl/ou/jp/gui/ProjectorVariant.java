package nl.ou.jp.gui;

import java.awt.MenuBar;
import java.awt.event.*;

import nl.ou.jp.gui.model.*;

public interface ProjectorVariant {
	MenuBar createMenubar();
	WindowListener createWindowlistener();
	KeyListener createKeylistener();
	DrawStrategy createDrawStrategy();
	ProjectorConfiguration createConfiguration();
	ProjectorContext createContext();
}
