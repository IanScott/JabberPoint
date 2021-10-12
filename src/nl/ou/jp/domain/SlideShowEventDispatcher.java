package nl.ou.jp.domain;

import nl.ou.jp.domain.core.model.SlideShow;
import nl.ou.jp.util.EventDispatcherAdapter;

public class SlideShowEventDispatcher extends EventDispatcherAdapter {

	@Override
	public void fireEvent(Object source) {
		if(this.eventListeners != null) {
			this.eventListeners.forEach(x -> x.eventReceived(new SlideShowEvent((SlideShow)source)));			
		}	
	}
}