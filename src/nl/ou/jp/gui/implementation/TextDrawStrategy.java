package nl.ou.jp.gui.implementation;

import java.awt.*;
import java.awt.font.*;
import java.awt.geom.Rectangle2D;
import java.text.AttributedString;
import java.util.*;
import java.util.List;

import nl.ou.jp.domain.core.model.SlideShowComponant;
import nl.ou.jp.domain.core.model.TextItem;
import nl.ou.jp.gui.ProjectorGUIException;
import nl.ou.jp.gui.model.SlideItemStyle;
import nl.ou.jp.gui.model.Rectangle;
import nl.ou.jp.gui.model.SlideItemFont;
import nl.ou.jp.logging.*;


/** <p>Een tekst item.</p>
 */

public class TextDrawStrategy extends SwingDrawStrategy {
	private Logger logger = LoggerManager.getLogger();
	
	public TextDrawStrategy(SwingDrawStrategy next) {
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
		return new RectangleImp((int) (myStyle.getIndent()*scale), 0, xsize, ysize );
	}

	@Override
	public Rectangle draw(SlideShowComponant data, SlideItemStyle myStyle, int x, int y) {
		if(!(data instanceof TextItem)) {
			return this.getNext(data, myStyle, x, y);
		}
		
		TextItem textitem = (TextItem)data;
		
		String text = (textitem.getText() == null)?"":textitem.getText();
	
		List<TextLayout> layouts = getLayouts(getGraphics(), myStyle, getScale(), text);
		
		Point pen = new Point(x + (int)(myStyle.getIndent() * getScale()), 
				y + (int) (myStyle.getLeading() * getScale()));
		Graphics2D g2d = (Graphics2D)getGraphics();
		
		Color color = new Color(myStyle.getColor().getRGBValue());
		g2d.setColor(color);
		Iterator<TextLayout> it = layouts.iterator();
		while (it.hasNext()) {
			TextLayout layout = it.next();
			pen.y += layout.getAscent();
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}
		
		return getBoundingBox(getGraphics(), text, myStyle, getScale());
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
			return getProjectorContext().getConfiguration().getDefaultSlideDimensions().getWidth();			
		}catch(Exception e) {
			logger.logError("Failed to fetch default slide dimensions.");
			throw new ProjectorGUIException(e.getMessage());
		}
	}
	
	private AttributedString getAttributedString(SlideItemStyle slideItemStyle, float scale, String text) {
		AttributedString attrStr = new AttributedString(text);
		SlideItemFont s = slideItemStyle.getFont(scale);
		Font font = new Font(s.getName(),s.getStyle(),s.getSize());
		attrStr.addAttribute(TextAttribute.FONT, font, 0, text.length());			
		return attrStr;
	}

}