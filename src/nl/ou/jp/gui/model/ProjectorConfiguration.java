package nl.ou.jp.gui.model;

import java.awt.Dimension;
import java.awt.Font;

//Kind of Factory
public interface ProjectorConfiguration {

	String getDefaultTitle();

	SlideItemStyle getStyle(int level);

	Dimension getDefaultSlideDimensions();

	Dimension getDefaultInnerSlideDimensions();

	int getSlideBackgroundRGBColor();

	int getDefaultFontRGBColor();

	Font getDefaultLabelFont();

	String getMenuName(String menuId);

	String getMessage(String messageId);

}