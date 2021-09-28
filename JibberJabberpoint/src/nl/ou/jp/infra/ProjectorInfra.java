package nl.ou.jp.infra;

import java.io.InputStream;
import java.nio.file.Path;

import nl.ou.jp.domain.core.model.SlideShow;


public interface ProjectorInfra {
	
	SlideShow openPresentation(Path path);

	void savePresentation(SlideShow slideshow, Path path);

	InputStream fetchFileAsStream(Path path);
}