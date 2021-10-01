package nl.ou.jp.gui.implementation;

import java.awt.Graphics;
import java.awt.image.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import nl.ou.jp.gui.model.SlideItemStyle;
import nl.ou.jp.domain.core.model.*;
import nl.ou.jp.gui.model.Rectangle;

/** <p>De klasse voor een Bitmap item</p>
*/

public class FigureDrawStrategy extends SwingDrawStrategy {
	
	public FigureDrawStrategy(SwingDrawStrategy next) {
		super(next);
	}

	@Override
	public Rectangle draw(Graphics graphics, SlideShowComponant data, SlideItemStyle slideItemStyle, int x, int y) {
		if(!(data instanceof FigureItem)) {
			return getNext(graphics,data, slideItemStyle, x, y);
		}
		
		FigureItem figure = (FigureItem)data;
		
		
		InputStream stream = getProjectorContext().getProjector().fetchFileAsStream(Path.of(figure.getSource()));
		
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ImageObserver observer =  ((JFrame)this.getProjectorContext().getMainGUI()).getContentPane();
		
		drawImp(graphics,bufferedImage, slideItemStyle, observer, x, y);
		return getBoundingBox(bufferedImage, slideItemStyle, observer);
	}
	
	private void drawImp(Graphics graphics, BufferedImage bufferedImage, SlideItemStyle slideItemStyle, ImageObserver observer, int x, int y) {
		int width = x + (int) (slideItemStyle.getIndent() * getScale());
		int height = y + (int) (slideItemStyle.getLeading() * getScale());
		graphics.drawImage(bufferedImage, width, height,(int) (bufferedImage.getWidth(observer)*getScale()),
                (int) (bufferedImage.getHeight(observer)*getScale()), observer);
	}
	
	private Rectangle getBoundingBox(BufferedImage bufferedImage, SlideItemStyle slideItemStyle, ImageObserver observer) {
		return new RectangleImp((int) (slideItemStyle.getIndent() * getScale()), 0,
				(int) (bufferedImage.getWidth(observer) * getScale()),
				((int) (slideItemStyle.getLeading() * getScale())) + 
				(int) (bufferedImage.getHeight(observer) * getScale()));
	}
}
