package nl.ou.jp.infra.model;

import nl.ou.jp.domain.*;
import nl.ou.jp.domain.core.model.SlideShow;

/**

 */

public interface Accessor {

	SlideShowBuilder loadFile(SlideShowBuilder builder, String fn);

	void saveFile(SlideShow slideshow, String fn);
	
	String[] getSupportedFileExtensions();
}