package nl.ou.jp.gui.implementation.drawstrategies;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import nl.ou.jp.gui.model.DrawStrategy;
import nl.ou.jp.gui.model.SlideItemStyle;
import nl.ou.jp.domain.core.model.*;

/** <p>De klasse voor een Bitmap item</p>
*/

public class FigureDrawStrategy extends SwingDrawStrategy {
	
	public FigureDrawStrategy(DrawStrategy next) {
		super(next);
	}

	@Override
	public Rectangle draw(Graphics graphics, Component component, SlideShowComponant data, SlideItemStyle slideItemStyle, int x, int y) {
		if(!(data instanceof FigureItem)) {
			return getNext(graphics, component, data, slideItemStyle, x, y);
		}
		System.out.println("drawing Figure");
		
		FigureItem figure = (FigureItem)data;
		InputStream stream = getProjectorContext().getController().fetchFileAsStream(Path.of(figure.getSource()));
		
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(stream);
			System.out.println(bufferedImage);
		} catch (IOException e) {
			e.printStackTrace();
		}

		drawImp(graphics, bufferedImage, slideItemStyle, component, x, y);
		return getBoundingBox(bufferedImage, slideItemStyle, component);
	}
	
	private void drawImp(Graphics graphics, BufferedImage bufferedImage, SlideItemStyle slideItemStyle, ImageObserver observer, int x, int y) {
		int width = x + (int) (slideItemStyle.getIndent() * getScale());
		int height = y + (int) (slideItemStyle.getLeading() * getScale());
		graphics.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(observer)*getScale()),
                (int) (bufferedImage.getHeight(observer)*getScale()), observer);
	}
	
	private Rectangle getBoundingBox(BufferedImage bufferedImage, SlideItemStyle slideItemStyle, ImageObserver observer) {
		return new Rectangle((int) (slideItemStyle.getIndent() * getScale()), 0,
				(int) (bufferedImage.getWidth(observer) * getScale()),
				((int) (slideItemStyle.getLeading() * getScale())) + 
				(int) (bufferedImage.getHeight(observer) * getScale()));
	}
}
