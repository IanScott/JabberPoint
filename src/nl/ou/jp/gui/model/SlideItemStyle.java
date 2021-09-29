package nl.ou.jp.gui.model;

public interface SlideItemStyle {

	int getIndent();

	SlideItemColor getColor();

	int getLeading();

	SlideItemFont getFont(float scale);

}