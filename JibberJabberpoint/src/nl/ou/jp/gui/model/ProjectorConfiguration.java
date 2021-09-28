package nl.ou.jp.gui.model;

//Kind of Factory
public interface ProjectorConfiguration {

	String getDefaultTitle();

	SlideItemStyle getStyle(int level);

	Dimension getDefaultSlideDimensions();

	Dimension getDefaultInnerSlideDimensions();

	int getSlideBackgroundRGBColor();

	int getDefaultFontRGBColor();

	SlideItemFont getDefaultLabelFont();

	String getMenuName(String menuId);

	String getMessage(String messageId);

}