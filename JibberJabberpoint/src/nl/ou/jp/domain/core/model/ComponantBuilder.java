package nl.ou.jp.domain.core.model;

import java.util.List;

public interface ComponantBuilder {
	ComponantBuilder withTitle(String title);
	
	<T extends SlideShowComponant>ComponantBuilder withComponants(List<T> componants);
	
	<T extends SlideShowComponant>ComponantBuilder withComponant(T componant);
	
	SlideShowComponant build();
	
	ComponantBuilder clone();
}