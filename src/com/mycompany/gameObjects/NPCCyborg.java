/**
 * 
 */
package com.mycompany.gameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.IStrategy;

/**
 * @author jp
 *
 */
public class NPCCyborg extends Cyborg {
	private IStrategy strategy;
	/**
	 * @param energyLevel
	 * @param energyConsumptionRate
	 * @param damageLevel
	 * @param lastBaseReached
	 * @param maxBaseReached
	 * @param steeringDirection
	 * @param heading
	 * @param speed
	 * @param size
	 * @param point
	 * @param color
	 */
	public NPCCyborg(int energyLevel, int energyConsumptionRate, int damageLevel, 
			int maxDamageLevel, int lastBaseReached,int maxBaseReached, 
			int steeringDirection, int heading, int speed, 
			int size, Point point, int color, int UUID) {
		
		super(energyLevel, energyConsumptionRate, damageLevel, 
				maxDamageLevel, lastBaseReached, maxBaseReached, 
				steeringDirection, heading, speed,
				size, point, color, UUID);
	}
	
	/**
	 * 
	 */
	public void invokeStrategy() {
		strategy.apply();
	}

	
	/**
	 * 
	 * @return
	 */
	public IStrategy getStrategy() {
		return strategy;
	}
	
	/**
	 * 
	 * @param strategy
	 */
	public void setStrategy(IStrategy strategy) {
		this.strategy = strategy;
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int size = super.getSize();
		g.setColor(this.getcolor());
		int xLoc = (int) (pCmpRelPrnt.getX()+ this.getPoint().getX());
		int yLoc = (int) (pCmpRelPrnt.getY()+ this.getPoint().getY());
		g.drawRect(xLoc-size/2, yLoc-size/2, size, size);
	}

	
	/**
	 * 
	 */
	@Override
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = parentDesc
				+ "Strategy= " + strategy.getClass().getSimpleName();
		return myDesc;
	}	
	
	/**
	 * Overides the Movable Gameobject
	 * sets limitations for Movable Cyborg Gameobject from moving 
	 * based off of speed, energy level, and damage. 
	 */
	@Override
	public void move() {
		//invokeStrategy();
		super.move();
	}



	@Override
	public void handleCollision(GameObject otherObject) {

	}
}
