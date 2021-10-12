package nl.ou.jp.gui.model;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;

import nl.ou.jp.domain.core.model.SlideShowComponant;


public interface DrawStrategy {

	Rectangle draw(Graphics graphics, Component component, SlideShowComponant data, SlideItemStyle fontstyle, int x, int y);

	void setContext(ProjectorContext projectorContext);

	void setScale(float scale);

}