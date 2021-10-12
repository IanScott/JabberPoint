package nl.ou.jp.util;

import java.util.ArrayList;
import java.util.List;

public abstract class EventDispatcherAdapter implements EventDispatcher {
	protected List<EventListener> eventListeners;

	@Override
	public void addListener(EventListener subscriber) {
		if(this.eventListeners == null) {
			this.eventListeners = new ArrayList<>();
		}
		this.eventListeners.add(subscriber);
	}

	@Override
	public void removeListener(EventListener eventListener) {
		if(this.eventListeners != null) {
			this.eventListeners.remove(eventListener);			
		}
	}
}
