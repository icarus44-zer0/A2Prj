package com.mycompany.a3;
/** Represents a Game.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/


import java.util.ArrayList;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.SelectableIconHolder;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.UITimer;
import com.mycompany.command.*;
import com.mycompany.gui.MapViewContainer;
import com.mycompany.gui.ScoreViewContainer;
import com.mycompany.gui.SideMenuItemForm;
import com.mycompany.sfx.ChargeSound;
import com.mycompany.sfx.CrashSound;
import com.mycompany.sfx.DeathSound;
import com.mycompany.sfx.ThemeSound;


public class Game extends Form implements Runnable{
	private MapViewContainer mapViewContainer;
	private ScoreViewContainer scoreViewContainer;
	private Container bottomContainer;
	private Container leftContainer;
	private Container rightContainer;
	private TurnLeftCommand turnLeftCommand;
	private TurnRightCommand turnRightCommand;
	private ChangeStratsCommand changeStratsCommand;
	private AccelerateCommand accelerateCommand;
	private BreakCommand breakCommand;
	private PositionCommand positionCommand;
	private PauseCommand pauseCommand;
	private GameButton turnLeftButton;
	private GameButton turnRightButton;
	private GameButton changeStratsButton;
	private GameButton accelerateButton;
	private GameButton brakeButton;
	private GameButton positionButton;
	private GameButton pauseButton;
	private Toolbar gameToolbar;
	private UITimer timer;
	private HelpCommand helpCommand;
	private ExitGameCommand exitGameCommand;
	private SideMenuItemForm sideMenuItemForm;
	
	private int mapViewContainer_Height;
	private int mapViewContainer_Width;
	
	/*
	 * Controller Class
	 */
	public Game() {		
		timer = new UITimer(this);
		setLayout(new BorderLayout());
		initContiners();
		initToolBar();
		addContainersToForm();
		initLeftTurn();
		initRightTurn();
		initChangeStrategies();
		initAccelerate();
		initBrake();
		initPosition();
		initPause();
		
		this.show();
		
		calcuateGameMapLocation();
		setupGameWorld();
		StartTimer();
		revalidate();
	}
	
	
	public void StartTimer() {
		timer.schedule(20, true, this);
	}
	
	private void setupGameWorld() {
		GameWorld gameWorld = GameWorld.getInstance();
		gameWorld.init(mapViewContainer_Width, mapViewContainer_Height);;
		gameWorld.createSounds();
		gameWorld.addObserver(mapViewContainer); 
		gameWorld.addObserver(scoreViewContainer); 
	}

	private void calcuateGameMapLocation() {
		mapViewContainer_Height = mapViewContainer.getHeight();
		mapViewContainer_Width = mapViewContainer.getWidth();
	}

	/**
	 * 
	 */
	private void initContiners() {
		mapViewContainer = new MapViewContainer(new FlowLayout(Component.CENTER)); 
		scoreViewContainer = new ScoreViewContainer(new FlowLayout(Component.CENTER));	
		bottomContainer = new Container(new FlowLayout(Component.CENTER));
		leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
	}
	
	/**
	 * 
	 */
	private void initLeftTurn() {
		GameWorld gameWorld = GameWorld.getInstance();
		turnLeftCommand = new TurnLeftCommand("Turn Left", gameWorld);
		turnLeftButton = new GameButton(turnLeftCommand);
		leftContainer.add(turnLeftButton);
		addKeyListener('l', turnLeftCommand);
	}
	
	/**
	 * 
	 */
	private void initRightTurn() {
		GameWorld gameWorld = GameWorld.getInstance();
		turnRightCommand = new TurnRightCommand("Turn Right", gameWorld);
		turnRightButton = new GameButton(turnRightCommand);
		rightContainer.add(turnRightButton);
		addKeyListener('r', turnRightCommand);
	}

	/**
	 * 
	 */
	private void initChangeStrategies() {
		GameWorld gameWorld = GameWorld.getInstance();
		changeStratsCommand	= new ChangeStratsCommand("Change Stratagies", gameWorld);
		changeStratsButton = new GameButton(changeStratsCommand);
		leftContainer.add(changeStratsButton);
		addKeyListener('s', turnRightCommand);
		
	}
	
	/**
	 * 
	 */
	private void initAccelerate() {
		GameWorld gameWorld = GameWorld.getInstance();
		accelerateCommand = new AccelerateCommand("Accelerate", gameWorld);
		accelerateButton = new GameButton(accelerateCommand);
		leftContainer.add(accelerateButton);
		addKeyListener('a', accelerateCommand);
	}
	
	/**
	 * 
	 */
	private void initBrake() {
		GameWorld gameWorld = GameWorld.getInstance();
		breakCommand = new BreakCommand("Break", gameWorld);
		brakeButton = new GameButton(breakCommand);
		rightContainer.add(brakeButton);
		addKeyListener('b', breakCommand);
	}
	
	private void initPause() {
		GameWorld gameWorld = GameWorld.getInstance();
		pauseCommand = new PauseCommand("Pause", gameWorld, timer, accelerateButton, accelerateButton, this);
		pauseButton = new GameButton(pauseCommand);
		bottomContainer.add(pauseButton);
	}
	
	private void initPosition() {
		GameWorld gameWorld = GameWorld.getInstance();
		positionCommand = new PositionCommand("Position", gameWorld);
		positionButton = new GameButton(positionCommand);
		positionButton.setEnabled(false);
		bottomContainer.add(positionButton);
	}

	private void enablelisteners() {
		addKeyListener('b', breakCommand);
		addKeyListener('a', accelerateCommand);
		addKeyListener('s', turnRightCommand);
		addKeyListener('r', turnRightCommand);
		addKeyListener('l', turnLeftCommand);
		addKeyListener('x', exitGameCommand);
	}
	
	private void disablelisteners() {
		removeKeyListener('b', breakCommand);
		removeKeyListener('a', accelerateCommand);
		removeKeyListener('s', turnRightCommand);
		removeKeyListener('r', turnRightCommand);
		removeKeyListener('l', turnLeftCommand);
		removeKeyListener('x', exitGameCommand);
	}
	

	/**
	 * 
	 */
	private void initToolBar() {
		gameToolbar = new Toolbar();
		setToolbar(gameToolbar);
		
		sideMenuItemForm = new SideMenuItemForm(gameToolbar);
		
		Label gameLabel = new Label("Keep On Track Game");
		gameToolbar.setTitleComponent(gameLabel);
		gameToolbar.addComponentToSideMenu(sideMenuItemForm);	

		helpCommand = new HelpCommand("Help");
		exitGameCommand = new ExitGameCommand("Exit Game"); 
		
		addKeyListener('x', exitGameCommand);
		
		gameToolbar.addCommandToRightBar(helpCommand);
		gameToolbar.addCommandToOverflowMenu(exitGameCommand);
				
	}

	
	/**
	 * 
	 */
	private void addContainersToForm() {
		this.add(BorderLayout.NORTH,scoreViewContainer);
		this.add(BorderLayout.CENTER,mapViewContainer);
		this.add(BorderLayout.SOUTH,bottomContainer);
		this.add(BorderLayout.WEST,leftContainer);
		this.add(BorderLayout.EAST,rightContainer);
		
	}

	@Override
	public void run() {
		GameWorld gameWorld = GameWorld.getInstance();
		double twentymillis = .02;
		gameWorld.tickGameClock(twentymillis);
		//mapViewContainer.repaint();
	}

	public void pause() {
		timer.cancel();
		positionButton.setEnabled(true);
		disablelisteners();
		GameWorld.getInstance().pauseThemeMusic();
		pauseButton.setText("Play");
		GameWorld.getInstance().setPaused(true);
		accelerateButton.setEnabled(false);
		turnLeftButton.setEnabled(false);
		brakeButton.setEnabled(false);
		changeStratsButton.setEnabled(false);
		turnRightButton.setEnabled(false);
		
		sideMenuItemForm.getAboutCheckbox().setEnabled(false);
		sideMenuItemForm.getAccelerateValueCheckbox().setEnabled(false);
		sideMenuItemForm.getSoundValueCheckBox().setEnabled(false);

	}
	
	public void unpause() {
		timer.schedule(20, true, this);
		positionButton.setEnabled(false);
		enablelisteners();
	
		pauseButton.setText("Pause");
		GameWorld.getInstance().playThemeMusic();
		GameWorld.getInstance().setPaused(false);
		
		accelerateButton.setEnabled(true);
		turnLeftButton.setEnabled(true);
		brakeButton.setEnabled(true);
		changeStratsButton.setEnabled(true);
		turnRightButton.setEnabled(true);
		sideMenuItemForm.getAboutCheckbox().setEnabled(true);
		sideMenuItemForm.getAccelerateValueCheckbox().setEnabled(true);
		sideMenuItemForm.getSoundValueCheckBox().setEnabled(true);
		
	}

}
