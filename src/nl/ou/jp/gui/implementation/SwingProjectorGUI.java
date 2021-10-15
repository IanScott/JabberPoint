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
public class SwingProjectorGUI extends JFrame implements ProjectorGUI {
	private static final long serialVersionUID = 3227L;
	private transient Logger logger = LoggerManager.getLogger();
	private transient DrawStrategy strategy = null;

	private transient ProjectorController projectorController = null;
	private transient ProjectorMediator projectorMediator = null;
	private transient ProjectorConfiguration configurationDefault = null;

	private transient SlideShowComponant currentSlide;

	public SwingProjectorGUI(DrawStrategy strategy, ProjectorController projectorController, ProjectorConfiguration configurationDefault, ProjectorMediator context) {
		
		this.strategy = strategy;
		this.projectorController = projectorController;
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

	private void initialize(ProjectorMediator projectorMediator) {
		this.projectorMediator = projectorMediator;
		this.projectorMediator.setMainGUI(this);
		this.projectorMediator.setProjectorController(this.projectorController);
		this.projectorMediator.setAnnotationLineColor(this.configurationDefault.getDefaultColorCode());
		this.projectorMediator.setAnnotationLineWeight(this.configurationDefault.getDefaultLineWeight());
		
		projectorController.registerSlideShowListeners(this);

		initializeContentPane();
	}

	@Override
	public void start(String path) {
		if(this.projectorMediator == null || this.projectorController == null) {
			throw new ProjectorGUIException("Context has not been intialized yet.");
		}
		if(path != null) {
			this.projectorController.openSlideShow(Path.of(path));			
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
				validateParameters(g, projectorMediator);
			
				if(currentSlide != null) {
					strategy.setProjectorConfiguration(configurationDefault);
					strategy.setProjectorController(projectorController);
					strategy.draw(g, this, currentSlide, null, 0, 0);					
				}else {
					this.removeAll();
					super.paintComponent(g);
				} 
			}
		});
	}

	private boolean validateParameters(Graphics g, ProjectorMediator projectorMediator) {
		if (g == null || projectorMediator == null) {
			throw new ProjectorGUIException("Input parameters cannot be NULL.");
		}

		if(this.projectorController == null) {
			throw new ProjectorGUIException("Context has not been properly initialized.");
		}
		
		return (this.projectorController.getSlideShowSize() > 0);
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