package nl.ou.jp.gui.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

public interface ProjectorConfiguration {

	String getDefaultTitle();

	SlideItemStyle getStyle(int level);

	Dimension getDefaultSlideDimensions();

	Dimension getDefaultInnerSlideDimensions();

	Color getSlideBackgroundColor();

	Color getDefaultFontColor();

	Font getDefaultLabelFont();

	String getMenuName(String menuId);

	String getMessage(String messageId);
	
	Map<String,Color> getColors();
	
	String getColorName(int rgb);

}