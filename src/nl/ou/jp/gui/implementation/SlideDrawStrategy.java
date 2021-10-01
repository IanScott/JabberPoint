package nl.ou.jp.gui.implementation;

import java.awt.*;

import javax.swing.JFrame;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.domain.core.model.*;
import nl.ou.jp.gui.model.ProjectorConfiguration;
import nl.ou.jp.gui.model.ProjectorContext;
import nl.ou.jp.gui.model.Dimension;
import nl.ou.jp.gui.model.SlideItemStyle;
import nl.ou.jp.logging.*;
import nl.ou.jp.gui.model.Rectangle;
import nl.ou.jp.gui.model.SlideItemFont;

public class SlideDrawStrategy extends SwingDrawStrategy {
	private static final String SLIDEOFID = "SLIDEOF";
	private Logger logger = LoggerManager.getLogger();

	public SlideDrawStrategy(SwingDrawStrategy strategy) {
		super(strategy);
	}

	@Override
	public Rectangle draw(Graphics graphics, SlideShowComponant data, SlideItemStyle fontstyle, int x, int y) {
		if(!(data instanceof Slide)) {
			return this.getNext(graphics, data, fontstyle, x, y);
		}

		return renderSlide((Slide)data, graphics, getProjectorContext());
	}
	
	private Rectangle renderSlide(Slide slide, Graphics graphics, ProjectorContext projectorContext) {		

		
		Rectangle bounds = renderSlideBounds(graphics, projectorContext);
		renderPosition(slide, graphics, projectorContext);
		renderArea(graphics, slide, projectorContext);
		return bounds;
	}
		
	private Rectangle renderSlideBounds(Graphics g, ProjectorContext projectorContext) {
		Component component = ((JFrame)this.getProjectorContext().getMainGUI()).getContentPane();
		ProjectorConfiguration configuration = projectorContext.getConfiguration();
		
		Color backgroundColor = new Color(configuration.getSlideBackgroundRGBColor());	
		g.setColor(backgroundColor);
		g.fillRect(0, 0, component.getSize().width, component.getSize().height);
		return new RectangleImp(0, 0, component.getSize().width, component.getSize().height);
	}
		
	private void renderPosition(Slide slide, Graphics g, ProjectorContext projectorContext) {
		ProjectorController projectorController = projectorContext.getProjector();
		ProjectorConfiguration configuration = projectorContext.getConfiguration();
		Dimension innerArea = configuration.getDefaultInnerSlideDimensions();
		
		SlideItemFont sfont = configuration.getDefaultLabelFont();
		Font font = new Font(sfont.getName(), sfont.getStyle(), sfont.getSize());
		g.setFont(font);
		
		Color fontColor = new Color(configuration.getDefaultFontRGBColor());
		g.setColor(fontColor);
			
		String template = projectorContext.getConfiguration().getMessage(SLIDEOFID);
		String slideOf = String.format(template,(1 + slide.getSequenceNumber()),projectorController.getSlideShowSize());
			
		g.drawString(slideOf, innerArea.getWidth(), innerArea.getHeight());
	}		
		
	private void renderArea(Graphics g, Slide slide, ProjectorContext projectorContext) {
		ProjectorConfiguration config = projectorContext.getConfiguration();
		Component component = ((JFrame)this.getProjectorContext().getMainGUI()).getContentPane();
		
		Dimension innerArea = config.getDefaultInnerSlideDimensions();
			
		Rectangle area = new RectangleImp(0, innerArea.getHeight(), component.getWidth(), (component.getHeight() - innerArea.getHeight()));
		float scale = getScale(area, config);
		    
		int x = area.getX();
		int y = area.getY();
		
		if(getStrategy() != null) {
			getStrategy().setContext(getProjectorContext());
			getStrategy().setScale(scale);
			
			SlideShowItem item = getTitleItem(slide.getTitle());
			
			SlideShowComponantIterator iterator = null;
			do{
				if(iterator == null) {
					iterator = slide.getIterator();
				}else {
					iterator.gotoNext();
					item = (SlideShowItem) iterator.getCurrentItem();
				}
				y = renderTextItem(g, projectorContext, x, y, item);
			}while(iterator.hasNext()); 
			
		}
		
	}

	private int renderTextItem(Graphics g ,ProjectorContext projectorContext, int x, int y, SlideShowItem item) {
		try {
			SlideItemStyle style = projectorContext.getConfiguration().getStyle(item.getLevel().getRating());
			y += getStrategy().draw(g, item, style, x, y).getHeight();			
		}catch(Exception e) {
			logger.logError(e.getMessage());
		}
		return y;
	}
		
	private TextItem getTitleItem(String title) {
		return new TextItem() {
				@Override
				public SlideShowComponant copy() {
					return this;
				}
				@Override
				public Level getLevel() {
					return () -> 0;
				}
				@Override
				public String getText() {
					return title;
				}
				@Override
				public void add(SlideShowComponant componant) {
					//ignore
				}
				@Override
				public SlideShowComponantIterator getIterator() {
					return null;
				}
				@Override
				public int getSequenceNumber() {
					return 0;
				}
				@Override
				public void setSequenceNumber(int seqnr) {
				}
		};
	}
		
	// geef de schaal om de slide te kunnen tekenen
	private float getScale(Rectangle area, ProjectorConfiguration config) {
			var dimension = config.getDefaultSlideDimensions();
			return Math.min(((float)area.getWidth()) / (dimension.getWidth()), ((float)area.getHeight()) / (dimension.getHeight()));
	}
	
}
