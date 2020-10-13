package com.mycompany.a2;
/** Represents a Game.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/


import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.command.*;



public class Game extends Form {
	private GameWorld _gameWorld;
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
	 * new game constructor
	 */
	public Game() {
		
		setLayout(new BorderLayout());
		
		_gameWorld = GameWorld.get_Instance();
		_gameWorld.init();
		
		mapViewContainer = new MapView(); 
		scoreViewContainer = new ScoreView();
		
		_gameWorld.addObserver(mapViewContainer); 
		_gameWorld.addObserver(scoreViewContainer); 
		
		//Instantiate Contianers		
		bottomContainer = new Container(new FlowLayout(Component.CENTER));
		leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		//Instantiate Commands	
		turnLeftCommand = new TurnLeftCommand("Turn Left");
		turnRightCommand = new TurnRightCommand("Turn Right");
		changeStratsCommand	= new ChangeStratsCommand("Change Stratagies");
		accelerateCommand = new AccelerateCommand("Accelerate");
		BrakeCommand = new BreakCommand("Break");
		npcCyborg_ColCommand = new NPC_CollisionCommand("NPC Cyborg Collision");
		eStation_ColCommand = new Estat_CollisionCommand("eStation Collision");
		drone_ColCommand = new Drone_CollisionCommand("Drone Collision");
		base_ColCommand = new Base_CollisionCommand("Base Collision");
		tickGameClockCommand = new TickGameClockCommand("Tick Game Clock");
		
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
				
		//Add Containers to Border Layout 	
		this.add(BorderLayout.NORTH,scoreViewContainer);
		this.add(BorderLayout.CENTER,mapViewContainer);
		this.add(BorderLayout.SOUTH,bottomContainer);
		this.add(BorderLayout.WEST,leftContainer);
		this.add(BorderLayout.EAST,rightContainer);
			
		this.show();
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
						_gameWorld.pCyborg_accelerate();
						break;
					case 'b':
						_gameWorld.pCyborg_brake();
						break;
					case 'l':
						_gameWorld.pCyborg_turnLeft();
						break;
					case 'r':
						_gameWorld.pCyborg_turnRight();
						break;
					case 'c':
						_gameWorld.pCyborg_cyborgCollision();
						break;
					case 'e':
						_gameWorld.pCyborg_eStationCollison();
						break;
					case 'g':
						_gameWorld.pCyborg_droneCollison();
						break;
					case 't':
						_gameWorld.tickGameClock();
						break;
					case 'd':
						_gameWorld.displayGameStatus();
						break;
					case 'm':
						_gameWorld.displayGameMap();
						break;
					case 'x':
						_gameWorld.promptExitGame();
						break;
					case 'y':
						_gameWorld.confirmExitGame();
						break;
					case 'n':
						_gameWorld.declineExitGame();
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
						_gameWorld.pCyborg_BaseCollison(inum);
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
