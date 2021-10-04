package nl.ou.jp.domain;

import nl.ou.jp.domain.core.model.Slide;
import nl.ou.jp.util.EventDispatcherAdapter;

public class SlideEventDispatcher extends EventDispatcherAdapter {

	@Override
	public void fireEvent(Object source) {
		
		if(source instanceof Slide && this.eventListeners != null) 
		{
			this.eventListeners.forEach(x -> x.eventReceived(new SlideEvent((Slide)source)));				
		}
	}
}