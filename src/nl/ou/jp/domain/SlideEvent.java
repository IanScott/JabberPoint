package nl.ou.jp.domain;

import nl.ou.jp.domain.core.model.Slide;
import nl.ou.jp.util.Event;

public class SlideEvent implements Event {
	
	private Slide source;
	
	public SlideEvent(Slide slide) 
	{
		this.source = slide;
	}

	@Override
	public Object getSource() {
		return source;
	}
}
