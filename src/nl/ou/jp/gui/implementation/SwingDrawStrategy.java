package nl.ou.jp.gui.implementation;

import java.awt.*;

import nl.ou.jp.domain.core.model.*;
import nl.ou.jp.gui.model.SlideItemStyle;
import nl.ou.jp.gui.model.ProjectorContext;
import nl.ou.jp.gui.model.Rectangle;

public abstract class SwingDrawStrategy {
	
	private SwingDrawStrategy strategy;
	private float scale;
	private ProjectorContext projectorContext;
	
	protected SwingDrawStrategy(SwingDrawStrategy next) {
		this.strategy = next;
	}
	
	public Rectangle draw(Object graphics, SlideShowComponant data, SlideItemStyle fontstyle, int x, int y) {
		if(graphics instanceof Graphics) {
			return draw((Graphics) graphics, data, fontstyle, x, y);
		}
		return new RectangleImp(0,0,0,0);
	}
	
	public abstract Rectangle draw(Graphics graphics, SlideShowComponant data, SlideItemStyle fontstyle, int x, int y);
	
	protected SwingDrawStrategy getStrategy() {
		return this.strategy;
	}
	
	protected Rectangle getNext(Graphics graphics, SlideShowComponant data, SlideItemStyle myStyle, int x, int y) {
		return (this.strategy == null)?null:next(graphics, data, myStyle, x, y);
	}

	protected Rectangle next(Graphics graphics, SlideShowComponant data, SlideItemStyle mystyle, int x, int y) {
		this.strategy.setContext(this.projectorContext);
		this.strategy.setScale(this.scale);
		return this.strategy.draw(graphics, data, mystyle, x, y);
	}

	public void setContext(ProjectorContext projectorContext) {
		this.projectorContext = projectorContext;
	}
	
	protected ProjectorContext getProjectorContext() {
		return this.projectorContext;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	protected float getScale() {
		return this.scale;
	}
}