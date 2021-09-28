package nl.ou.jp.domain.core.implementation;

import nl.ou.jp.domain.core.model.*;

public abstract class SlideShowLeaf implements SlideShowComponant {

	@Override
	public void add(SlideShowComponant componant) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SlideShowComponantIterator getIterator() {
		throw new UnsupportedOperationException();
	}
}
