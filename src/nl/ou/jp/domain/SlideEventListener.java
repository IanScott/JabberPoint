package nl.ou.jp.domain;

import nl.ou.jp.util.EventListener;

public interface SlideEventListener extends EventListener {

	 void eventReceived(SlideEvent event);
}
