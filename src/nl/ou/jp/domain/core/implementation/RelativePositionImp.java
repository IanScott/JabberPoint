package nl.ou.jp.domain.core.implementation;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import nl.ou.jp.domain.core.model.RelativePosition;

public class RelativePositionImp implements RelativePosition {
	
	private double x;
	private double y;
	private final int precision = 1;
	
	public RelativePositionImp(double x, double y) 
	{
		this.x = x;
		this.y = y;
	}
	
	public double getRelativeX() 
	{
		return round(x, precision);
	}
	
	public double getRelativeY() 
	{
		return round(y, precision);
	}
	
	private double round(double value, int precision) 
	{
		int scale = (int)Math.pow(10, precision);
		return (double)Math.round(value * scale) / scale;
	}
}