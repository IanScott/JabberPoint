package nl.ou.jp.gui.implementation.drawstrategies;

import java.awt.*;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.domain.SlideShowComponantFactory;
import nl.ou.jp.domain.core.implementation.SimpleLevel;
import nl.ou.jp.domain.core.model.*;
import nl.ou.jp.gui.model.*;
import nl.ou.jp.logging.*;

public class SlideDrawStrategy extends SwingDrawStrategy {
	private static final String TEXTITEM_TYPE = "TEXT";
	private static final String SLIDEOFID = "SLIDEOF";
	private Logger logger = LoggerManager.getLogger();

	public SlideDrawStrategy(DrawStrategy strategy) {
		super(strategy);
	}

	@Override
	public Rectangle draw(Graphics graphics, Component component, SlideShowComponant data, SlideItemStyle fontstyle, int x, int y) {
		if(!(data instanceof Slide)) {
			return this.getNext(graphics, component, data, fontstyle, x, y);
		}
		return renderSlide(graphics, component, (Slide)data);
	}
	
	private Rectangle renderSlide(Graphics graphics, Component component, Slide slide) {		
		Rectangle bounds = renderSlideBounds(graphics, component);
		renderPosition(graphics, slide);
		renderArea(graphics, component, slide);
		return bounds;
	}
		
	private Rectangle renderSlideBounds(Graphics g, Component component) {
		ProjectorConfiguration configuration = getProjectorContext().getConfiguration();
		
		Color backgroundColor = configuration.getSlideBackgroundColor();	
		g.setColor(backgroundColor);
		g.fillRect(0, 0, component.getSize().width, component.getSize().height);
		return new Rectangle(0, 0, component.getSize().width, component.getSize().height);
	}
		
	private void renderPosition(Graphics g, Slide slide) {
		ProjectorController projectorController = getProjectorContext().getController();
		ProjectorConfiguration configuration = getProjectorContext().getConfiguration();
		Dimension innerArea = configuration.getDefaultInnerSlideDimensions();
		
		Font font = configuration.getDefaultLabelFont();
		g.setFont(font);
		
		Color fontColor = configuration.getDefaultFontColor();
		g.setColor(fontColor);
			
		String template = getProjectorContext().getConfiguration().getMessage(SLIDEOFID);
		String slideOf = String.format(template,(1 + slide.getSequenceNumber()),projectorController.getSlideShowSize());
		
		g.drawString(slideOf, (int)innerArea.getHeight(), (int)innerArea.getWidth());
	}		
		
	private void renderArea(Graphics g, Component component, Slide slide) {
		System.out.println("Slide size: "+slide.size());
		
		ProjectorConfiguration config = getProjectorContext().getConfiguration();

		Dimension innerArea = config.getDefaultInnerSlideDimensions();
		
		Rectangle area = new Rectangle(0, innerArea.width, component.getWidth(), (component.getHeight() - innerArea.width));
		float scale = getScale(area, config);
		    
		int x = area.x;
		int y = area.y;
		
		if(getStrategy() != null) {
			getStrategy().setContext(getProjectorContext());
			getStrategy().setScale(scale);
			
			SlideShowComponant item = getTitleItem(slide.getTitle());
			
			SlideShowComponantIterator iterator = null;
			do{
				if(iterator == null) {
					iterator = slide.getIterator();
				}else {
					iterator.gotoNext();
					item =  iterator.getCurrentItem();
				}
				y = renderSlideShowItem(g, component, item, x, y);
			}while(iterator.hasNext()); 
			
		}		
	}

	private int renderSlideShowItem(Graphics g , Component component, SlideShowComponant item, int x, int y) {
		try {
			SlideItemStyle style = null;
			if(item instanceof SlideShowItem) {
				style = getProjectorContext().getConfiguration().getStyle(((SlideShowItem)item).getLevel().getRating());				
			}
			y += getStrategy().draw(g, component, item, style, x, y).getHeight();			
		}catch(Exception e) {
			logger.logError("Error Rendering Item "+ x+" "+y+ " item: "+item.getSequenceNumber());
			logger.logError(e.getMessage());
		}
		return y;
	}
		
	private SlideShowItem getTitleItem(String title) {
		var item = SlideShowComponantFactory.getInstance().createSlideShowComponant(TEXTITEM_TYPE);
		((TextItem)item).setLevel(new SimpleLevel(0));
		((TextItem)item).setText(title);
		return ((TextItem)item);
	}
		
	// geef de schaal om de slide te kunnen tekenen
	private float getScale(Rectangle area, ProjectorConfiguration config) {
			var dimension = config.getDefaultSlideDimensions();
			return Math.min(((float)area.width) / (dimension.width), ((float)area.height / (dimension.height)));
	}
	
}
