package nl.ou.jp.gui;

import nl.ou.jp.util.EventListener;

public interface ProjectorGUI extends EventListener {
	void start(String path);	
	void exit();
	void showMessageDialog(String message, String title);
	void showErrorMessage(String message, String title);
}