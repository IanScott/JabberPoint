package nl.ou.jp.domain;

import nl.ou.jp.domain.core.model.SlideShow;
import nl.ou.jp.util.Event;

public class SlideShowEvent implements Event {

	private SlideShow source;
	
	public SlideShowEvent(SlideShow slideShow) 
	{
		this.source = slideShow;
	}
	
	@Override
	public Object getSource() {
		return source;
	}
}