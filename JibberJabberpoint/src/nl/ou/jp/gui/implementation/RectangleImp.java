package nl.ou.jp.gui.implementation;

import nl.ou.jp.gui.model.Rectangle;

public class RectangleImp implements Rectangle {

	private int height;
	private int width;
	private int x;
	private int y;
	
	public RectangleImp(int x, int y, int width, int height) {
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

}
