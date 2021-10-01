package nl.ou.jp.gui.implementation;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;
import java.util.*;
import java.util.List;

import nl.ou.jp.domain.core.model.*;
import nl.ou.jp.gui.ProjectorGUIException;
import nl.ou.jp.gui.model.*;
import nl.ou.jp.logging.*;

/** <p>Een tekst item.</p>
 */

public class TextDrawStrategy extends SwingDrawStrategy {
	private Logger logger = LoggerManager.getLogger();
	
	public TextDrawStrategy(DrawStrategy next) {
		super(next);
	}
	
	private Rectangle getBoundingBox(Graphics g, String text, SlideItemStyle myStyle, float scale) {
		
		List<TextLayout> layouts = getLayouts(g, myStyle, scale, text);
		int xsize = 0; 
		int ysize = (int) (myStyle.getLeading() * scale);
		Iterator<TextLayout> iterator = layouts.iterator();
		while (iterator.hasNext()) {
			TextLayout layout = iterator.next();
			Rectangle2D bounds = layout.getBounds();
			if (bounds.getWidth() > xsize) {
				xsize = (int) bounds.getWidth();
			}
			if (bounds.getHeight() > 0) {
				ysize += bounds.getHeight();
			}
			ysize += layout.getLeading() + layout.getDescent();
		}
		return new Rectangle((int) (myStyle.getIndent()*scale), 0, xsize, ysize );
	}

	@Override
	public Rectangle draw(Graphics graphics, Component component, SlideShowComponant data, SlideItemStyle myStyle, int x, int y) {
		if(!(data instanceof TextItem)) {
			return this.getNext(graphics, component, data, myStyle, x, y);
		}

		TextItem textitem = (TextItem)data;
		
		String text = (textitem.getText() == null)?"":textitem.getText();
	
		List<TextLayout> layouts = getLayouts(graphics, myStyle, getScale(), text);
		
		Point pen = new Point(x + (int)(myStyle.getIndent() * getScale()), 
				y + (int) (myStyle.getLeading() * getScale()));
		Graphics2D g2d = (Graphics2D)graphics;
		
		g2d.setColor(myStyle.getColor());
		Iterator<TextLayout> it = layouts.iterator();
		while (it.hasNext()) {
			TextLayout layout = it.next();
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}
		
		return getBoundingBox(graphics, text, myStyle, getScale());
	  }

	
	private List<TextLayout> getLayouts(Graphics g, SlideItemStyle s, float scale, String text) {
		List<TextLayout> layouts = new ArrayList<>();
		AttributedString attrStr = getAttributedString(s, scale, text);
    	Graphics2D g2d = (Graphics2D) g;
    	FontRenderContext frc = g2d.getFontRenderContext();
    	LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr.getIterator(), frc);
    	var width = getDefaultSlideWidth();
 
    	float wrappingWidth = (width - s.getIndent()) * scale;
    	while (measurer.getPosition() < text.length()) {
    		TextLayout layout = measurer.nextLayout(wrappingWidth);
    		layouts.add(layout);
    	}
    	return layouts;
	}

	private int getDefaultSlideWidth() {
		try {
			return (int)getProjectorContext().getConfiguration().getDefaultSlideDimensions().getWidth();			
		}catch(Exception e) {
			logger.logError("Failed to fetch default slide dimensions.");
			throw new ProjectorGUIException(e.getMessage());
		}
	}
	
	private AttributedString getAttributedString(SlideItemStyle slideItemStyle, float scale, String text) {
		AttributedString attrStr = new AttributedString(text);
		attrStr.addAttribute(TextAttribute.FONT, slideItemStyle.getFont(scale), 0, text.length());			
		return attrStr;
	}
}