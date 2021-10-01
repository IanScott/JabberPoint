package nl.ou.jp.domain;

import nl.ou.jp.util.EventListener;

public interface SlideShowEventListener extends EventListener {

	 void eventReceived(SlideShowEvent event);
}
