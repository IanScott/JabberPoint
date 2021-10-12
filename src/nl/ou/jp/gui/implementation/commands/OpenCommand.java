package nl.ou.jp.gui.implementation.commands;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import nl.ou.jp.gui.model.*;

public class OpenCommand implements ProjectorCommand, GetMessageMixin {
	private static final String NAME = "OPEN";
	private static final String LOADERRORID = "LOADERROR";
	private static final String IOEXCEPTIONID = "IOEXCEPTION";
	private ProjectorMediator projectorMediator = null;
	private ProjectorConfiguration configuration = null;
	
	public OpenCommand(ProjectorMediator projectorMediator, ProjectorConfiguration configuration) {
		this.projectorMediator = projectorMediator;
		this.configuration = configuration;
	}
	
	@Override
	public void execute(){
		try {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnValue = jfc.showOpenDialog(null);
			        
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				this.projectorMediator.openSlideShow(selectedFile.toPath());
			}
		} catch (RuntimeException exc) {
			String excp = getMessage(configuration,IOEXCEPTIONID);
			String message = getMessage(configuration,LOADERRORID);
			
			projectorMediator.showErrorMessageDialog(excp + exc, message);
		}
	}
	
	@Override
	public String getName() {
		return NAME;
	}
}
