package nl.ou.jp.gui.implementation;

import nl.ou.jp.gui.model.*;

public class DimensionImp implements Dimension {
	
	private int height;
	private int width;
	
	public DimensionImp(int height, int width) {
		this.height = height;
		this.width = width;
	}
	
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

}
