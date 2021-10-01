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
	
	private ProjectorInfra projectorInfra = null;
	private SlideShowService slideShowService = null;
	
	private List<EventListener> eventListeners = null; 
	
	public ProjectorControllerImp(ProjectorInfra projectorInfra, SlideShowService service) {
		this.projectorInfra = projectorInfra;
		this.slideShowService = service;
		if(this.slideShowService == null || this.projectorInfra == null) {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public String getSlideShowTitle() {
		logger.logDebug("Calling getSlideShowTitle");
		return this.slideShowService.getSlideShowTitle();
	}
	
	@Override
	public int getSlideShowSize() {
		logger.logDebug("Calling getSlideShowSize");
		return this.slideShowService.getSlideShowSize();
	}

	@Override
	public int getCurrentSlideNumber() {
		logger.logDebug("Calling getCurrentSlideNumber");
		return this.slideShowService.getCurrentSlideNumber();
	}
	
	@Override
	public Slide getCurrentSlide() {
		logger.logDebug("Calling getCurrentSlide");
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
	}
	
	@Override
	public void gotoSlideNumber(int number) {
		this.slideShowService.gotoSlideIndex(number);
	}

	@Override
	public void previousSlide() {
		this.slideShowService.previousSlide();
	}

	@Override
	public void nextSlide() {
		this.slideShowService.nextSlide();
	}
	

	@Override
	public void reset() {
		this.slideShowService.resetSlideShow();
	}


	@Override
	public void registerSlideShowListeners(EventListener listener) {
		this.slideShowService.getSlideEventDispatcher().addListener(listener);
		this.slideShowService.getSlideShowEventDispatcher().addListener(listener);
	}
}