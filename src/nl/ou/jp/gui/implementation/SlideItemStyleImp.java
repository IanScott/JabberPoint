package nl.ou.jp.gui.implementation;

import java.awt.Color;
import java.awt.Font;

import nl.ou.jp.gui.model.SlideItemStyle;

public class SlideItemStyleImp implements SlideItemStyle {
	private int indent;
	private Color color;
	private String fontname;
	private int fontSize;
	private int leading;

	public SlideItemStyleImp(String fontname, int indent, Color color, int points, int leading) {
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
	public Color getColor() {
		return this.color;
	}
	
	@Override
	public int getLeading() {
		return this.leading;
	}
	
	@Override
	public Font getFont(float scale) {
		return new Font(this.fontname, Font.BOLD, (int) (this.fontSize * scale));

	}
	
	public String toString() {
		return "["+ indent + "," + color + "; " + fontSize + " on " + leading +"]";
	}
}