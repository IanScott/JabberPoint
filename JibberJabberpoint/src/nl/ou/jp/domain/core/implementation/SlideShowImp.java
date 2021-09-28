package nl.ou.jp.domain.core.implementation;

import java.util.*;
import nl.ou.jp.domain.core.model.*;

public class SlideShowImp extends SlideShowCompositeTemplate implements SlideShow {

	public SlideShowImp(String title, List<SlideShowComponant> slides) {
		super(title, slides);
	}
	
	@Override
	public Slide getSlide(int index) {
		if(this.componants != null) {
			return (Slide)this.componants.get(index);
		}
		return null;
	}

	@Override
	protected SlideShowComponant createComponant(String title, List<SlideShowComponant> componants) {
		return new SlideShowImp(title, componants);
	}
}