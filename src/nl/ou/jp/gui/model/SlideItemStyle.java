package nl.ou.jp.gui.model;

import java.awt.Color;
import java.awt.Font;

public interface SlideItemStyle {

	int getIndent();

	Color getColor();

	int getLeading();

	Font getFont(float scale);

}