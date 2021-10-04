package nl.ou.jp.gui.implementation.commands;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import nl.ou.jp.gui.model.ProjectorCommand;
import nl.ou.jp.gui.model.ProjectorConfiguration;
import nl.ou.jp.gui.model.ProjectorContext;

public class OpenCommand implements ProjectorCommand {
	private static final String NAME = "OPEN";
	private static final String LOADERRORID = "LOADERROR";
	private static final String IOEXCEPTIONID = "IOEXCEPTION";
	private ProjectorContext projectorContext = null;
	
	public OpenCommand(ProjectorContext projectorContext) {
		this.projectorContext = projectorContext;
	}
	
	@Override
	public void execute(){
		try {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnValue = jfc.showOpenDialog(null);
			        
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				this.projectorContext.getController().openSlideShow(selectedFile.toPath());
			}
		} catch (RuntimeException exc) {
			ProjectorConfiguration config = this.projectorContext.getConfiguration();
			String excp = config.getMessage(IOEXCEPTIONID);
			String message = config.getMessage(LOADERRORID);
			
			projectorContext.getMainGUI().showErrorMessageDialog(excp + exc, message);
		}
	}
	
	@Override
	public String getName() {
		return NAME;
	}
}
