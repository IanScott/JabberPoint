package nl.ou.jp.domain.implementation;

import java.util.*;

import nl.ou.jp.domain.*;
import nl.ou.jp.domain.core.implementation.*;
import nl.ou.jp.domain.core.model.*;

public class SlideShowBuilderImp extends ComponantBuilder implements SlideShowBuilder {

	private Slide lastslide = null;
	
	public SlideShowBuilderImp() {
		super();
	}
	
	public SlideShowBuilderImp(String title, List<SlideShowComponant> componants) {
		super(title, componants);
	}
	
	@Override
	public void withSlideShowTitle(String title) {
		this.withTitle(title);
	}

	@Override
	public void appendSlide(String title) {
		this.lastslide = new SlideImp(title, new ArrayList<>());
		this.withComponant(this.lastslide);
	}
	
	@Override
	public void appendTextItemToSlide(String text, int level) {
		SlideShowComponant item = SlideShowComponantFactory.getInstance().createSlideShowComponant(SlideShowComponantFactory.TEXTITEM_TYPE);
		((TextItem)item).setLevel(new SimpleLevel(level));
		((TextItem)item).setText(text);
		this.lastslide.add(item);
	}
	
	@Override
	public void appendFigureItemToSlide(String source, int level) {
		SlideShowComponant item = SlideShowComponantFactory.getInstance().createSlideShowComponant(SlideShowComponantFactory.FIGUREITEM_TYPE);
		((FigureItem)item).setLevel(new SimpleLevel(level));
		((FigureItem)item).setSource(source);
		this.lastslide.add(item);
	}
	
	public SlideShow getSlideShow() {
		return(SlideShow)this.build();
	}

	@Override
	protected boolean componantAllowed(SlideShowComponant componant) {
		return (componant instanceof Slide);
	}

	@Override
	protected SlideShowComponant create(String title, List<SlideShowComponant> componants) {
		SlideShowImp slideShow = new SlideShowImp(title,componants);
		slideShow.setState(SlideShowEditableState.getInstance());		
		return slideShow;
	}
}