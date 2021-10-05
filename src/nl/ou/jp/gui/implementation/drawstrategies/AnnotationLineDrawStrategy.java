package nl.ou.jp.gui.implementation.drawstrategies;

import java.awt.*;
import java.util.List;
import java.util.*;

import nl.ou.jp.domain.core.implementation.SlideShowCompositeTemplate;
import nl.ou.jp.domain.core.model.SlideShowComponant;
import nl.ou.jp.gui.model.*;

public class AnnotationLineDrawStrategy extends SwingDrawStrategy {

	
	public AnnotationLineDrawStrategy(DrawStrategy next) {
		super(next);
	}

	@Override
	public Rectangle draw(Graphics graphics, Component component, SlideShowComponant data, SlideItemStyle fontstyle,
			int x, int y) {
		System.out.println("AnnotationLineDrawStrategy");
		if(!(data instanceof AnnotationItem)) {
			return this.getNext(graphics, component, data, fontstyle, x, y);
		}
		
		System.out.println("Drawing line");
		AnnotationItem item = ((AnnotationItem)data);
		
		System.out.println(item.getX());
		System.out.println(item.getY());
		
		int[] xpoints = covertToAbsolute(item.getX(), component.getWidth());
		int[] ypoints = covertToAbsolute(item.getY(), component.getHeight());
		
		graphics.setColor(new Color(item.getColor()));
		((Graphics2D)graphics).setStroke(new BasicStroke(item.getLine()));
		graphics.drawPolyline(xpoints, ypoints, xpoints.length);
		
		return new Rectangle(x,y,0,0);
	}
	
	private int[] covertToAbsolute(List<Double> relative, int scale) {
		int[] absolute = new int[relative.size()];
		for(int i = 0; i< relative.size(); i++) {
			absolute[i]= (int)(relative.get(i)*scale);
		}
		return absolute;
	}
	
	///Test Methods
	public static SlideShowComponant getTestItem() {
		List<Double> x = new ArrayList<>();
		List<Double> y = new ArrayList<>();
		
		x.add(0.1);x.add(0.2);x.add(0.3);x.add(0.4);x.add(0.5);x.add(0.6);
		y.add(0.1);y.add(0.2);y.add(0.3);y.add(0.4);y.add(0.5);y.add(0.6);
		
		AnnotationItem item = new AnnotationItem(5,5,x,y);
		return item;
	}
	
	public static class AnnotationItem extends SlideShowCompositeTemplate {
		private int line; 
		private int color; 
		private List<Double> x; 
		private List<Double> y;
		
		public AnnotationItem(int line, int color, List<Double> x, List<Double> y) {
			super("", new ArrayList<>());	
			this.line = line;
			this.color = color;
			this.x = x;
			this.y = y;
		}
		
		public AnnotationItem(String title, List<SlideShowComponant> componants) {
			super(title, componants);	
		}
		
		public int getLine() {
			return line;
		}
		
		public int getColor() {
			return color;
		}
		
		public int getSize() {
			return Math.min(x.size(),y.size());
		}
		
		public List<Double> getX() {
			return x;
		}
		
		public List<Double> getY() {
			return y;
		}
		
		@Override
		protected SlideShowComponant createComponant(String title, List<SlideShowComponant> componants) {
			return (SlideShowComponant) new AnnotationLineDrawStrategy.AnnotationItem(title, componants);
		}

		@Override
		public int getSequenceNumber() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setSequenceNumber(int seqnr) {
			// TODO Auto-generated method stub
			
		}
		
		public SlideShowComponant copy() {
			return this;
		}
		
	}
}
