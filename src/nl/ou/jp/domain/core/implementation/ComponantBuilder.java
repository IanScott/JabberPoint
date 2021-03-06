package nl.ou.jp.domain.core.implementation;

import java.util.*;

import nl.ou.jp.domain.core.model.*;

public abstract class ComponantBuilder {
	private static final String ILLEGALCOMPONANTMESSAGE = "Illegal Componant: ";
	
	protected String title = null;
	protected List<SlideShowComponant> componants = null;
	
	protected ComponantBuilder() {
		super();
	}
	
	protected ComponantBuilder(String title, List<SlideShowComponant> componants) {
		this.title = title;
		this.componants = componants;
	}
	
	public ComponantBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
	
	public <T extends SlideShowComponant>ComponantBuilder withComponants(List<T> componants) {
		for(T componant: componants) {
			addComponant(componant);
		}
		return this;
	}
	
	public <T extends SlideShowComponant>ComponantBuilder withComponant(T componant) {		
		addComponant(componant);
		return this;
	}
	
	private void addComponant(SlideShowComponant componant) {
		if(this.componantAllowed(componant)) {
			addComponantImp(componant);
		}else {
			throw new SlideShowException(ILLEGALCOMPONANTMESSAGE + componant.getClass().getSimpleName());
		}
	}
	
	protected abstract boolean componantAllowed(SlideShowComponant componant);
	
	private void addComponantImp(SlideShowComponant componant) {
		if(this.componants == null) {
			this.componants = new ArrayList<>();
		}
		this.componants.add(componant);
	}
	
	public SlideShowComponant build() {
		return create(this.title, this.componants);
	}

	protected abstract SlideShowComponant create(String title, List<SlideShowComponant> componants);
}