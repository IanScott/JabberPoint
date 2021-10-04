package nl.ou.jp.domain.core.implementation;

import java.text.DecimalFormat;

public class RelativePosition {
	
	private double x;
	private double y;
	private DecimalFormat decimalFormat;
	
	public RelativePosition(double x, double y, DecimalFormat decimalFormat) 
	{
		this.x = x;
		this.y = y;
		this.decimalFormat = decimalFormat;
	}
	
	public double getRelativeX() 
	{
		return Double.parseDouble(decimalFormat.format(x));
	}
	
	public double getRelativeY() 
	{
		return Double.parseDouble(decimalFormat.format(y));
	}
}