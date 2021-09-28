package nl.ou.jp.gui.model;

import nl.ou.jp.domain.core.model.*;

public interface DrawStrategy {
	
	Rectangle draw(SlideShowComponant data, SlideItemStyle fontstyle, int x, int y) ;
}
