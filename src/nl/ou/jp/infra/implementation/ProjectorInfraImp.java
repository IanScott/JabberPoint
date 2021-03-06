package nl.ou.jp.infra.implementation;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import nl.ou.jp.domain.core.model.SlideShow;
import nl.ou.jp.domain.implementation.SlideShowBuilderImp;
import nl.ou.jp.infra.*;
import nl.ou.jp.infra.model.*;
import nl.ou.jp.logging.*;

public class ProjectorInfraImp implements ProjectorInfra {
	private Logger logger = LoggerManager.getLogger();
	private Accessor[] accessors;
	private Path directory;
	
	public ProjectorInfraImp() {
		super();
	}
	
	public ProjectorInfraImp(Accessor ... accessors) {
		this.accessors = accessors;
	}
	
	@Override
	public void setAccessors(Accessor[] accessors) {
		this.accessors = accessors;
	}
	
	@Override
	public SlideShow openSlideShow(Path path) {
		this.directory = path.getParent();
		Accessor accessor = getAccessor(path); 
		SlideShowBuilderImp builder = new SlideShowBuilderImp();
		accessor.loadFile(builder,path.toString());
		return builder.getSlideShow();
	}
	
	private Accessor getAccessor(Path path) {
		for(Accessor accessor: this.accessors) {
			String[] exts = accessor.getSupportedFileExtensions();
			for(String ext: exts) {
				if(path.toString().toLowerCase().endsWith("."+ext)) {
					return accessor;
				}
			}
		}
		throw new ProjectorInfraException("Unsupported extension: "+path.toString());
	}
	
	@Override
	public void saveSlideShow(SlideShow slideshow, Path path) {
		logger.logDebug("Unimplemented method.");
	}
	
	@Override
	public InputStream fetchFileAsStream(Path path) {
		InputStream inputStream = null;
		try {
			if(directory != null) {
				path = directory.resolve(path);
			}
			inputStream = Files.newInputStream(path);
		}
		catch (IOException e) {
			logger.logError("File not fount");
		}
		return inputStream;
	}
}