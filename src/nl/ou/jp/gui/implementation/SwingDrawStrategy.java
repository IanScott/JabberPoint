package nl.ou.jp.gui.implementation;

import java.awt.*;

import javax.swing.JFrame;

import nl.ou.jp.domain.core.model.*;
import nl.ou.jp.gui.model.DrawStrategy;
import nl.ou.jp.gui.model.SlideItemStyle;
import nl.ou.jp.gui.model.ProjectorContext;
import nl.ou.jp.gui.model.Rectangle;

public abstract class SwingDrawStrategy implements DrawStrategy {
	
	private SwingDrawStrategy strategy;
	private Graphics graphics;
	private Component component;
	private float scale;
	private ProjectorContext projectorContext;
	
	protected SwingDrawStrategy(SwingDrawStrategy next) {
		this.strategy = next;
	}
	
	protected SwingDrawStrategy getStrategy() {
		return this.strategy;
	}
	
	protected Rectangle getNext(SlideShowComponant data, SlideItemStyle myStyle, int x, int y) {
		return (this.strategy == null)?null:next(data, myStyle, x, y);
	}

	protected Rectangle next(SlideShowComponant data, SlideItemStyle mystyle, int x, int y) {
		this.strategy.setContext(this.projectorContext);
		this.strategy.setGraphics(this.graphics);
		this.strategy.setContentPane(this.component);
		this.strategy.setScale(this.scale);
		return this.strategy.draw(data, mystyle, x, y);
	}

	@Override
	public void setContext(ProjectorContext projectorContext) {
		this.projectorContext = projectorContext;
		this.component = ((JFrame)projectorContext.getMainGUI()).getContentPane();
		this.graphics = this.component.getGraphics();
	}
	
	protected ProjectorContext getProjectorContext() {
		return this.projectorContext;
	}
	

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	protected Graphics getGraphics() {
		return this.graphics;
	}

	public void setContentPane(Component component) {
		this.component = component;
	}
	
	protected Component getContentPane() {
		return this.component;
	}
	
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	protected float getScale() {
		return this.scale;
	}
}