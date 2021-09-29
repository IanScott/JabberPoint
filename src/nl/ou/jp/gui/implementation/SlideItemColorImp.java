package nl.ou.jp.gui.implementation;

import nl.ou.jp.gui.model.SlideItemColor;

public class SlideItemColorImp implements SlideItemColor {
	
	private int rGBValue = 0;
	
	public SlideItemColorImp(int rGBValue) {
		this.rGBValue = rGBValue;
	}
	
	@Override
	public int getRGBValue() {
		return this.rGBValue;
	}

	@Override
	public void setRGBValue(int value) {
		this.rGBValue = value;
	}
	
}
