package nl.ou.jp.gui.implementation;

import nl.ou.jp.gui.model.SlideItemFont;

public class SlideItemFontImp implements SlideItemFont{

	private String name;
	private int style;
	private int size;
	
	public SlideItemFontImp(String name, int style, int size) {
		this.name = name;
		this.style = style;
		this.size = size;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getStyle() {
		return style;
	}

	@Override
	public int getSize() {
		return size;
	}
}
