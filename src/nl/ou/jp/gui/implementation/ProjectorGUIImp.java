package nl.ou.jp.gui.implementation;

import javax.swing.*;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.nio.file.Path;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.domain.SlideEvent;
import nl.ou.jp.domain.SlideShowEvent;
import nl.ou.jp.domain.SlideShowService;
import nl.ou.jp.domain.core.model.Slide;
import nl.ou.jp.domain.core.model.SlideShow;
import nl.ou.jp.domain.core.model.SlideShowComponant;
import nl.ou.jp.gui.ProjectorGUI;
import nl.ou.jp.gui.ProjectorGUIException;
import nl.ou.jp.gui.model.*;
import nl.ou.jp.logging.*;
import nl.ou.jp.util.Event;

/**
*/
public class ProjectorGUIImp extends JFrame implements ProjectorGUI {
	private static final long serialVersionUID = 3227L;
	private transient Logger logger = LoggerManager.getLogger();
	private transient SwingDrawStrategy strategy = null;

	private transient ProjectorContext projectorContext = null;
	private transient ProjectorConfiguration configurationDefault = null;

	private Slide currentSlide;

	protected ProjectorGUIImp(SwingDrawStrategy strategy, MenuBar projectorViewMenuBar, WindowListener windowListener,
			KeyListener keyListener, ProjectorConfiguration configurationDefault) {
		this.strategy = strategy;
		this.configurationDefault = configurationDefault;

		if (this.configurationDefault == null) {
			throw new ProjectorGUIException("Configuration cannot be NULL.");
		}

		if (this.strategy == null) {
			throw new ProjectorGUIException("Strategy cannot be NULL.");
		}

		setMenuBar(projectorViewMenuBar); // may be null
		addWindowListener(windowListener); // may be null
		addKeyListener(keyListener); // may be null

		setTitle(configurationDefault.getDefaultTitle());
		var dimension = configurationDefault.getDefaultSlideDimensions();
		setSize(dimension.getWidth(), dimension.getHeight());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void initialize(ProjectorContext projectorContext) {
		ProjectorController projectorController = projectorContext.getProjector();

		if (projectorController == null) {
			throw new ProjectorGUIException("Context item is invalid.");
		}

		this.projectorContext = projectorContext;
		this.projectorContext.setMainGUI(this);
		this.projectorContext.setConfiguration(this.configurationDefault);

		projectorController.registerSlideShowListeners(this);

		initializeContentPane();
	}

	@Override
	public void start(String path) {
		if (this.projectorContext == null || this.projectorContext.getProjector() == null) {
			throw new ProjectorGUIException("Context has not been intialized yet.");
		}

		if (path != null) {
			this.projectorContext.getProjector().openPresentation(Path.of(path));
		}

		setVisible(true);
	}

	private void initializeContentPane() {
		getContentPane().add(new JComponent() {
			private static final long serialVersionUID = -7453172265625523541L;

			@Override
			public Dimension getPreferredSize() {
				var dimension = configurationDefault.getDefaultSlideDimensions();
				Dimension result = new Dimension();
				result.height = dimension.getHeight();
				result.width = dimension.getWidth();
				return result;
			}

			@Override
			public void paintComponent(Graphics g) {
				validateParameters(g, projectorContext);

				Slide slide = projectorContext.getProjector().getCurrentSlide();
				if(slide != null) {
					strategy.setContext(projectorContext);
					strategy.draw(g, slide, null, 0, 0);					
				} 
			}
		});
	}

	private boolean validateParameters(Graphics g, ProjectorContext projectorContext) {
		if (g == null || projectorContext == null) {
			throw new ProjectorGUIException("Input parameters cannot be NULL.");
		}
		if (projectorContext.getConfiguration() == null || projectorContext.getMainGUI() == null
				|| projectorContext.getProjector() == null) {
			throw new ProjectorGUIException("Context has not been properly initialized.");
		}

		return (projectorContext.getProjector().getSlideShowSize() > 0);
	}

	private void eventReceivedSlideShow(SlideShowEvent event) {
		logger.logInfo("updating");
		SlideShow source = (SlideShow) event.getSource();

		if (source != null) {
			this.setTitle(source.getTitle());
		}
	}

	private void eventReceivedSlide(SlideEvent event) {
		logger.logInfo("updating");
		currentSlide = (Slide) event.getSource();
		update();
	}

	private void update() {
		repaint();
		this.getContentPane().repaint();
	}

	@Override
	public void showMessageDialog(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void exit() {
		this.dispose(); // clearup before termination.
		this.setVisible(false);
		System.exit(0);
	}

	@Override
	public void showErrorMessage(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}

	
	  @Override public void eventReceived(Event event) 
	  { 
		  if(event instanceof SlideShowEvent) 
		  {
			  this.eventReceivedSlideShow((SlideShowEvent)event);
		  }
		  if(event instanceof SlideEvent) 
		  {
			  this.eventReceivedSlide((SlideEvent)event);
		  }
	  }
}
