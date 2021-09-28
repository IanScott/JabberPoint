package nl.ou.jp.util;

public interface EventDispatcher {
	void addListener(EventListener eventListener);
	void removeListener(EventListener eventListener);
	
	void fireEvent(Event event);
}
