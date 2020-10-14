package com.mycompany.a2;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.TreeMap;

/** Represents a GameWorld.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/


public class GameWorld extends Observable{
	private static GameWorld _instance = null; 
	private HashMap<String,GameObject> _gameObjectMap;
	
	private final int _WIDTH = 1000;
	private final int _HIEGTH = 1000;
	private int _lives;
	private int _timeElapsed;
	private boolean _exitFlag;

	
	/**
	 * Constructor 
	 */
	private GameWorld() {
		_gameObjectMap = new HashMap<>();
		_lives = 3;
		_timeElapsed = 0;
	}
	
	/**
	 * Gets instance of Gameworld class 
	 * @return GameWorld instance
	 */
	public static GameWorld get_Instance() {
		 if (_instance == null) 
	        { 
			 _instance = new GameWorld(); 
	        } 
	        return _instance; 
	}
	
	/**
	 * initialize game objects 
	 */
	public void init() {
		
		Base base1 = new Base(1);
		Base base2 = new Base(2);
		Base base3 = new Base(3);
		Base base4 = new Base(4);

		Drone drone1 = new Drone();
		Drone drone2 = new Drone();
		Drone drone3 = new Drone();
		Drone drone4 = new Drone();
		
		EnergyStation eStation1 = new EnergyStation();
		EnergyStation eStation2= new EnergyStation();
		EnergyStation eStation3= new EnergyStation();
		EnergyStation eStation4= new EnergyStation();
				
		Cyborg playerCyborg = new Cyborg(base1.get_point());
		Cyborg cyborg1 = new Cyborg();
		
		_gameObjectMap.put("p1Cyborg",playerCyborg);
		_gameObjectMap.put("Cyborg1",cyborg1);
		
		_gameObjectMap.put("Drone1",drone1);
		_gameObjectMap.put("Drone2",drone2);
		_gameObjectMap.put("Drone3",drone3);
		_gameObjectMap.put("Drone4",drone4);
		
		_gameObjectMap.put("Base1",base1);
		_gameObjectMap.put("Base2",base2);
		_gameObjectMap.put("Base3",base3);
		_gameObjectMap.put("Base4",base4);
		
		_gameObjectMap.put("eStation1",eStation1);
		_gameObjectMap.put("eStation2",eStation2);
		_gameObjectMap.put("eStation3",eStation3);
		_gameObjectMap.put("eStation4",eStation4);
	}
	
	
	/**
	 * Getter for the lives of a the player cyborg.
	 * @return the lives of a the player cyborg.
	 */
	public int get_lives() {
		return _lives;
	}

	/**
	 * Setter for the lives of a the player cyborg.
	 * @param retunrs the lives of a the player cyborg.
	 */
	public void set_lives(int _lives) {
		this._lives = _lives;
	}

	/**
	 * Getter for the width of a the gameworld.
	 * @return the width of a the gameworld.
	 */
	public int get_width() {
		return _WIDTH;
	}

	/**
	 * Getter for the heigth of a the gameworld.
	 * @return the heigth of a the gameworld.
	 */
	public int get_hieght() {
		return _HIEGTH;
	}
	
	/**
	 * @return the _gameObjects
	 */
	public HashMap<String, GameObject> get_gameObjectMap() {
		return _gameObjectMap;
	}


	/**
	 * @return the _timeElapsed
	 */
	public int get_timeElapsed() {
		return _timeElapsed;
	}


	/*
	 * keyboard input "a"
	 * causes the player Cyborg to incriment speed by one.
	 */
	public void pCyborg_accelerate() {
		Cyborg refCyborg = (Cyborg) _gameObjectMap.get("p1Cyborg");
		int curSpeed = refCyborg.get_speed();
		curSpeed+=1;
		refCyborg.set_speed(curSpeed);
		setChanged();
		notifyObservers();
		
		
		System.out.println("p1Cyborg"+ ": " + refCyborg);
		System.out.println();
	}
	
	/*
	 * keyboard input "b"
	 * causes the player cybog to decrease spped by one.
	 */
	public void pCyborg_brake() {
		Cyborg refCyborg = (Cyborg) _gameObjectMap.get("p1Cyborg");
		int curSpeed = refCyborg.get_speed();
		refCyborg.set_speed(curSpeed-1);
		setChanged();
		notifyObservers();
		
		System.out.println("p1Cyborg"+ ": " + refCyborg);
		System.out.println();
	}

	/*
	 * keyboard input "l"
	 * causes the player cybog to turn left by 5 degrees.
	 */
	public void pCyborg_turnLeft() {
		Cyborg refCyborg = (Cyborg) _gameObjectMap.get("p1Cyborg");
		refCyborg.set_steeringDirection(-5);
		setChanged();
		notifyObservers();
		
		System.out.println("p1Cyborg"+ ": " + refCyborg);
		System.out.println();
	}

	/*
	 * keyboard input "r"
	 * causes the player cybog to turn right by 5 degrees.
	 */
	public void pCyborg_turnRight() {
		Cyborg refCyborg = (Cyborg) _gameObjectMap.get("p1Cyborg");
		refCyborg.set_steeringDirection(5);
		setChanged();
		notifyObservers();
		
		System.out.println("p1Cyborg"+ ": " + refCyborg);
		System.out.println();
	}
	
	/*
	 * Keyboard input "c"
	 * Simulates collision with another cyborg
	 * Causes damage to the player cyborg 
	 * based off size and speed of the other cyborg. 
	 */
	public void pCyborg_cyborgCollision() {
		
		String str= "Cyborg1";
		
		System.out.println();
		System.out.println("Player Cyborg has collided with " +  str);
		
		Cyborg refNPCyborg = (Cyborg) _gameObjectMap.get(str);
		Cyborg refCyborg = (Cyborg) _gameObjectMap.get("p1Cyborg");
	
		refCyborg.set_point(refNPCyborg.get_point());			
		refCyborg.fadeColor();
		int damage = 10 + 10*refNPCyborg.get_size()/100 + 10 *refCyborg.get_speed()/100;
	
		int curDamg = refCyborg.get_damageLevel();
		refCyborg.set_damageLevel(curDamg+damage);
		refCyborg.set_speed(refCyborg.get_speed());
		
		refNPCyborg.fadeColor();
		int damage2 = 10 + 10*refNPCyborg.get_size()/100 + 10 *refNPCyborg.get_speed()/100;
	
		int curDamg2 = refNPCyborg.get_damageLevel();
		refNPCyborg.set_damageLevel(curDamg2+damage2);
		
		checkCyborgState(refCyborg, refCyborg.toString());
		
		refNPCyborg.set_speed(refNPCyborg.get_speed());
		
		setChanged();
		notifyObservers();
		
		System.out.println("p1Cyborg"+ ": " + refCyborg);
		System.out.println();
	}

	
	/*
	 * keyboard input "1-9"
	 * Simulates collision between player cyborg and base.
	 * @param takes in the base the player cyborg is to move to.
	 */
	public void pCyborg_BaseCollison(int num) {
		String str= "Base";
		str = str+num;
		
		Cyborg refCyborg = (Cyborg) _gameObjectMap.get("p1Cyborg");
		Base refBase = null;
		
		if ((_gameObjectMap.get(str) == null)) {
			System.out.println();
			System.out.println("No Base at that location ");
			return;
		}
		
		System.out.println();
		System.out.println("Player Cyborg has collided with " +  str);
		
		refBase = (Base) _gameObjectMap.get(str);
		if ((refBase.get_sequenceNumber() - 1) == refCyborg.get_lastBaseReached()) {
			refCyborg.set_lastBaseReached(refBase.get_sequenceNumber());
		}

		refCyborg.set_point(refBase.get_point());
		
		setChanged();
		notifyObservers();
	
		System.out.println("p1Cyborg"+ ": " + refCyborg);
		System.out.println();
	}
	
	
	/*
	 * keyboard input "e"
	 * Simulates collision between player cyborg and energy station
	 * adds energy to the cyborg.
	 */
	public void pCyborg_eStationCollison() {
		
		Random random = new Random();
		int num = random.nextInt(4 - 1 + 1) + 1;
		int out = 0; 
		String str= "eStation";
		str = str+num;
		
		
		EnergyStation refStation = (EnergyStation) _gameObjectMap.get(str);
		Cyborg refCyborg = (Cyborg) _gameObjectMap.get("p1Cyborg");
		
		while (refStation.get_capacity()==0) {
			num = random.nextInt(4 - 1 + 1) + 1;
			str= "eStation";
			str = str+num;
			refStation = (EnergyStation) _gameObjectMap.get(str);
			out++;
			if(out > 10) {
				System.out.println("All eStations Empty");
				return;
			}
		}
		
		System.out.println();
		System.out.println("Player Cyborg has collided with " +  str);
		
		refCyborg.set_point(refStation.get_point());
		int eCpacity = refStation.get_capacity();
		
		refStation.set_capacity(0);
		
		int cyborgEng = refCyborg.get_energyLevel();
		refCyborg.set_energyLevel(0);
		
		refCyborg.set_energyLevel(eCpacity + cyborgEng);
		
		setChanged();
		notifyObservers();
		
		System.out.println("p1Cyborg"+ ": " + refCyborg);
		System.out.println();
	}

	/*
	 * keyboard input "g"
	 * Simulates collision between player cyborg and drone 
	 * Causes damage to the player cyborg 
	 * based off size and speed of the drone. 
	 */
	public void pCyborg_droneCollison() {
		
		Random random = new Random();
		int num = random.nextInt(4 - 1 + 1) + 1;
		String str= "Drone";
		str = str+num;
		
		System.out.println();
		System.out.println("Player Cyborg has collided with " +  str);
		
		Drone refDrone = (Drone) _gameObjectMap.get(str);
		Cyborg refCyborg = (Cyborg) _gameObjectMap.get("p1Cyborg");
		
		refCyborg.set_point(refDrone.get_point());			
		refCyborg.fadeColor();
		int damage = 5 + 10*refDrone.get_size()/100 + 10 *refCyborg.get_speed()/100;
	
		int curDamg = refCyborg.get_damageLevel();
		refCyborg.set_damageLevel(curDamg+damage);
		
		checkCyborgState(refCyborg, refCyborg.toString());
		
		refCyborg.set_speed(refCyborg.get_speed());
		
		setChanged();
		notifyObservers();
		
		System.out.println("p1Cyborg"+ ": " + refCyborg);
		System.out.println();
	}

	
	/*
	 * keyboard input "t"
	 * advances the gameclock forward
	 * causes all movalble objects to move based off heading and speed.
	 */
	public void tickGameClock() {
		_timeElapsed++;	
		
		System.out.println("Time Elapesed " + _timeElapsed);
		Cyborg refCyborg = (Cyborg) _gameObjectMap.get("p1Cyborg");
		String str = refCyborg.toString();
		
		if (refCyborg.get_damageLevel() >= refCyborg.get_MAX_DAMAGE_LEVEL()) {
			_lives--;
			if(_lives>=0) {
				int r=170, g=169, b=173; // initial color 
				refCyborg.set_damageLevel(0);
				refCyborg.set_color(r, g, b);
				refCyborg.set_energyLevel(100);
			}
		}
		
		else if (refCyborg.get_energyLevel() <=0 ) {
			_lives--;
			if(_lives>=0) {
				int r=170, g=169, b=173; // initial color 
				refCyborg.set_damageLevel(0);
				refCyborg.set_color(r, g, b);
				refCyborg.set_energyLevel(100);
			}
		}

		if (_lives < 0) {
			System.out.println("CYBORG HAS NO REMAINING LIVES");
			System.out.println("GAMEOVER");
			System.out.println("FINAL STATS");
			System.out.println("PLAYER CYBORG");
			System.out.println(str);
			System.exit(42);
			return;
		}
		
		
		else {
			for (String key : _gameObjectMap.keySet()) {
				if(_gameObjectMap.get(key) instanceof Movable) {
					Movable mObj = (Movable)_gameObjectMap.get(key);
					mObj.move();
				}
			}	
		}
		
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Checks to see if the player cyborg is in a dead state 
	 * has no energy or has reached max damage
	 * @param refCyborg the player cyborg to check
	 * @param str the player cyborg toString used to print final cyborg state.  
	 */
	public void checkCyborgState(Cyborg refCyborg, String str) {
		if (refCyborg.get_damageLevel() >= refCyborg.get_MAX_DAMAGE_LEVEL()) {
			_lives--;
			if(_lives>=0) {
				int r=170, g=169, b=173; // initial color 
				refCyborg.set_damageLevel(0);
				refCyborg.set_color(r, g, b);
				refCyborg.set_energyLevel(100);
			}
		}

		if (_lives < 0) {
			System.out.println("CYBORG HAS NO REMAINING LIVES");
			System.out.println("GAMEOVER");
			System.out.println("FINAL STATS");
			System.out.println("PLAYER CYBORG");
			System.out.println(str);
			System.exit(42);
			return;
		}
		setChanged();
		notifyObservers();
	}
	
	
	/*
	 * keyboard input "d"
	 * displays the current status of the player cyborg.
	 */
	@Deprecated
	public void displayGameStatus() {
		System.out.println();
		Cyborg refCyborg = (Cyborg) _gameObjectMap.get("p1Cyborg");
		
		double xVal = refCyborg.get_point().getX();
		double rxVal = Math.round(xVal*10.0)/10.0;
		double yVal = refCyborg.get_point().getY();
		double ryVal = Math.round(yVal*10.0)/10.0;
		String Locaction = "Location= " + "(" + rxVal + "," + ryVal + "), ";
		
		System.out.println("p1Cyborg");
		System.out.println("Lives: " + _lives);
		System.out.println("Time Elapsed: "+ _timeElapsed);
		System.out.println("Player Cyborg: ");
		System.out.println("Last Base Reached: " + refCyborg.get_lastBaseReached());
		System.out.println("Energy Level: " + refCyborg.get_energyLevel());
		System.out.println("Damage Level: " + refCyborg.get_damageLevel());
		System.out.println("Location: " + Locaction);
		System.out.println();
	}
	
	/*
	 * Keybaord input "m"
	 * prints the values of all of the GameObjects to the console.
	 */
	@Deprecated
	public void displayGameMap() {
		System.out.println();
		for (String key : _gameObjectMap.keySet()) {
			System.out.println(key + ": " + _gameObjectMap.get(key));
		}
		System.out.println();
	}
	

	/*
	 * Keyboard input "x" d
	 * prompts the user to the exit the game. 
	 */
	public void promptExitGame() {
		System.out.println();
		System.out.println("Would you like to end the game");
		System.out.println("y: yes");
		System.out.println("n: no");
		System.out.println();
		this._exitFlag = true;
	}
	
	
	/*
	 * Keyboard input "y"
	 * confirms the user to exit the game.
	 */
	public void confirmExitGame() {
		if (this._exitFlag == true) {
		System.out.println("\n \n \n \n \n \nThe Enrichment Center is committed to the well being of all participants.");
		System.exit(42);
		}
	}
	

	/*
	 * Keyboard input "n"
	 * confirms to decline the user exiting the game. 
	 */
	public void declineExitGame() {
		this._exitFlag = false;
	}
		
}