package com.mycompany.a3;
/** Represents a Game.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/


import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.UITimer;
import com.mycompany.command.*;
import com.mycompany.gui.MapViewContainer;
import com.mycompany.gui.ScoreViewContainer;
import com.mycompany.gui.SideMenuItemForm;


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
	private BreakCommand BrakeCommand;
	private NPCCollisionCommand NPCCyborgColCommand;
	private EstatCollisionCommand eStationColCommand;
	private DroneCollisionCommand droneColCommand;
	private BaseCollisionCommand baseColCommand;
	private TickGameClockCommand tickGameClockCommand;
	private GameButton turnLeftButton;
	private GameButton turnRightButton;
	private GameButton changeStratsButton;
	private GameButton accelerateButton;
	private GameButton brakeButton;
	private GameButton NPCCollisionButton;
	private GameButton droneCollisionButton;
	private GameButton eStationCollisionButton;
	private GameButton baseCollisionButton;
	private GameButton tickGameClockButton;
	private Toolbar gameToolbar;
	private UITimer timer;
	
	
	/*
	 * Controller Class
	 */
	public Game() {		
		setLayout(new BorderLayout());
		initContiners();
		initToolBar();
		addContainersToForm();
		initLeftTurn();
		initRightTurn();
		initChangeStrategies();
		initAccelerate();
		initBrake();
		initCollisionCyborg();
		initCollisionBase();
		initCollisioneStat();
		initCollisionDrone();
		initGameClock();
		this.show();
		
		GameWorld gameWorld = GameWorld.getInstance();
		gameWorld.init(mapViewContainer.getMapWidth(),mapViewContainer.getMapHeight());
		gameWorld.addObserver(mapViewContainer); 
		gameWorld.addObserver(scoreViewContainer); 
		timer = new UITimer(this);
		timer.schedule(20, true, this);
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
		BrakeCommand = new BreakCommand("Break", gameWorld);
		brakeButton = new GameButton(BrakeCommand);
		rightContainer.add(brakeButton);
		addKeyListener('b', BrakeCommand);
	}

	/**
	 * 
	 */
	private void initCollisionCyborg() {
		GameWorld gameWorld = GameWorld.getInstance();
		NPCCyborgColCommand = new NPCCollisionCommand("NPC Cyborg Collision", gameWorld);
		NPCCollisionButton = new GameButton(NPCCyborgColCommand);
		bottomContainer.add(NPCCollisionButton);
		addKeyListener('c', NPCCyborgColCommand);
	}

	/**
	 * 
	 */
	private void initCollisionBase() {
		GameWorld gameWorld = GameWorld.getInstance();
		baseColCommand = new BaseCollisionCommand("Base Collision", gameWorld);
		baseCollisionButton = new GameButton(baseColCommand);
		bottomContainer.add(baseCollisionButton);
		addKeyListener('1', baseColCommand);
		addKeyListener('2', baseColCommand);
		addKeyListener('3', baseColCommand);
		addKeyListener('4', baseColCommand);
		addKeyListener('5', baseColCommand);
		addKeyListener('6', baseColCommand);
		addKeyListener('7', baseColCommand);
		addKeyListener('8', baseColCommand);
		addKeyListener('9', baseColCommand);
	}
	
	/**
	 * 
	 */
	private void initCollisioneStat() {
		GameWorld gameWorld = GameWorld.getInstance();
		eStationColCommand = new EstatCollisionCommand("eStation Collision", gameWorld);
		eStationCollisionButton = new GameButton(eStationColCommand);
		bottomContainer.add(eStationCollisionButton);
		addKeyListener('e', eStationColCommand);
	}

	/**
	 * 
	 */
	private void initCollisionDrone() {
		GameWorld gameWorld = GameWorld.getInstance();
		droneColCommand = new DroneCollisionCommand("Drone Collision", gameWorld);
		droneCollisionButton = new GameButton(droneColCommand);
		bottomContainer.add(droneCollisionButton);
		addKeyListener('g', droneColCommand);
	}

	/**
	 * 
	 */
	private void initGameClock() {
		GameWorld gameWorld = GameWorld.getInstance();
		tickGameClockCommand = new TickGameClockCommand("Tick Game Clock", gameWorld);
		tickGameClockButton = new GameButton(tickGameClockCommand);
		bottomContainer.add(tickGameClockButton);
		addKeyListener('t', tickGameClockCommand);
	}

	/**
	 * 
	 */
	private void initToolBar() {
		gameToolbar = new Toolbar();
		setToolbar(gameToolbar);
		
		Label gameLabel = new Label("Keep On Track Game");
		gameToolbar.setTitleComponent(gameLabel);
		gameToolbar.addComponentToSideMenu(new SideMenuItemForm(gameToolbar));	

		HelpCommand helpCommand = new HelpCommand("Help");
		ExitGameCommand exitGameCommand = new ExitGameCommand("Exit Game"); 
		
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
		gameWorld.tickGameClock();
		mapViewContainer.repaint();
	}
}
