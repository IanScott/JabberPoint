package nl.ou.jp.gui.implementation.drawstrategies;

import java.awt.*;

import nl.ou.jp.domain.core.model.*;
import nl.ou.jp.gui.model.*;

public abstract class SwingDrawStrategy implements DrawStrategy {
	
	private DrawStrategy strategy;
	private float scale;
	private ProjectorContext projectorContext;
	
	protected SwingDrawStrategy(DrawStrategy next) {
		this.strategy = next;
	}
	
	protected DrawStrategy getStrategy() {
		return this.strategy;
	}
	
	protected Rectangle getNext(Graphics graphics, Component component,SlideShowComponant data, SlideItemStyle myStyle, int x, int y) {
		return (this.strategy == null)?null:next(graphics, component, data, myStyle, x, y);
	}

	protected Rectangle next(Graphics graphics, Component component, SlideShowComponant data, SlideItemStyle mystyle, int x, int y) {
		this.strategy.setContext(this.projectorContext);
		this.strategy.setScale(this.scale);
		return this.strategy.draw(graphics, component, data, mystyle, x, y);
	}
	
	@Override
	public void setContext(ProjectorContext projectorContext) {
		this.projectorContext = projectorContext;
	}
	
	protected ProjectorContext getProjectorContext() {
		return this.projectorContext;
	}

	@Override
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	protected float getScale() {
		return this.scale;
	}
}