package nl.ou.jp.gui.implementation.drawstrategies;

import java.awt.*;
import java.util.List;
import java.util.*;

import nl.ou.jp.domain.core.implementation.SlideShowCompositeTemplate;
import nl.ou.jp.domain.core.model.AnnotationLine;
import nl.ou.jp.domain.core.model.AnnotationPoint;
import nl.ou.jp.domain.core.model.RelativePosition;
import nl.ou.jp.domain.core.model.SlideShowComponant;
import nl.ou.jp.domain.core.model.SlideShowComponantIterator;
import nl.ou.jp.gui.model.*;

public class AnnotationLineDrawStrategy extends SwingDrawStrategy {

	
	public AnnotationLineDrawStrategy(DrawStrategy next) {
		super(next);
	}

	@Override
	public Rectangle draw(Graphics graphics, Component component, SlideShowComponant data, SlideItemStyle fontstyle,
			int x, int y) {
		System.out.println("AnnotationLineDrawStrategy");
		if(!(data instanceof AnnotationLine)) {
			return this.getNext(graphics, component, data, fontstyle, x, y);
		}
		
		System.out.println("Drawing line");
		AnnotationLine item = ((AnnotationLine)data);
		SlideShowComponantIterator it = item.getIterator();
		
		List<Integer> xpointlist = new ArrayList<>();
		List<Integer> ypointlist = new ArrayList<>();
		
		while(it.hasNext()) {
			it.gotoNext();
			AnnotationPoint point = (AnnotationPoint)it.getCurrentItem();
			RelativePosition pos = point.getRelativePosition();
			xpointlist.add(covertToAbsolute(pos.getRelativeX(), component.getWidth()));
			ypointlist.add(covertToAbsolute(pos.getRelativeY(), component.getHeight()));
		}
		
		int[] xpoints = new int[xpointlist.size()];
		for(int i=0; i<xpointlist.size(); i++){
			xpoints[i] = xpointlist.get(i);
		}
		
		int[] ypoints = new int[ypointlist.size()];
		for(int i=0; i<ypointlist.size(); i++){
			ypoints[i] = ypointlist.get(i);
		}
		
		graphics.setColor(new Color(item.getLineColor()));
		((Graphics2D)graphics).setStroke(new BasicStroke(item.getLineWeight()));
		graphics.drawPolyline(xpoints, ypoints, xpoints.length);
		
		return new Rectangle(x,y,0,0);
	}
	
	private int covertToAbsolute(Double relative, int scale) {
			return (int)(relative*scale);
	}
}
