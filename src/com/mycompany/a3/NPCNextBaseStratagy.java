package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;
import com.mycompany.gameObjects.Base;
import com.mycompany.gameObjects.GameObject;
import com.mycompany.gameObjects.NPCCyborg;

public class NPCNextBaseStratagy implements IStrategy {
	private NPCCyborg npcCyborg;
	private Base nextBase;
	private GameObjectCollection gameObjectCollection;
	private GameWorld gameWorld;
	private Point npcLocation;
	private Point baseLocation;
	
	/**
	 * 
	 * @param npcCyborg
	 */
	public NPCNextBaseStratagy(NPCCyborg npcCyborg) {
		this.npcCyborg = npcCyborg;
		gameWorld = GameWorld.getInstance();
		gameObjectCollection = gameWorld.getGameObjectCollection();
	}
	
	/**
	 * apply method 
	 */
	@Override
	public void apply() {
		gameWorld = GameWorld.getInstance();
		gameObjectCollection = gameWorld.getGameObjectCollection();
		setNextBase();
		setLocations();
		
		float npcXcord = npcLocation.getX();
		float npcYcord = npcLocation.getY();
		
		float baseXcord = baseLocation.getX();
		float baseYcord = baseLocation.getY();
		
		float xDist = baseXcord - npcXcord;
		float yDist = baseYcord - npcYcord;
		
		double angrad = MathUtil.atan2(xDist, yDist);
		double angdeg = Math.toDegrees(angrad);
		
		double idealheading = 90 - angdeg;
		int heading = (int) Math.round(idealheading);
		
		npcCyborg.setheading(heading);
	}
	

	/**
	 * 
	 */
	private void setLocations() {
		npcLocation = npcCyborg.getPoint();
		baseLocation = nextBase.getPoint();
	}
	

	/**
	 * 
	 */
	private void setNextBase() {
		IIterator iterator = gameObjectCollection.getIterator();
		while (iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if(gameObject instanceof Base) {
				if (((Base) gameObject).getSequenceNumber() == npcCyborg.getmaxBaseReached() + 1 ) {
					nextBase = (Base)gameObject;
				}
			}	
		}
	}

}
