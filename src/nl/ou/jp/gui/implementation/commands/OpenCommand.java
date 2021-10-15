package nl.ou.jp.gui.implementation.commands;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class OpenCommand extends ProjectorCommandTemplate implements GetMessageMixin {
	private static final String NAME = "OPEN";
	private static final String LOADERRORID = "LOADERROR";
	private static final String IOEXCEPTIONID = "IOEXCEPTION";
	
	@Override
	public void execute(){
		try {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
			int returnValue = jfc.showOpenDialog(null);
			        
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				this.mediator.openSlideShow(selectedFile.toPath());
			}
		} catch (RuntimeException exc) {
			String excp = getMessage(configuration,IOEXCEPTIONID);
			String message = getMessage(configuration,LOADERRORID);
			
			mediator.showErrorMessageDialog(excp + exc, message);
		}
	}
	
	@Override
	public String getName() {
		return NAME;
	}
}
