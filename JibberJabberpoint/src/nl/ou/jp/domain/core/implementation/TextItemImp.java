package nl.ou.jp.domain.core.implementation;

import nl.ou.jp.domain.core.model.*;

public class TextItemImp extends SlideShowLeaf implements TextItem {

	private String text = null;
	private Level level = null;
	
	public TextItemImp(String text, Level level) {
		this.text = text;
		this.level = level;
	}
	
	@Override
	public String getText() {
		return this.text;
	}

	@Override
	public Level getLevel() {
		return this.level;
	}
	
	@Override
	public SlideShowComponant copy() {
		return new TextItemImp(this.text, this.level);
	}
}