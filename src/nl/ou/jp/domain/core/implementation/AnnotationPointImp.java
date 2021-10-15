package nl.ou.jp.domain.core.implementation;

import nl.ou.jp.domain.core.model.AnnotationPoint;
import nl.ou.jp.domain.core.model.RelativePosition;
import nl.ou.jp.domain.core.model.SlideShowComponant;

public class AnnotationPointImp   extends SlideShowLeaf implements AnnotationPoint {

	private RelativePosition relativePosition; 
	
	public AnnotationPointImp() {
		super();
	}
	
	public AnnotationPointImp(RelativePosition relativePosition) {
		this.relativePosition = relativePosition;
	}
	
	public RelativePosition getRelativePosition() {
		return relativePosition;
	}

	@Override
	public void setRelativePosition(RelativePosition position) {
		this.relativePosition = position;
	}
	
	@Override
	public SlideShowComponant copy() {
		return new AnnotationPointImp(this.relativePosition);
	}
}
