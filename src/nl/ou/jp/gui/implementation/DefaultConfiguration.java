package nl.ou.jp.gui.implementation;

import java.awt.*;
import java.util.*;

import nl.ou.jp.gui.model.*;

import nl.ou.jp.logging.*;

public class DefaultConfiguration implements ProjectorConfiguration {
	private Logger logger = LoggerManager.getLogger();
	
	private static final String APPLICATIONTITLEID = "APPLICATIONTITLE";
	private static final String UNKOWN_MESSAGEID_MESSAGE = "UNKOWN MESSAGE";
	private static final String UNKOWN_MENUID_MESSAGE = "UNKOWN";

	private static final String RESOURCE_MESSAGES = "nl.ou.jp.gui.configuration.Messages";
	private static final String RESOURCE_MENUNAMES = "nl.ou.jp.gui.configuration.MenuNames";

	//Dimensions
	private static final int DEFAULT_FRAME_WIDTH = 1200;
	private static final int DEFAULT_FRAME_HEIGHT = 800;
	
	private static final int XPOS = 1100;
	private static final int YPOS = 20;
	
	//Colors
	public static final Color BGCOLOR = Color.white;
	public static final Color COLOR = Color.black;
	
	//Fonts
	private static final String FONTNAME = "Dialog";
	private static final int FONTSTYLE = Font.BOLD;
	private static final int FONTHEIGHT = 10;
	private static final String DEFAULT_TEXT_FONTNAME = "Helvetica";
	private SlideItemStyle[] slideItemStyles; 
	
	public DefaultConfiguration() {
		createStyles();
	}

	@Override
	public String getDefaultTitle() {
		return getMessage(APPLICATIONTITLEID);
	}
	
	@Override
	public SlideItemStyle getStyle(int level) {
		if (level >= slideItemStyles.length) {
			level = slideItemStyles.length - 1;
		}
		return slideItemStyles[level];
	}
	
	@Override
	public Dimension getDefaultSlideDimensions() {
		return new Dimension(DEFAULT_FRAME_WIDTH,DEFAULT_FRAME_HEIGHT);
	}
	
	@Override
	public Dimension getDefaultInnerSlideDimensions() {
		return new Dimension(YPOS, XPOS);
	}
	
	@Override
	public Color getSlideBackgroundColor() {
		return BGCOLOR;
	}
	
	@Override
	public Color getDefaultFontColor() {
		return COLOR;
	}
	
	@Override
	public Font getDefaultLabelFont() {
		return new Font(FONTNAME, FONTSTYLE, FONTHEIGHT);
	}
	
	@Override
	public String getMenuName(String menuId) {
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_MENUNAMES);
			return resourceBundle.getString(menuId);			
		}catch(Exception e) {
			logger.logError(e.getMessage());
		}
		return UNKOWN_MENUID_MESSAGE;
	}
	
	@Override
	public String getMessage(String messageId) {
		try {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_MESSAGES);
			return resourceBundle.getString(messageId);			
		}catch(Exception e) {
			logger.logError(e.getMessage());
		}
		return UNKOWN_MESSAGEID_MESSAGE;
		
	}
	
	private void createStyles() {
		slideItemStyles = new SlideItemStyle[5];    
		// De styles zijn vast ingecodeerd.
		slideItemStyles[0] = new SlideItemStyleImp(DEFAULT_TEXT_FONTNAME, 00, Color.red,   48, 20);	// style voor item-level 0
		slideItemStyles[1] = new SlideItemStyleImp(DEFAULT_TEXT_FONTNAME, 20, Color.blue,  40, 10);	// style voor item-level 1
		slideItemStyles[2] = new SlideItemStyleImp(DEFAULT_TEXT_FONTNAME, 50, Color.black, 36, 10);	// style voor item-level 2
		slideItemStyles[3] = new SlideItemStyleImp(DEFAULT_TEXT_FONTNAME, 70, Color.black, 30, 10);	// style voor item-level 3
		slideItemStyles[4] = new SlideItemStyleImp(DEFAULT_TEXT_FONTNAME, 90, Color.black, 24, 10);	// style voor item-level 4
	}
}
