package nl.ou.jp.gui.implementation;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;
import java.nio.file.Path;

import nl.ou.jp.controller.ProjectorController;
import nl.ou.jp.domain.*;
import nl.ou.jp.domain.core.model.*;
import nl.ou.jp.gui.*;
import nl.ou.jp.gui.model.*;
import nl.ou.jp.logging.*;
import nl.ou.jp.util.Event;

/**
*/
public class ProjectorGUIImp extends JFrame implements ProjectorGUI {
	private static final long serialVersionUID = 3227L;
	private transient Logger logger = LoggerManager.getLogger();
	private transient DrawStrategy strategy = null;

	private transient ProjectorContext projectorContext = null;
	private transient ProjectorConfiguration configurationDefault = null;

	private transient Slide currentSlide;

	public ProjectorGUIImp(DrawStrategy strategy, ProjectorConfiguration configurationDefault, ProjectorContext context) {
		
		this.strategy = strategy;
		this.configurationDefault = configurationDefault;

		if (this.configurationDefault == null) {
			throw new ProjectorGUIException("Configuration cannot be NULL.");
		}

		if (this.strategy == null) {
			throw new ProjectorGUIException("Strategy cannot be NULL.");
		}

		setTitle(configurationDefault.getDefaultTitle());
		setSize(configurationDefault.getDefaultSlideDimensions());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		
		initialize(context);
	}

	private void initialize(ProjectorContext projectorContext) {

		ProjectorController projectorController = projectorContext.getController();		
		if(projectorController == null) {

			throw new ProjectorGUIException("Context item is invalid.");
		}

		this.projectorContext = projectorContext;
		this.projectorContext.setMainGUI(this);
		this.projectorContext.setConfiguration(this.configurationDefault);
		this.projectorContext.setAnnotationLineColor(this.configurationDefault.getDefaultColorCode());
		this.projectorContext.setAnnotationLineWeight(this.configurationDefault.getDefaultLineWeight());
		
		projectorController.registerSlideShowListeners(this);

		initializeContentPane();
	}

	@Override
	public void start(String path) {
		if(this.projectorContext == null || this.projectorContext.getController() == null) {
			throw new ProjectorGUIException("Context has not been intialized yet.");
		}
		if(path != null) {
			this.projectorContext.getController().openSlideShow(Path.of(path));			
		}
		setVisible(true);
	}

	private void initializeContentPane() {
		getContentPane().add(new JComponent() {
			private static final long serialVersionUID = -7453172265625523541L;

			@Override
			public Dimension getPreferredSize() {
				return configurationDefault.getDefaultSlideDimensions();
			}

			@Override
			public void paintComponent(Graphics g) {
				validateParameters(g, projectorContext);
			
				if(currentSlide != null) {
					strategy.setContext(projectorContext);
					strategy.draw(g, this, currentSlide, null, 0, 0);					
				}else {
					this.removeAll();
					super.paintComponent(g);
				} 
			}
		});
	}

	private boolean validateParameters(Graphics g, ProjectorContext projectorContext) {
		if (g == null || projectorContext == null) {
			throw new ProjectorGUIException("Input parameters cannot be NULL.");
		}

		if(projectorContext.getConfiguration() == null || projectorContext.getMainGUI() == null || projectorContext.getController() == null) {
			throw new ProjectorGUIException("Context has not been properly initialized.");
		}
		
		return (projectorContext.getController().getSlideShowSize() > 0);
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
	public void showErrorMessageDialog(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void exit() {
		this.dispose(); // clearup before termination.
		this.setVisible(false);
		System.exit(0);
	}

	@Override 
	public void eventReceived(Event event) 
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
	
	private void eventReceivedSlideShow(SlideShowEvent event) {
		logger.logInfo("updating");
		SlideShow source = (SlideShow) event.getSource();
		
		if (source != null) {
			this.setTitle(source.getTitle());
		}else {
			this.currentSlide = null;
			update();
		}
	}
	
	private void eventReceivedSlide(SlideEvent event) {
		logger.logInfo("updating");
		currentSlide = (Slide) event.getSource();
		update();
	}

	@Override
	public int showItemSelectorDialog(String message, String title, String[] items) {
		return JOptionPane.showOptionDialog(this,
				message,
				title,
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				items,
				items[0]);
	}

	@Override
	public Dimension getCanvasDimension() {
		var dimension = new Dimension();
		dimension.width = this.getContentPane().getWidth();
		dimension.height = this.getContentPane().getHeight();
		return dimension;
	}

	@Override
	public void setMenubar(MenuBar menubar) {
		setMenuBar(menubar);
	}

	@Override
	public void setWindowlistener(WindowListener listener) {
		addWindowListener(listener);
	}

	@Override
	public void setKeylistener(KeyListener listener) {
		addKeyListener(listener); 
	}

	@Override
	public void setMouseListener(MouseListener listener) {
		getContentPane().addMouseListener(listener);
	}

	@Override
	public void setMouseInputListener(MouseInputListener listener) {
		getContentPane().addMouseMotionListener(listener);
	}
}