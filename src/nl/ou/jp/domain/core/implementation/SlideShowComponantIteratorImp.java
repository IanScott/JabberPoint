package nl.ou.jp.domain.core.implementation;

import java.util.*;

import nl.ou.jp.domain.core.model.SlideShowComponant;
import nl.ou.jp.domain.core.model.SlideShowComponantIterator;

public class SlideShowComponantIteratorImp implements SlideShowComponantIterator {

	private int currentIndex = -1;
	private List<SlideShowComponant> componants;
	
	public SlideShowComponantIteratorImp(List<SlideShowComponant> componants) {
		this.componants = componants;
	}
	
	@Override
	public boolean hasNext() {
		if(this.componants == null) {
			return false;
		}
		int nextIndex = currentIndex + 1;
		return (nextIndex < componants.size());
	}

	@Override
	public void gotoNext() {
		if(this.componants == null || this.currentIndex > this.componants.size()) {
			throw new NoSuchElementException();
		}
		this.currentIndex = this.currentIndex + 1;
	}

	@Override
	public boolean hasPrevious() {
		if(this.componants == null) {
			return false;
		}
		int previousIndex = currentIndex -1;
		return (previousIndex > -1);
	}

	@Override
	public void gotoPrevious() {
		if(this.componants == null || this.currentIndex < 0) {
			throw new NoSuchElementException();
		}
		
		this.currentIndex = this.currentIndex -1;
	}

	@Override
	public SlideShowComponant getCurrentItem() {
		if(this.componants == null || this.currentIndex < 0) {
			throw new NoSuchElementException();
		}
		
		this.componants.get(currentIndex).setSequenceNumber(this.currentIndex);
		return this.componants.get(this.currentIndex);			
	}

	@Override
	public int getCurrentIndex() {
		return this.currentIndex;
	}

	@Override
	public void setIndex(int index) {
		if(this.componants == null || index < 0 || index > this.componants.size() -1) {
			throw new NoSuchElementException();
		}
		
		this.currentIndex = index;
	}
}
