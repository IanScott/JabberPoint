package nl.ou.jp.infra.implementation;
import java.io.*;

import javax.xml.parsers.*;

import org.xml.sax.SAXException;
import org.w3c.dom.*;

import nl.ou.jp.domain.SlideShowBuilder;
import nl.ou.jp.domain.core.model.SlideShow;
import nl.ou.jp.infra.model.Accessor;
import nl.ou.jp.logging.*;


/** XMLAccessor, reads and writes XML files
 */

public class XMLAccessor implements Accessor {
	private static final String[] SUPPORTEDEXTENSIONS = new String[] {"xml","XML"};

	private Logger logger = LoggerManager.getLogger();
	
    /** Default API to use. */
    protected static final String DEFAULT_API_TO_USE = "dom";
    
    /** namen van xml tags of attributen */
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";
    
    /** tekst van messages */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";
     
	@Override
	public String[] getSupportedFileExtensions() {
		return SUPPORTEDEXTENSIONS;
	}
    
    private String getTextContent(Element element, String tagName) {
    	NodeList titles = element.getElementsByTagName(tagName);
    	return titles.item(0).getTextContent();
    	
    }

    @Override
	public SlideShowBuilder loadFile(SlideShowBuilder slideshowBuilder, String filename){
		int slideNumber = 0 ;
		int itemNumber = 0; 
		int max = 0;
		int maxItems = 0;
		try {
			Element doc = getDocumentElement(filename);
			
			String title = getTextContent(doc, SHOWTITLE);
			slideshowBuilder.withSlideShowTitle(title);
			
			NodeList slideNodes = doc.getElementsByTagName(SLIDE);
			max = slideNodes.getLength();
			for (slideNumber = 0; slideNumber < max; slideNumber++) {
				Element xmlSlide = (Element) slideNodes.item(slideNumber);
				
				String slideTitle = getTextContent(xmlSlide, SLIDETITLE);
				slideshowBuilder.appendSlide(slideTitle);
				
				NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
				maxItems = slideItems.getLength();
				for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {
					Element item = (Element) slideItems.item(itemNumber);
					String type = getType(item);
					String data = getData(item);
					int level = getLevel(item);
					
					if("TEXT".equals(type)) {
						slideshowBuilder.appendTextItemToSlide(data, level);
					}
					
					if("IMAGE".equals(type)) {
						slideshowBuilder.appendFigureItemToSlide(data, level);
					}
				}
			}
		} 
		catch (IOException | SAXException | ParserConfigurationException e) {
			logger.logError(e.toString());
		}
		
		return slideshowBuilder;
	}

	private Element getDocumentElement(String filename) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();  
		
		Document document = builder.parse(new File(filename)); // maak een JDOM document
		return document.getDocumentElement();
	}

	
	private Integer getLevel(Element item) {
		int level = 1; // default
		NamedNodeMap attributes = item.getAttributes();
		String leveltext = attributes.getNamedItem(LEVEL).getTextContent();
		if (leveltext != null) {
			try {
				level = Integer.parseInt(leveltext);
			}
			catch(NumberFormatException x) {
				logger.logError(NFE);
			}
		}
		return level;
	}
	
	private String getType(Element item) {
		NamedNodeMap attributes = item.getAttributes();
		return attributes.getNamedItem(KIND).getTextContent().toUpperCase();
	}
	
	private String getData(Element item) {
		return item.getTextContent();
	}
	
	@Override
	public void saveFile(SlideShow slideshow, String filename) {
		logger.logDebug("Not Implemented.");
	}
}
