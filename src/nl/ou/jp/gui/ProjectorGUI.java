package nl.ou.jp.gui;

import nl.ou.jp.domain.*;

public interface ProjectorGUI extends SlideShowEventListener, SlideEventListener {
	void start(String path);	
	void exit();
	void showMessageDialog(String message, String title);
	void showErrorMessageDialog(String message, String title);
}