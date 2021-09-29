package nl.ou.jp.gui.implementation;

import java.awt.Font;

import nl.ou.jp.gui.model.SlideItemColor;
import nl.ou.jp.gui.model.SlideItemFont;
import nl.ou.jp.gui.model.SlideItemStyle;

public class SlideItemStyleImp implements SlideItemStyle {
	private int indent;
	private SlideItemColor color;
	private String fontname;
	private int fontSize;
	private int leading;

	public SlideItemStyleImp(String fontname, int indent, SlideItemColor color, int points, int leading) {
		this.indent = indent;
		this.color = color;
		this.fontSize = points;
		this.fontname = fontname;
		this.leading = leading;
	}

	@Override
	public int getIndent() {
		return this.indent;
	}
	
	@Override
	public SlideItemColor getColor() {
		return this.color;
	}
	
	@Override
	public int getLeading() {
		return this.leading;
	}
	
	@Override
	public SlideItemFont getFont(float scale) {
		return new SlideItemFontImp(this.fontname, Font.BOLD, (int) (this.fontSize * scale));

	}
	
	public String toString() {
		return "["+ indent + "," + color + "; " + fontSize + " on " + leading +"]";
	}
}