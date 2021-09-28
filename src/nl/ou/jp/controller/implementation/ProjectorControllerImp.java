package nl.ou.jp.controller.implementation;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.*;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.domain.SlideShowService;
import nl.ou.jp.domain.core.model.*;
import nl.ou.jp.infra.*;
import nl.ou.jp.logging.Logger;
import nl.ou.jp.logging.LoggerManager;
import nl.ou.jp.util.*;
import nl.ou.jp.util.EventListener;

public class ProjectorControllerImp implements ProjectorController {
	private Logger logger = LoggerManager.getLogger();
	private static final String EMPTYSTRING = "";
	private static final int EMPTYSIZE = -1;
	
	private ProjectorInfra projectorInfra = null;
	private SlideShowService slideShowService = null;
	
	private List<EventListener> eventListeners = null; 
	
	public ProjectorControllerImp(ProjectorInfra projectorInfra, SlideShowService service) {
		this.projectorInfra = projectorInfra;
		this.slideShowService = service;
	}
	
	@Override
	public String getSlideShowTitle() {
		logger.logDebug("Calling getSlideShowTitle");
		if(this.slideShowService == null) {
			return EMPTYSTRING;
		}
		return this.slideShowService.getSlideShowTitle();
	}
	
	@Override
	public int getSlideShowSize() {
		logger.logDebug("Calling getSlideShowSize");
		if(this.slideShowService == null) {
			return EMPTYSIZE;
		}
		return this.slideShowService.getSlideShowSize();
	}

	//@Override
	public int getCurrentSlideNumber() {
		logger.logDebug("Calling getCurrentSlideNumber");
		if(this.slideShowService == null) {
			return EMPTYSIZE;
		}
		return this.slideShowService.getCurrentSlideNumber();
	}

	/*
	 * CURRENT SLIDE DATA. Methods for retrieving current slide data.
	 */
	
	@Override
	public Slide getCurrentSlide() {
		logger.logDebug("Calling getCurrentSlide");
		if(this.slideShowService == null) {
			return null;
		}
		return this.slideShowService.getCurrentSlide();
	}
	
	@Override
	public InputStream fetchFileAsStream(Path path) {
		return this.projectorInfra.fetchFileAsStream(path);
	}
	
	@Override
	public void openPresentation(Path path) {
		SlideShow slideshow = this.projectorInfra.openPresentation(path);
		this.slideShowService.loadSlideShow(slideshow);
		
		this.fireEvent(()-> this.slideShowService);
	}
	
	@Override
	public void gotoSlideNumber(int number) {
		if(this.slideShowService != null) {
			this.slideShowService.gotoSlideIndex(number);
		}
		this.fireEvent(this::getCurrentSlide);
		logger.logDebug("seq: "+this.getCurrentSlide().getSequenceNumber());
	}

	@Override
	public void previousSlide() {
		if(this.slideShowService != null) {
			this.slideShowService.previousSlide();
		}
		this.fireEvent(this::getCurrentSlide);
	}

	@Override
	public void nextSlide() {
		if(this.slideShowService != null) {
			this.slideShowService.nextSlide();
		}
		this.fireEvent(this::getCurrentSlide);
	}
	
	/*
	 * EVENTDISPATCHER. Implemented inherited EventDispatcher Methods.
	 */
	
	@Override
	public void addListener(EventListener subscriber) {
		if(this.eventListeners == null) {
			this.eventListeners = new ArrayList<>();
		}
		this.eventListeners.add(subscriber);
	}

	@Override
	public void removeListener(EventListener eventListener) {
		this.eventListeners.remove(eventListener);
	}
	
	@Override
	public void fireEvent(Event event) {
		if(this.eventListeners != null) {
			this.eventListeners.forEach(x -> x.eventReceived(event));			
		}
	}

	@Override
	public void reset() {
		this.slideShowService = null;
		fireEvent(()->this);
	}
}