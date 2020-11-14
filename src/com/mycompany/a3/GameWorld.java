package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.mycompany.gui.MapViewContainer;

/**
 * Represents a GameWorld.
 * 
 * @author Josh Poe
 * @version 1.0
 * @since 202-09-28
 */
public class GameWorld extends Observable {
	private static GameWorld instance = null;

	private int gameWidth;
	private int gameHeight;
	private int lives;
	private int timeElapsed;
	private boolean exitFlag;
	private boolean soundFlag;
	private boolean strategyflag;
	private int baseSequenceNumber;
	private int baseCount;
	private int npcCyborgCount;
	private int droneCount;
	private int eStationCount;
	private int playerCount;
	private Point intiBasePoint;
	private PlayerCyborg playerCyborg;
	private GameObjectCollection gameObjectCollection;

	/**
	 * Constructor
	 */
	private GameWorld() {
		gameObjectCollection = new GameObjectCollection();
		lives = 3;
		timeElapsed = 0;
		exitFlag = false;
		soundFlag = false;
		strategyflag = false;
		baseSequenceNumber = 1;
		baseCount = 4;
		npcCyborgCount = 3;
		droneCount = 4;
		eStationCount = 4;
		playerCount = 1;
	}

	/**
	 * Gets instance of Gameworld class
	 * 
	 * @return GameWorld instance
	 */
	public static GameWorld getInstance() {
		if (instance == null) {
			instance = new GameWorld();
		}
		return instance;
	}

	/**
	 * initialize game objects
	 */
	public void init(int gameWidth, int gameHeight) {
		
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;

		for (int i = 0; i < baseCount; i++) {
			Base base = initBase();
			if (i == 0)
				intiBasePoint = new Point(base.getpoint().getX(), base.getpoint().getY());
			;
			gameObjectCollection.add(base);
		}

		for (int i = 0; i < playerCount; i++) {
			playerCyborg = initPlayerCyborg();
			gameObjectCollection.add(playerCyborg);
		}

		for (int i = 0; i < eStationCount; i++) {
			EnergyStation eStation = initEnergyStation();
			gameObjectCollection.add(eStation);
		}

		for (int i = 0; i < droneCount; i++) {
			Drone drone = initDrone();
			gameObjectCollection.add(drone);
		}

		for (int i = 0; i < npcCyborgCount; i++) {
			NPCCyborg npcCyborg = initNPCyborg();
			assignStrategy(npcCyborg);
			gameObjectCollection.add(npcCyborg);
		}
	}

	/**
	 * 
	 * @param npcCyborg
	 */
	private void assignStrategy(NPCCyborg npcCyborg) {
		if (strategyflag == false) {
			npcCyborg.setStrategy(new NPCAttackStratagy(npcCyborg));
			strategyflag = true;
		} else {
			npcCyborg.setStrategy(new NPCNextBaseStratagy(npcCyborg));
		}
	}
	

	/**
	 * 
	 * @return
	 */
	private PlayerCyborg initPlayerCyborg() {
		Random random = new Random();
		int r = 26, g = 188, b = 212; // Sea Blue

		int energyLevel = 100;
		int energyConsumptionRate = 10;
		int damageLevel = 0;
		int maxDamageLevel = 100;
		int lastBaseReached = 1;
		int maxBaseReached = 1;
		int steeringDirection = 0;
		int size = 50;
		Point point = intiBasePoint;
		int heading = random.nextInt(359) + 1;
		int speed = random.nextInt(10 - 5) + 5;
		int color = ColorUtil.rgb(r, g, b);

		return PlayerCyborg.getInstance(energyLevel, energyConsumptionRate, damageLevel, maxDamageLevel,
				lastBaseReached, maxBaseReached, steeringDirection, heading, speed, size, point, color);
	}

	
	/**
	 * 
	 * @return
	 */
	private NPCCyborg initNPCyborg() {
		Random random = new Random();
		Point point = setInitialPoint();

		float min = 50f;
		float max = 200f;
		float deltaX = min + random.nextFloat() * (max - min);
		float deltaY = min + random.nextFloat() * (max - min);

		point.setX(point.getX() + deltaX);
		point.setY(point.getY() + deltaY);

		int r = 111, g = 23, b = 231; // Royal Purple

		int energyLevel = 10000;
		int energyConsumptionRate = 10;
		int damageLevel = 0;
		int maxDamageLevel = 10000;
		int lastBaseReached = 1;
		int maxBaseReached = 1;
		int steeringDirection = 0;
		int size = 50;
		int heading = random.nextInt(359) + 1;
		int speed = random.nextInt(10 - 5) + 5;
		int color = ColorUtil.rgb(r, g, b);

		return new NPCCyborg(energyLevel, energyConsumptionRate, damageLevel, maxDamageLevel, lastBaseReached,
				maxBaseReached, steeringDirection, heading, speed, size, point, color);
	}

	/**
	 * 
	 * @return
	 */
	private Drone initDrone() {
		Random random = new Random();
		Point point = setInitialPoint();
		int r = 219, g = 22, b = 224; // HOT PINK
		int damageLevel = 0;
		int size = 50;
		int heading = random.nextInt(359) + 1;
		int speed = random.nextInt(10 - 5) + 5;
		int color = ColorUtil.rgb(r, g, b);
		return new Drone(damageLevel, heading, speed, size, point, color);
	}

	/**
	 * 
	 * @return
	 */
	private Base initBase() {
		Point point = setInitialPoint();
		int r = 101, g = 247, b = 113; // Bright Green
		int size = 50;
		int color = ColorUtil.rgb(r, g, b);
		return new Base(baseSequenceNumber++, size, point, color);
	}

	/**
	 * 
	 * @return
	 */
	private EnergyStation initEnergyStation() {
		Random random = new Random();
		Point point = setInitialPoint();
		int r = 180, g = 90, b = 11; // Burnt Orange
		int size = random.nextInt(40 + 1) + 10;
		int color = ColorUtil.rgb(r, g, b);
		int capacity = size * random.nextInt(3 + 1) + 1;
		return new EnergyStation(capacity, size, point, color);
	}

	private Point setInitialPoint() {
		Random random = new Random();
		float min = 0f;
		float maxH = gameHeight;
		float maxW = gameWidth;
		float x = min + random.nextFloat() * (maxH - min);
		float y = min + random.nextFloat() * (maxW - min);
		return new Point(x, y);
	}

	/**
	 * Getter for the lives of a the player cyborg.
	 * 
	 * @return the lives of a the player cyborg.
	 */
	public int getlives() {
		return lives;
	}

	/**
	 * Setter for the lives of a the player cyborg.
	 * 
	 * @param retunrs the lives of a the player cyborg.
	 */
	public void setlives(int lives) {
		this.lives = lives;
	}

	/**
	 * @return the gameWidth
	 */
	public int getGameWidth() {
		return gameWidth;
	}

	/**
	 * @param gameWidth the gameWidth to set
	 */
	public void setGameWidth(int gameWidth) {
		this.gameWidth = gameWidth;
	}

	/**
	 * @return the gameHeight
	 */
	public int getGameHeight() {
		return gameHeight;
	}

	/**
	 * @param gameHeight the gameHeight to set
	 */
	public void setGameHeight(int gameHeight) {
		this.gameHeight = gameHeight;
	}

	/**
	 * @return the timeElapsed
	 */
	public int gettimeElapsed() {
		return timeElapsed;
	}

	/**
	 * @return the soundFlag
	 */
	public boolean getSoundFlag() {
		return soundFlag;
	}

	/**
	 * @return the soundFlag
	 */
	public String getSoundFlagStatus() {
		return soundFlag == true ? "ON" : "OFF";
	}

	/**
	 * @param soundFlag the soundFlag to set
	 */
	public void setSoundFlag(boolean soundFlag) {
		this.soundFlag = soundFlag;
		setChanged();
		notifyObservers();
	}

	/**
	 * 
	 * @return
	 */
	public GameObjectCollection getGameObjectCollection() {
		return gameObjectCollection;
	}

	/**
	 * 
	 */
	public void changeNPCStrategy() {
		IIterator iterator = gameObjectCollection.getIterator();
		while (iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if(gameObject instanceof NPCCyborg) {
				NPCCyborg npcCyborg = (NPCCyborg)gameObject;
				if(npcCyborg.getStrategy() instanceof NPCNextBaseStratagy){
					npcCyborg.setStrategy(new NPCAttackStratagy(npcCyborg));
					npcCyborg.setmaxBaseReached(npcCyborg.getmaxBaseReached()+1);
				}else {
					npcCyborg.setStrategy(new NPCNextBaseStratagy(npcCyborg));
					npcCyborg.setmaxBaseReached(npcCyborg.getmaxBaseReached()+1);
				}
			}
		}
		checkNPCValues();
		setChanged();
		notifyObservers();
	}
	
	
	/*
	 * keyboard input "a" causes the player Cyborg to incriment speed by one.
	 */
	public void pCyborgaccelerate() {
		int curSpeed = playerCyborg.getspeed();
		curSpeed += 1;
		playerCyborg.setspeed(curSpeed);
		System.out.println("Player Cyborg" + ": " + playerCyborg);
		setChanged();
		notifyObservers();
	}

	/*
	 * keyboard input "b" causes the player cybog to decrease spped by one.
	 */
	public void pCyborgbrake() {
		int curSpeed = playerCyborg.getspeed();
		playerCyborg.setspeed(curSpeed - 1);
		System.out.println("Player Cyborg" + ": " + playerCyborg);
		setChanged();
		notifyObservers();
	}

	/*
	 * keyboard input "l" causes the player cybog to turn left by 5 degrees.
	 */
	public void pCyborgturnLeft() {
		playerCyborg.setsteeringDirection(-5);
		System.out.println("Player Cyborg" + ": " + playerCyborg);
		setChanged();
		notifyObservers();
	}

	/*
	 * keyboard input "r" causes the player cybog to turn right by 5 degrees.
	 */
	public void pCyborgturnRight() {
		playerCyborg.setsteeringDirection(5);
		System.out.println("Player Cyborg" + ": " + playerCyborg);
		setChanged();
		notifyObservers();
	}

	/*
	 * Keyboard input "c" Simulates collision with another cyborg Causes damage to
	 * the player cyborg based off size and speed of the other cyborg.
	 */
	public void pCyborgcyborgCollision(NPCCyborg npcCyborg) {
		Cyborg refNPCyborg = null;
		
		if (npcCyborg != null) {
			refNPCyborg = npcCyborg;
		}
		else {
			IIterator iterator = gameObjectCollection.getIterator();
			while (iterator.hasNext()) {
				GameObject gameObject = (GameObject) iterator.getNext();
				if (gameObject instanceof NPCCyborg) {
					refNPCyborg = (NPCCyborg) gameObject;
					break;
				}
			}
		}
		
		int damageTaken = 10 + 10 * refNPCyborg.getsize() / 100 + 10 * playerCyborg.getspeed() / 100;
		int currentDamage = playerCyborg.getdamageLevel();
		int npcDamageTaken = 10 + 10 * refNPCyborg.getsize() / 100 + 10 * refNPCyborg.getspeed() / 100;
		int npcCurrrentDamg = refNPCyborg.getdamageLevel();

		playerCyborg.setdamageLevel(currentDamage + damageTaken);
		playerCyborg.setspeed(playerCyborg.getspeed());
		playerCyborg.fadeColor();
		playerCyborg.setpoint(refNPCyborg.getpoint());

		refNPCyborg.setdamageLevel(npcCurrrentDamg + npcDamageTaken);
		refNPCyborg.setspeed(refNPCyborg.getspeed()); // TODO create update speed method
		refNPCyborg.fadeColor();

		checkCyborgState();

		System.out.println("Player Cyborg has collided with NPC Cyborg");
		System.out.println("Player Cyborg" + ": " + playerCyborg);
		setChanged();
		notifyObservers();
	}

	/*
	 * keyboard input "1-9" Simulates collision between player cyborg and base.
	 * 
	 * @param takes in the base the player cyborg is to move to.
	 */
	public void pCyborgBaseCollison(int sequenceNumber) {
		Base refBase = null;

		IIterator iterator = gameObjectCollection.getIterator();
		while (iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if (gameObject instanceof Base) {
				refBase = (Base) gameObject;
				if (refBase.getsequenceNumber() == sequenceNumber)
					break;
			} else
				System.out.println("No Base at that location ");
		}

		if ((refBase.getsequenceNumber() - 1) == playerCyborg.getlastBaseReached()) {
			playerCyborg.setlastBaseReached(refBase.getsequenceNumber());
		}
		playerCyborg.setpoint(refBase.getpoint());

		System.out.println("Player Cyborg has collided with Base");
		System.out.println("Player Cyborg" + ": " + playerCyborg);

		setChanged();
		notifyObservers();
	}

	/*
	 * keyboard input "e" Simulates collision between player cyborg and energy
	 * station adds energy to the cyborg.
	 */
	public void pCyborgeStationCollison() {
		EnergyStation refStation = null;

		IIterator theElements = gameObjectCollection.getIterator();
		while (theElements.hasNext()) {
			GameObject gameObject = (GameObject) theElements.getNext();
			if (gameObject instanceof EnergyStation) {
				refStation = (EnergyStation) gameObject;
				if (refStation.getcapacity() != 0)
					break;
			}
		}

		playerCyborg.setpoint(refStation.getpoint());
		int eCpacity = refStation.getcapacity();
		refStation.setcapacity(0);

		int curEnergy = playerCyborg.getenergyLevel();
		playerCyborg.setenergyLevel(0);
		playerCyborg.setenergyLevel(eCpacity + curEnergy);

		EnergyStation energyStation = initEnergyStation();
		gameObjectCollection.add(energyStation);

		System.out.println("Player Cyborg has collided with Energy Station");
		System.out.println("Player Cyborg" + ": " + playerCyborg);

		setChanged();
		notifyObservers();
	}

	/*
	 * keyboard input "g" Simulates collision between player cyborg and drone Causes
	 * damage to the player cyborg based off size and speed of the drone.
	 */
	public void pCyborgdroneCollison() {
		Drone refDrone = null;

		IIterator theElements = gameObjectCollection.getIterator();
		while (theElements.hasNext()) {
			GameObject gameObject = (GameObject) theElements.getNext();
			if (gameObject instanceof Drone) {
				refDrone = (Drone) gameObject;
				break;
			}
		}

		playerCyborg.setpoint(refDrone.getpoint());
		playerCyborg.fadeColor();

		int damage = 5 + 10 * refDrone.getsize() / 100 + 10 * playerCyborg.getspeed() / 100;
		int curDamg = playerCyborg.getdamageLevel();
		playerCyborg.setdamageLevel(curDamg + damage);
		checkCyborgState();

		playerCyborg.setspeed(playerCyborg.getspeed());

		System.out.println("Player Cyborg has collided with Drone");
		System.out.println("Player Cyborg" + ": " + playerCyborg);

		setChanged();
		notifyObservers();
	}

	/*
	 * keyboard input "t" advances the gameclock forward causes all movalble objects
	 * to move based off heading and speed.
	 */
	public void tickGameClock() {
		timeElapsed++;

		System.out.println("Time Elapesed " + timeElapsed);
		checkPlayerValues();
		checkPlayerLives();
		checkNPCValues();
		moveAll();
		setChanged();
		notifyObservers();
	}

	/**
	 * 
	 */
	private void moveAll() {
		IIterator theElements = gameObjectCollection.getIterator();
		while (theElements.hasNext()) {
			GameObject gameObject = (GameObject) theElements.getNext();
			if (gameObject instanceof Movable) {
				Movable mObj = (Movable) gameObject;
				mObj.move();
			}
		}
	}

	/**
	 * 
	 */
	private void checkPlayerLives() {
		if (lives < 0) {
			System.out.println("Player Cyborg has no remaining lives");
			System.out.println("GAMEOVER");
			Display.getInstance().exitApplication();
			return;
		}
	}

	/**
	 * 
	 */
	private void checkPlayerValues(){
		if (playerCyborg.getdamageLevel() >= playerCyborg.getMAXDAMAGELEVEL()) {
			lives--;
			if (lives >= 0) {
				int r = 170, g = 169, b = 173; // initial color
				playerCyborg.setdamageLevel(0);
				playerCyborg.setcolor(r, g, b);
				playerCyborg.setenergyLevel(100);
			}
		}

		else if (playerCyborg.getenergyLevel() <= 0) {
			lives--;
			if (lives >= 0) {
				int r = 170, g = 169, b = 173; // initial color
				playerCyborg.setdamageLevel(0);
				playerCyborg.setcolor(r, g, b);
				playerCyborg.setenergyLevel(100);
			}
		}
	}

	/**
	 * 
	 */
	private void checkNPCValues() {
		IIterator theElements = gameObjectCollection.getIterator();
		while (theElements.hasNext()) {
			GameObject gameObject = (GameObject) theElements.getNext();
			if (gameObject instanceof NPCCyborg) {
				NPCCyborg npc = (NPCCyborg) gameObject;
				if(npc.getmaxBaseReached() == baseCount) {
					System.out.println("NPC HAS REACHED FINAL BASE");
					System.out.println("GAMEOVER");
					Display.getInstance().exitApplication();
					return;
				}
					
			}
		}
	}

	/**
	 * Checks to see if the player cyborg is in a dead state has no energy or has
	 * reached max damage
	 * 
	 * @param playerCyborg the player cyborg to check
	 * @param str          the player cyborg toString used to print final cyborg
	 *                     state.
	 */
	public void checkCyborgState() {
		if (playerCyborg.getdamageLevel() >= playerCyborg.getMAXDAMAGELEVEL()) {
			lives--;
			if (lives >= 0) {
				int r = 170, g = 169, b = 173; // initial color
				playerCyborg.setdamageLevel(0);
				playerCyborg.setcolor(r, g, b);
				playerCyborg.setenergyLevel(100);
			}
		}

		if (lives < 0) {
			System.out.println("CYBORG HAS NO REMAINING LIVES");
			System.out.println("GAMEOVER");
			System.out.println("FINAL STATS");
			System.out.println("PLAYER CYBORG");
			System.out.println(playerCyborg);
			Display.getInstance().exitApplication();
			return;
		}
		setChanged();
		notifyObservers();
	}

	/*
	 * Keyboard input "x" d prompts the user to the exit the game.
	 */
	@Deprecated
	public void promptExitGame() {
		System.out.println();
		System.out.println("Would you like to end the game");
		System.out.println("y: yes");
		System.out.println("n: no");
		System.out.println();
		this.exitFlag = true;
	}

	/*
	 * Keyboard input "y" confirms the user to exit the game.
	 */
	@Deprecated
	public void confirmExitGame() {
		if (this.exitFlag == true) {
			System.out.println(
					"\n \n \n \n \n \nThe Enrichment Center is committed to the well being of all participants.");
			Display.getInstance().exitApplication();
		}
	}

	/*
	 * Keyboard input "n" confirms to decline the user exiting the game.
	 */
	@Deprecated
	public void declineExitGame() {
		this.exitFlag = false;
	}

	/*
	 * keyboard input "d" displays the current status of the player cyborg.
	 */
	@Deprecated
	public void displayGameStatus() {
		double xVal = playerCyborg.getpoint().getX();
		double rxVal = Math.round(xVal * 10.0) / 10.0;
		double yVal = playerCyborg.getpoint().getY();
		double ryVal = Math.round(yVal * 10.0) / 10.0;
		String Locaction = "Location= " + "(" + rxVal + "," + ryVal + "), ";

		System.out.println("p1Cyborg");
		System.out.println("Lives: " + lives);
		System.out.println("Time Elapsed: " + timeElapsed);
		System.out.println("Player Cyborg: ");
		System.out.println("Last Base Reached: " + playerCyborg.getlastBaseReached());
		System.out.println("Energy Level: " + playerCyborg.getenergyLevel());
		System.out.println("Damage Level: " + playerCyborg.getdamageLevel());
		System.out.println("Location: " + Locaction);
		System.out.println();
	}
}