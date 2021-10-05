package nl.ou.jp.domain.core.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nl.ou.jp.domain.core.model.SlideShowComponant;
import nl.ou.jp.domain.core.model.SlideShowComponantIterator;
import nl.ou.jp.domain.core.model.SlideShowComposite;

public abstract class SlideShowCompositeTemplate implements SlideShowComposite {
	private static final int EMPTY = 0;
	
	private String title = null;
	protected List<SlideShowComponant> componants = null;
	
	protected SlideShowCompositeTemplate(String title, List<SlideShowComponant> componants) {
		this.title = title;
		this.componants = componants;
	}
	
	@Override
	public String getTitle() {
		return this.title;
	}

	@Override
	public int size() {
		if(this.componants != null) {
			return this.componants.size();
		}
		return EMPTY;
	}

	@Override
	public void add(SlideShowComponant componant) {
		if(this.componants == null) {
			this.componants = new ArrayList<>();
		}
		this.componants.add(componant);
	}

	@Override
	public SlideShowComponantIterator getIterator() {
		if(this.componants == null) {
			this.componants = new ArrayList<>();
		}
		return new SlideShowComponantIteratorImp(this.componants);
	}
	
	@Override
	public SlideShowComponant copy() {
		List<SlideShowComponant> componantsCopy = this.componants.stream().map(SlideShowComponant::copy).collect(Collectors.toList());
		return createComponant(this.title, componantsCopy);
	}
	
	protected abstract SlideShowComponant createComponant(String title, List<SlideShowComponant> componants);
}
