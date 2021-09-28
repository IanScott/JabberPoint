package nl.ou.jp.domain.core.implementation;

import nl.ou.jp.domain.core.model.*;

public class FigureItemImp extends SlideShowLeaf implements FigureItem {
	private String source = null;
	private Level level = null;
	
	public FigureItemImp(String source, Level level) {
		this.source = source;
		this.level = level;
	}
	
	@Override
	public String getSource() {
		return this.source;
	}

	@Override
	public Level getLevel() {
		return this.level;
	}
	
	@Override
	public SlideShowComponant copy() {
		return new FigureItemImp(this.source, this.level);
	}
}