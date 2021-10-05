package nl.ou.jp.domain.core.implementation;

import java.util.*;

import nl.ou.jp.domain.core.model.*;
import nl.ou.jp.gui.implementation.drawstrategies.AnnotationLineDrawStrategy;

public class SlideBuilderImp extends ComponantBuilderTemplate implements SlideBuilder {
	
	public SlideBuilderImp() {
		super();
	}
	
	public SlideBuilderImp(String title, List<SlideShowComponant> componants) {
		super(title, componants);
	}
	
	@Override
	protected boolean componantAllowed(SlideShowComponant componant) {
		return (componant instanceof SlideShowItem || componant instanceof AnnotationLineDrawStrategy.AnnotationItem);
	}

	@Override
	protected SlideShowComponant create(String title, List<SlideShowComponant> componants) {
		return new SlideImp(title, componants);
	}
	
	@Override
	public ComponantBuilder clone() {
		return new SlideBuilderImp(this.title, this.componants);
	}
}