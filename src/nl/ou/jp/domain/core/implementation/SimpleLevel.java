package nl.ou.jp.domain.core.implementation;

import nl.ou.jp.domain.core.model.Level;

public class SimpleLevel implements Level {

	private Integer rating = null;
	
	public SimpleLevel(Integer rating) {
		this.rating = rating;
	}
	
	@Override
	public Integer getRating() {
		return this.rating;
	}
}