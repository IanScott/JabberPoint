package nl.ou.jp.domain.implementation;

import java.util.*;
import java.util.stream.Collectors;

import nl.ou.jp.domain.*;
import nl.ou.jp.domain.core.*;
import nl.ou.jp.domain.core.implementation.*;
import nl.ou.jp.domain.core.model.*;

public class SlideShowBuilderImp extends ComponantBuilderTemplate implements SlideShowBuilder {

	private List<ComponantBuilder> componantBuilders = null;
	private ComponantBuilder protoSlideBuilder = null;
	
	public SlideShowBuilderImp(ComponantBuilder protoSlideBuilder) {
		this.protoSlideBuilder = protoSlideBuilder;
	}
	
	public SlideShowBuilderImp(ComponantBuilder protoSlideBuilder, String title, List<SlideShowComponant> componants) {
		super(title, componants);
		this.protoSlideBuilder = protoSlideBuilder;
	}
	
	@Override
	public void withSlideShowTitle(String title) {
		this.withTitle(title);
	}

	@Override
	public void appendSlide(String title) {
		if(this.componantBuilders  == null) {
			this.componantBuilders = new ArrayList<>();
		}
		ComponantBuilder componantBuilder = protoSlideBuilder.clone(); 
		componantBuilder.withTitle(title);
		this.componantBuilders.add(componantBuilder);
		
	}

	@Override
	public void appendSlideItemToSlide(int slideIndex, String type, String data, int level) {
		if(this.componantBuilders != null && slideIndex < this.componantBuilders.size()) {
			ComponantBuilder builder = this.componantBuilders.get(slideIndex);
			SlideShowItem item = SlideShowItemFactory.getInstance().createSlideShowItem(type, data, level);
			builder.withComponant(item);
		}
	}

	@Override
	public SlideShow getSlideShow() {
		List<SlideShowComponant> slides = componantBuilders.stream().map(ComponantBuilder::build).collect(Collectors.toList());
		this.withComponants(slides);
		SlideShow slideshow = (SlideShow)this.build();
		
		//reset values
		this.componants = null;
		this.title = null;
		this.componantBuilders = null;
		
		return slideshow;
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
	
	@Override
	public ComponantBuilder clone() {
		return new SlideShowBuilderImp(this.protoSlideBuilder, this.title, this.componants);
	}
}