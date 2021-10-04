package nl.ou.jp.domain.core.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nl.ou.jp.domain.core.model.SlideShowComponant;
import nl.ou.jp.domain.core.model.SlideShowComponantIterator;

public abstract class SlideShowCompositeTemplate {
	private static final int EMPTY = 0;
	
	protected String title = null;
	
	protected List<SlideShowComponant> componants = null;
	
	protected SlideShowCompositeTemplate(String title, List<SlideShowComponant> componants) 
	{
		this.title = title; 
		this.componants = componants; 
	}
	 
	protected SlideShowCompositeTemplate() {
		
	}
	
	public String getTitle() {
		return this.title;
	}

	public int size() {
		if(this.componants != null) {
			return this.componants.size();
		}
		return EMPTY;
	}
	
	public SlideShowComponantIterator getIterator() {
		if(this.componants == null) {
			this.componants = new ArrayList<>();
		}
		return new SlideShowComponantIteratorImp(this.componants);
	}
	
	public SlideShowComponant copy() {
		List<SlideShowComponant> componantsCopy = this.componants.stream().map(SlideShowComponant::copy).collect(Collectors.toList());
		return createComponant(this.title, componantsCopy);
	}
	
	protected abstract SlideShowComponant createComponant(String title, List<SlideShowComponant> componants);
}
