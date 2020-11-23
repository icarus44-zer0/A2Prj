package com.mycompany.a3;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.mycompany.gameObjects.Base;
import com.mycompany.gameObjects.Cyborg;
import com.mycompany.gameObjects.Drone;
import com.mycompany.gameObjects.EnergyStation;
import com.mycompany.gameObjects.GameObject;
import com.mycompany.gameObjects.Movable;
import com.mycompany.gameObjects.NPCCyborg;
import com.mycompany.gameObjects.PlayerCyborg;
import com.mycompany.gui.MapViewContainer;
import com.mycompany.sfx.ChargeSound;
import com.mycompany.sfx.CrashSound;
import com.mycompany.sfx.DeathSound;
import com.mycompany.sfx.ThemeSound;

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
	private double timeElapsed;
	private double totalTimeElapsed;
	private boolean exitFlag;
	private boolean soundFlag;
	private boolean strategyflag;
	private int intialBaseSeqNumber;
	private int baseCount;
	private int npcCyborgCount;
	private int droneCount;
	private int eStationCount;
	private int playerCount;
	private Point intiBasePoint;
	private PlayerCyborg playerCyborg;
	private GameObjectCollection gameObjectCollection;
	private CrashSound crashSound;
	private ChargeSound chargeSound;
	private DeathSound deathSound;
	private ThemeSound themeSound;
	private ArrayList<Point> locationList;

	/**
	 * Constructor
	 */
	private GameWorld() {
		gameObjectCollection = new GameObjectCollection();
		locationList = new ArrayList<Point>();
		lives = 3;
		timeElapsed = 0;
		exitFlag = false;
		soundFlag = false;
		strategyflag = false;
		intialBaseSeqNumber = 1;
		baseCount = 4;
		npcCyborgCount = 3;
		droneCount = 3;
		eStationCount = 4;
		playerCount = 1;
	}

	/**
	 * Gets instance of GameWorld class
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
				intiBasePoint = new Point(base.getPoint().getX(), base.getPoint().getY());
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
		int energyConsumptionRate = 0;
		int damageLevel = 0;
		int maxDamageLevel = 100;
		int lastBaseReached = 1;
		int maxBaseReached = 1;
		int steeringDirection = 0;
		int size = 75;

		Point point = intiBasePoint;
		int heading = random.nextInt(359) + 1;
		int speed = random.nextInt(10 - 5) + 5;
		int color = ColorUtil.rgb(r, g, b);

		return PlayerCyborg.getInstance(energyLevel, energyConsumptionRate, damageLevel, maxDamageLevel,
				lastBaseReached, maxBaseReached, steeringDirection, heading, speed, size, point, color);
	}
	
	private void resetPlayerCyborg() {
		Random random = new Random();
		int r = 26, g = 188, b = 212; // Sea Blue
		playerCyborg.setColor(r, g, b);
		playerCyborg.setPoint(intiBasePoint);
		
		playerCyborg.setspeed(random.nextInt(10 - 5) + 5);
		
	}

	/**
	 * 
	 * @return
	 */
	private NPCCyborg initNPCyborg() {
		Random random = new Random();
		Point point = setInitialPoint();

		int r = 111, g = 23, b = 231; // Royal Purple

		int energyLevel = 100;
		int energyConsumptionRate = 0;
		int damageLevel = 0;
		int maxDamageLevel = 1000;
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
		int r = 219, g = 22, b = 224; // Hot Pink
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
		return new Base(intialBaseSeqNumber++, size, point, color);
	}

	/**
	 * 
	 * @return
	 */
	private EnergyStation initEnergyStation() {
		Random random = new Random();
		Point point = setInitialPoint();
		int r = 255, g = 191, b = 0; // Burnt Orange
		int size = 25 + random.nextInt(100 + 1) + 10;
		int color = ColorUtil.rgb(r, g, b);
		int capacity = size * 3;
		return new EnergyStation(capacity, size, point, color);
	}

	private Point setInitialPoint() {
		Random random = new Random();
		Point initPoint = new Point();
		IIterator iter = gameObjectCollection.getIterator();
		boolean isUnique = false;
		boolean isProx = false;
		float x;
		float y;
		

		if (gameObjectCollection.isEmpty()) {
			x = (float) random.nextInt((gameWidth - 150) - 100) + 100;
			y = (float) random.nextInt((gameHeight - 150) - 100) + 100;
			initPoint.setX(x);
			initPoint.setY(y);
			return initPoint;
		} else {
			while (!isUnique && !isProx) {
				x = (float) random.nextInt((gameWidth - 150) - 100) + 100;
				y = (float) random.nextInt((gameHeight - 150) - 100) + 100;
				initPoint.setX(x);
				initPoint.setY(y);

				while (iter.hasNext()) {
					GameObject object = (GameObject) iter.getNext();
					if (initPoint.equals(object.getPoint())) {
						isUnique = false;
					}
//					if(isProximity(initPoint, object.getPoint()){
//						isProx = false;
//					}
					isUnique = true;
					isProx = true;
				}
			}
			return initPoint;
		}
	}

//	private boolean isProximity(Point a, Point b) {
//		float b_xMax = a.getX() + 50;
//		float b_xMin = a.getX();
//		float b_yMax = a.getY() + 50;
//		float b_yMin = a.getY();
//		
//		float c_xLoc = b.getX();
//		float c_yLoc = b.getY();
//		
//		return (b_xMax <= c_xLoc && b && c_yLoc <= b_yMax && c_yLoc >= b_yMin);
//	}

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
	public double gettimeElapsed() {
		return timeElapsed;
	}

	/**
	 * 
	 * @param i
	 */
	public void setElapsedTime(double i) {
		timeElapsed = i;
		setTotalTimeElapsed(i);
	}

	
	
	/**
	 * @return the totalTimeElapsed
	 */
	public int getTotalTimeElapsed() {
		return (int)totalTimeElapsed;
	}

	/**
	 * @param totalTimeElapsed the totalTimeElapsed to set
	 */
	public void setTotalTimeElapsed(double totalTimeElapsed) {
		this.totalTimeElapsed += totalTimeElapsed;
//		setChanged();
//		notifyObservers();
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
	 * @return
	 */
	public GameObjectCollection getGameObjectCollection() {
		return gameObjectCollection;
	}

	public void createSounds() {
		try {
			crashSound = new CrashSound("damage.wav");
			deathSound = new DeathSound("LifeLost.wav");
			chargeSound = new ChargeSound("retroPower.wav");
			themeSound = new ThemeSound("resistors.mp3");
		} catch (Exception e) {
			
		}

	}

	/**
	 * 
	 */
	public void changeNPCStrategy() {
		NPCCyborg npcCyborg =  null;
		IIterator iterator = gameObjectCollection.getIterator();
		while (iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if (gameObject instanceof NPCCyborg) {
				npcCyborg = (NPCCyborg) gameObject;
				if (npcCyborg.getStrategy() instanceof NPCNextBaseStratagy) {
					npcCyborg.setStrategy(new NPCAttackStratagy(npcCyborg));
				} else {
					npcCyborg.setStrategy(new NPCNextBaseStratagy(npcCyborg));
				}
			}
		}
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
		//System.out.println("Player Cyborg" + ": " + playerCyborg);
		setChanged();
		notifyObservers();
	}

	/*
	 * keyboard input "r" causes the player cybog to turn right by 5 degrees.
	 */
	public void pCyborgturnRight() {
		playerCyborg.setsteeringDirection(5);
		//System.out.println("Player Cyborg" + ": " + playerCyborg);
		setChanged();
		notifyObservers();
	}

	/*
	 * Keyboard input "c" Simulates collision with another cyborg Causes damage to
	 * the player cyborg based off size and speed of the other cyborg.
	 */
	public void pCyborgcyborgCollision(NPCCyborg refNPCyborg) {
		playerCyborg = PlayerCyborg.getInstance();

	
		int damageTaken = 10 + 10 * refNPCyborg.getSize() / 100 + 10 * playerCyborg.getspeed() / 100;
		int currentDamage = playerCyborg.getdamageLevel();
		int npcDamageTaken = 10 + 10 * refNPCyborg.getSize() / 100 + 10 * refNPCyborg.getspeed() / 100;
		int npcCurrrentDamg = refNPCyborg.getdamageLevel();

		playerCyborg.setdamageLevel(currentDamage + damageTaken);
		playerCyborg.setspeed(playerCyborg.getspeed());
		playerCyborg.fadeColor();
		playerCyborg.setPoint(refNPCyborg.getPoint());

		refNPCyborg.setdamageLevel(npcCurrrentDamg + npcDamageTaken);
		refNPCyborg.setspeed(refNPCyborg.getspeed()); // TODO create update speed method
		refNPCyborg.fadeColor();

		themeSound.pause();
		crashSound.play();
		themeSound.play();

		checkNpcValues(refNPCyborg);
		checkPlayerValues();


		System.out.println("Player Cyborg has collided with NPC Cyborg");

		setChanged();
		notifyObservers();
	}

	/*
	 * keyboard input "1-9" Simulates collision between player cyborg and base.
	 * 
	 * @param takes in the base the player cyborg is to move to.
	 */
	public void pCyborgBaseCollison(Base refBase) {

		playerCyborg = PlayerCyborg.getInstance();

		if ( playerCyborg.getmaxBaseReached() + 1 == refBase.getSequenceNumber()) {
			playerCyborg.setmaxBaseReached(refBase.getSequenceNumber());
		}

		System.out.println("Player Cyborg Collided with base " + refBase.getSequenceNumber());
		checkPlayerValues();
		
		setChanged();
		notifyObservers();
	}

	/*
	 * keyboard input "e" Simulates collision between player cyborg and energy
	 * station adds energy to the cyborg.
	 */
	public void pCyborgeStationCollison(EnergyStation refStation) {
		playerCyborg.setPoint(refStation.getPoint());
		int eCpacity = refStation.getcapacity();
		refStation.setcapacity(0);

		int curEnergy = playerCyborg.getenergyLevel();
		playerCyborg.setenergyLevel(0);
		playerCyborg.setenergyLevel(eCpacity + curEnergy);

		EnergyStation energyStation = initEnergyStation();
		gameObjectCollection.add(energyStation);

		System.out.println("Player Cyborg has collided with Energy Station");

		themeSound.pause();
		chargeSound.play();
		themeSound.play();
		
		
		checkPlayerValues();
		
		setChanged();
		notifyObservers();
	}

	/*
	 * keyboard input "g" Simulates collision between player cyborg and drone Causes
	 * damage to the player cyborg based off size and speed of the drone.
	 */
	public void pCyborgdroneCollison(Drone refDrone) {
		

		crashSound.play();
		playerCyborg.setPoint(refDrone.getPoint());
		playerCyborg.fadeColor();

		int damage = 5 + 10 * refDrone.getSize() / 100 + 10 * playerCyborg.getspeed() / 100;
		int curDamg = playerCyborg.getdamageLevel();
		playerCyborg.setdamageLevel(curDamg + damage);
		playerCyborg.setspeed(playerCyborg.getspeed());

		System.out.println("Player Cyborg has collided with Drone");
		
		themeSound.pause();
		crashSound.play();
		themeSound.play();
		
		checkPlayerValues();
		
		setChanged();
		notifyObservers();
	}

	/*
	 * keyboard input "1-9" Simulates collision between player cyborg and base.
	 * 
	 * @param takes in the base the player cyborg is to move to.
	 */
	public void NPCCyborgBaseCollison(Base refBase, NPCCyborg refNpcCyborg) {

		if ((refBase.getSequenceNumber() - 1) == refNpcCyborg.getlastBaseReached()) {
			refNpcCyborg.setlastBaseReached(refBase.getSequenceNumber());
		}
		refNpcCyborg.setPoint(refBase.getPoint());

		System.out.println("NPC Cyborg has collided with Base");
		System.out.println("NPC Cyborg" + ": " + playerCyborg);
		
		checkNpcValues(refNpcCyborg);
		setChanged();
		notifyObservers();
	}

	/*
	 * keyboard input "t" advances the gameclock forward causes all movalble objects
	 * to move based off heading and speed.
	 */
	public void tickGameClock(double timeElapsed) {
		this.timeElapsed += timeElapsed;
		this.totalTimeElapsed += timeElapsed;
		//themeSound.play();
		moveAll();
		checkCollisions();
		setChanged();
		notifyObservers();
	}

	private void checkCollisions() {
		IIterator iter = gameObjectCollection.getIterator();
		while(iter.hasNext())
		{
			Object thisObj = iter.getNext();
			if(thisObj instanceof ICollider)
			{
				ICollider thisColliderObj = (ICollider) thisObj;
				IIterator otherIter = gameObjectCollection.getIterator();
				while(otherIter.hasNext())
				{
					Object otherObj = otherIter.getNext();
					if(otherObj instanceof ICollider && !(thisObj.equals(otherObj)))
					{
						ICollider otherColliderObj = (ICollider) otherObj;
						
						if(thisColliderObj.collidesWith((GameObject) otherColliderObj))
						{
							thisColliderObj.handleCollision((GameObject) otherColliderObj);
							
						}
					}
				}
			}
		}
	}
	
	
	
//	
//	/**
//	 * 
//	 */
//	private void checkCollisions() {
//		IIterator iter = gameObjectCollection.getIterator();
//		IIterator iter2 = gameObjectCollection.getIterator();
//
//		while (iter.hasNext()) {
//			GameObject obj1 = (GameObject) iter.getNext();
//
//			if (obj1 instanceof ICollider) {
//				ICollider c1 = (ICollider) obj1;
//				ArrayList<ICollider> c1ArrayList = c1.getCollideList();
//
//				while (iter2.hasNext()) {
//					GameObject obj2 = (GameObject) iter2.getNext();
//					
//					if (obj2 instanceof ICollider && !(obj1.equals(obj2))) {
//						ICollider c2 = (ICollider) obj2;
//						ArrayList<ICollider> c2ArrayList = c2.getCollideList();
//
//						if (c1.collidesWith((GameObject) c2) == true) {
//							//System.out.println("Colided " + c1.getClass().getSimpleName() + " + " + c2.getClass().getSimpleName());
//							setNewCollsion(c1, c2, c1ArrayList, c2ArrayList);
//							obj1.handleCollision(obj2);
//							obj2.handleCollision(obj1);
//						} else {
//							removeCollsions(c1, c2, c1ArrayList, c2ArrayList);
//						}
//					}
//				}
//			}
//		}
//	}
//
//	/**
//	 * 
//	 * @param ICollider c1
//	 * @param ICollider c2
//	 */
//	private void removeCollsions(ICollider c1, ICollider c2, ArrayList<ICollider> c1ArrayList,
//			ArrayList<ICollider> c2ArrayList) {
//
//		if ((previousCollsion(c1, c2, c1ArrayList, c2ArrayList))) {
//			c1ArrayList.remove(c1ArrayList.indexOf(c2));
//			c2ArrayList.remove(c2ArrayList.indexOf(c1));
//			System.out.println("Removed " + c1.getClass().getSimpleName() + " + " + c2.getClass().getSimpleName());
//		}
//
//	}
//
//	/**
//	 * 
//	 * @param ICollider c1
//	 * @param ICollider c2
//	 */
//	private void setNewCollsion(ICollider c1, ICollider c2, ArrayList<ICollider> c1ArrayList,
//			ArrayList<ICollider> c2ArrayList) {
//
//		if (!(previousCollsion(c2, c2, c2ArrayList, c2ArrayList))) {
//			c1ArrayList.add(c2);
//			c2ArrayList.add(c1);
//			System.out.println("Added " + c1.getClass().getSimpleName() + " + " + c2.getClass().getSimpleName());
//		}
//	}
//
//	/**
//	 * 
//	 * @param ICollider c1
//	 * @param ICollider c2
//	 * @return
//	 */
//	private boolean previousCollsion(ICollider c1, ICollider c2, ArrayList<ICollider> c1ArrayList,
//			ArrayList<ICollider> c2ArrayList) {
//		return(c1ArrayList.contains(c2) && c2ArrayList.contains(c1));
//	}
//
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

//	/**
//	 * 
//	 */
//	private void checkPlayerLives() {
//		if (lives < 0) {
//			System.out.println("Player Cyborg has no remaining lives");
//			System.out.println("GAMEOVER");
//			Display.getInstance().exitApplication();
//			return;
//		}
//	}

	/**
	 * 
	 */
	private void checkPlayerValues() {
		if (playerCyborg.getdamageLevel() >= playerCyborg.getMAXDAMAGELEVEL()) {
			lives--;
			themeSound.pause();
			deathSound.play();
			themeSound.play();
			resetPlayerCyborg();
			if (lives >= 0) {
				int r = 170, g = 169, b = 173; // initial color
				playerCyborg.setdamageLevel(0);
				playerCyborg.setColor(r, g, b);
				playerCyborg.setenergyLevel(100);
			}
		}

		else if (playerCyborg.getenergyLevel() <= 0) {
			lives--;
			themeSound.pause();
			deathSound.play();
			themeSound.play();
			resetPlayerCyborg();
			if (lives >= 0) {
				int r = 170, g = 169, b = 173; // initial color
				playerCyborg.setdamageLevel(0);
				playerCyborg.setColor(r, g, b);
				playerCyborg.setenergyLevel(100);
			}
		}
		
		
		else if (playerCyborg.getmaxBaseReached() == baseCount) {
			System.out.println("NPC HAS REACHED FINAL BASE");
			System.out.println("GAMEOVER");
			Display.getInstance().exitApplication();
			return;
		}

		else if (lives < 0) {
			System.out.println("CYBORG HAS NO REMAINING LIVES");
			System.out.println("GAMEOVER");
			System.out.println("FINAL STATS");
			System.out.println("PLAYER CYBORG");
			System.out.println(playerCyborg);
			Display.getInstance().exitApplication();
			return;
		}
	}

	/**
	 * 
	 */
	private void checkNpcValues(NPCCyborg npc) {
		if (npc.getmaxBaseReached() == baseCount) {
			System.out.println("NPC HAS REACHED FINAL BASE");
			System.out.println("GAMEOVER");
			Display.getInstance().exitApplication();
			return;
		}
	}
//
//	/**
//	 * Checks to see if the player cyborg is in a dead state has no energy or has
//	 * reached max damage
//	 * 
//	 * @param playerCyborg the player cyborg to check
//	 * @param str          the player cyborg toString used to print final cyborg
//	 *                     state.
//	 */
//	public void checkCyborgState() {
//		if (playerCyborg.getdamageLevel() >= playerCyborg.getMAXDAMAGELEVEL()) {
//			lives--;
//			deathSound.play();
//			if (lives >= 0) {
//				int r = 170, g = 169, b = 173; // initial color
//				playerCyborg.setdamageLevel(0);
//				playerCyborg.setColor(r, g, b);
//				playerCyborg.setenergyLevel(100);
//			}
//		}
//		
//		if (playerCyborg.getmaxBaseReached() == baseCount) {
//			System.out.println("NPC HAS REACHED FINAL BASE");
//			System.out.println("GAMEOVER");
//			Display.getInstance().exitApplication();
//			return;
//		}
//
//		if (lives < 0) {
//			System.out.println("CYBORG HAS NO REMAINING LIVES");
//			System.out.println("GAMEOVER");
//			System.out.println("FINAL STATS");
//			System.out.println("PLAYER CYBORG");
//			System.out.println(playerCyborg);
//			Display.getInstance().exitApplication();
//			return;
//		}
//		setChanged();
//		notifyObservers();
//	}


	/*
	 * keyboard input "d" displays the current status of the player cyborg.
	 */
	@Deprecated
	public void displayGameStatus() {
		double xVal = playerCyborg.getPoint().getX();
		double rxVal = Math.round(xVal * 10.0) / 10.0;
		double yVal = playerCyborg.getPoint().getY();
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

	public void debug() {
		// objectsInBounds_Width();
		// objectsInBounds_Heigth();
		// playerLocation();
		// npcNeverWin();
		// printMap();

	}

	private void printMap() {
		GameWorld gameWorld = GameWorld.getInstance();
		GameObjectCollection gameObjectCollection = gameWorld.getGameObjectCollection();
		IIterator it = gameObjectCollection.getIterator();
		while (it.hasNext()) {
			GameObject gameObject = (GameObject) it.getNext();
			System.out.println(gameObject.getClass().getSimpleName() + ": " + gameObject.toString());

		}

	}

	private void npcNeverWin() {
		IIterator itr = gameObjectCollection.getIterator();
		while (itr.hasNext()) {
			GameObject gameObject = (GameObject) itr.getNext();
			if (gameObject instanceof NPCCyborg) {
				NPCCyborg npc = (NPCCyborg) gameObject;
				if (npc.getmaxBaseReached() == baseCount - 1) {
					npc.setmaxBaseReached(1);
					return;
				}
			}

		}
	}

	private void playerLocation() {
		IIterator itr = gameObjectCollection.getIterator();
		while (itr.hasNext()) {
			GameObject gameObject = (GameObject) itr.getNext();
			if (gameObject instanceof PlayerCyborg) {
				PlayerCyborg playerCyborg = (PlayerCyborg) gameObject;

				double xVal = playerCyborg.getPoint().getX();
				double rxVal = Math.round(xVal * 10.0) / 10.0;
				double yVal = playerCyborg.getPoint().getY();
				double ryVal = Math.round(yVal * 10.0) / 10.0;

				String loc = "P1 Location= " + "(" + rxVal + "," + ryVal + "), ";
				System.out.println(loc);
			}
		}
	}

	private void objectsInBounds_Width() {
		GameWorld gameWorld = GameWorld.getInstance();
		GameObjectCollection gameObjectCollection = gameWorld.getGameObjectCollection();
		IIterator it = gameObjectCollection.getIterator();
		while (it.hasNext()) {
			GameObject gameObject = (GameObject) it.getNext();
			float x = gameObject.getPoint().getX();

			if (x <= 0 && x > (float) gameWorld.getGameWidth()) {
				System.out.println("X: " + x + " GW" + (float) gameWorld.getGameWidth());
			}
		}

	}

	private void objectsInBounds_Heigth() {
		GameWorld gameWorld = GameWorld.getInstance();
		GameObjectCollection gameObjectCollection = gameWorld.getGameObjectCollection();
		IIterator it = gameObjectCollection.getIterator();
		while (it.hasNext()) {
			GameObject gameObject = (GameObject) it.getNext();
			float y = gameObject.getPoint().getY();
			if (y <= 0 && y > (float) gameWorld.getGameWidth()) {
				System.out.println("Y: " + y + " GW" + (float) gameWorld.getGameHeight());
			}

		}
	}

	public void playThemeMusic() {
		themeSound.play();
	}

	public void pauseThemeMusic() {
		themeSound.pause();
	}

}