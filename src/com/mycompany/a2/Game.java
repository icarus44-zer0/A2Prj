package com.mycompany.a2;
/** Represents a Game.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/


import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ActionSource;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionEvent.Type;

import java.lang.String;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.EventDispatcher;
import com.mycompany.command.*;



public class Game extends Form {
	private GameWorld gameWorld;
	private MapView mapViewContainer;
	private ScoreView scoreViewContainer;
	private Container bottomContainer;
	private Container leftContainer;
	private Container rightContainer;
	private TurnLeftCommand turnLeftCommand;
	private TurnRightCommand turnRightCommand;
	private ChangeStratsCommand changeStratsCommand;
	private AccelerateCommand accelerateCommand;
	private BreakCommand BrakeCommand;
	private NPC_CollisionCommand npcCyborg_ColCommand;
	private Estat_CollisionCommand eStation_ColCommand;
	private Drone_CollisionCommand drone_ColCommand;
	private Base_CollisionCommand base_ColCommand;
	private TickGameClockCommand tickGameClockCommand;
	private GameButton turnLeft_Button;
	private GameButton turnRight_Button;
	private GameButton changeStrats_Button;
	private GameButton accelerate_Button;
	private GameButton brake_Button;
	private GameButton npc_Collision_Button;
	private GameButton drone_Collision_Button;
	private GameButton eStation_Collision_Button;
	private GameButton base_Collision_Button;
	private GameButton tickGameClock_Button;
	
	
	/*
	 * Controller Class
	 */
	public Game() {
		
		setLayout(new BorderLayout());
		
		gameWorld = GameWorld.get_Instance();
		gameWorld.init();
		
				
		mapViewContainer = new MapView(new FlowLayout(Component.CENTER)); 
		scoreViewContainer = new ScoreView(new FlowLayout(Component.CENTER));
		
		gameWorld.addObserver(mapViewContainer); 
		gameWorld.addObserver(scoreViewContainer); 
		
		//Instantiate Contianers		
		bottomContainer = new Container(new FlowLayout(Component.CENTER));
		leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		//Instantiate Commands	
		turnLeftCommand = new TurnLeftCommand("Turn Left", gameWorld);
		turnRightCommand = new TurnRightCommand("Turn Right", gameWorld);
		changeStratsCommand	= new ChangeStratsCommand("Change Stratagies", gameWorld);
		accelerateCommand = new AccelerateCommand("Accelerate", gameWorld);
		BrakeCommand = new BreakCommand("Break", gameWorld);
		npcCyborg_ColCommand = new NPC_CollisionCommand("NPC Cyborg Collision", gameWorld);
		eStation_ColCommand = new Estat_CollisionCommand("eStation Collision", gameWorld);
		drone_ColCommand = new Drone_CollisionCommand("Drone Collision", gameWorld);
		base_ColCommand = new Base_CollisionCommand("Base Collision", gameWorld);
		tickGameClockCommand = new TickGameClockCommand("Tick Game Clock", gameWorld);
		
		//Instantiate GameButtons		
		turnLeft_Button = new GameButton(turnLeftCommand);
		turnRight_Button = new GameButton(turnRightCommand);
		changeStrats_Button = new GameButton(changeStratsCommand);
		accelerate_Button = new GameButton(accelerateCommand);
		brake_Button = new GameButton(BrakeCommand);
		npc_Collision_Button = new GameButton(npcCyborg_ColCommand);
		drone_Collision_Button = new GameButton(eStation_ColCommand);
		eStation_Collision_Button = new GameButton(drone_ColCommand);
		base_Collision_Button = new GameButton(base_ColCommand);
		tickGameClock_Button = new GameButton(tickGameClockCommand);
					
		//Add Buttons to Form
		leftContainer.add(accelerate_Button);
		leftContainer.add(turnLeft_Button);
		leftContainer.add(changeStrats_Button);
		bottomContainer.add(npc_Collision_Button);
		bottomContainer.add(base_Collision_Button);
		bottomContainer.add(eStation_Collision_Button);
		bottomContainer.add(drone_Collision_Button);
		bottomContainer.add(tickGameClock_Button);
		rightContainer.add(brake_Button);
		rightContainer.add(turnRight_Button);
		
		//Add Keylisteners
		addKeyListener('l', turnLeftCommand);
		addKeyListener('r', turnRightCommand);
		addKeyListener('a', accelerateCommand);
		addKeyListener('b', BrakeCommand);
		addKeyListener('d', drone_ColCommand);
		addKeyListener('g', drone_ColCommand);
		addKeyListener('e', eStation_ColCommand);
		addKeyListener('c', npcCyborg_ColCommand);
		addKeyListener('t', tickGameClockCommand);
		
		addKeyListener('1', base_ColCommand);
		addKeyListener('2', base_ColCommand);
		addKeyListener('3', base_ColCommand);
		addKeyListener('4', base_ColCommand);
		addKeyListener('5', base_ColCommand);
		addKeyListener('6', base_ColCommand);
		addKeyListener('7', base_ColCommand);
		addKeyListener('8', base_ColCommand);
		addKeyListener('9', base_ColCommand);
		
		//Add Containers to Border Layout 	

		this.add(BorderLayout.NORTH,scoreViewContainer);
		this.add(BorderLayout.CENTER,mapViewContainer);
		this.add(BorderLayout.SOUTH,bottomContainer);
		this.add(BorderLayout.WEST,leftContainer);
		this.add(BorderLayout.EAST,rightContainer);
		//this.add(BorderLayout.CENTER_BEHAVIOR_TOTAL_BELOW,mainContainer);
		this.show();
		
		
	}
	
	/**
	 * Handle the keyboard events for bases.
	 */
	public void keyTyped(ActionEvent event) {
		int c = event.getKeyEvent();
		System.out.println(c);
	}
	
	
	/*
	 * takes users input from TextFeilds and determines game actions accordingly
	 * Deprecated as of V2
	 */
	@Deprecated
	private void play() {
		Label myLabel = new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();
		

		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				
				if (sCommand.length() != 0)
					switch (sCommand.charAt(0)) {
					case 'a':
						gameWorld.pCyborg_accelerate();
						break;
					case 'b':
						gameWorld.pCyborg_brake();
						break;
					case 'l':
						gameWorld.pCyborg_turnLeft();
						break;
					case 'r':
						gameWorld.pCyborg_turnRight();
						break;
					case 'c':
						gameWorld.pCyborg_cyborgCollision();
						break;
					case 'e':
						gameWorld.pCyborg_eStationCollison();
						break;
					case 'g':
						gameWorld.pCyborg_droneCollison();
						break;
					case 't':
						gameWorld.tickGameClock();
						break;
					case 'd':
						gameWorld.displayGameStatus();
						break;
					case 'm':
						gameWorld.displayGameMap();
						break;
					case 'x':
						gameWorld.promptExitGame();
						break;
					case 'y':
						gameWorld.confirmExitGame();
						break;
					case 'n':
						gameWorld.declineExitGame();
						break;
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
						String str = String.valueOf(sCommand.charAt(0));
						int inum = Integer.valueOf(str);
						gameWorld.pCyborg_BaseCollison(inum);
						break;
					default:
						System.out.println("no active keybinding");
						break;
					}
				} 
			} 
		); 
	}
}
