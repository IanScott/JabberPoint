package nl.ou.jp.gui;

import java.awt.Dimension;
import java.awt.MenuBar;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;

import javax.swing.event.MouseInputListener;

import nl.ou.jp.domain.*;

public interface ProjectorGUI extends SlideShowEventListener, SlideEventListener {
	void start(String path);	
	void exit();
	
	void showMessageDialog(String message, String title);
	void showErrorMessageDialog(String message, String title);
	int showItemSelectorDialog(String message, String title, String[] items);
	
	Dimension getCanvasDimension();
	
	void setMenubar(MenuBar menubar);
	void setWindowlistener(WindowListener listener);
	void setKeylistener(KeyListener listener);
	void setMouseListener(MouseListener listener);
	void setMouseInputListener(MouseInputListener listener);

}