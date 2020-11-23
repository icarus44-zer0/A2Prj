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
			int size, Point point, int color) {
		
		super(energyLevel, energyConsumptionRate, damageLevel, 
				maxDamageLevel, lastBaseReached, maxBaseReached, 
				steeringDirection, heading, speed,
				size, point, color);
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
		super.draw(g, pCmpRelPrnt);
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

	/**
	 * 
	 * @param GameObject
	 * @return
	 */
	@Override
	public boolean collidesWith(GameObject otherObject) {
		float b_xMax = this.getPoint().getX() + this.getSize()/2 + 50;
		float b_xMin = this.getPoint().getX() - this.getSize()/2 + 50;
		float b_yMax = this.getPoint().getY() + this.getSize()/2 + 50;
		float b_yMin = this.getPoint().getY() - this.getSize()/2 + 50;
		float c_xLoc = otherObject.getPoint().getX();
		float c_yLoc = otherObject.getPoint().getY();
		return (c_xLoc <= b_xMax && c_xLoc >= b_xMin && c_yLoc <= b_yMax && c_yLoc >= b_yMin);
	}
	

	@Override
	public void handleCollision(GameObject otherObject) {
		if (otherObject instanceof PlayerCyborg) {
			GameWorld.getInstance().pCyborgcyborgCollision(this);
			this.setheading(180 -this.getheading());
			PlayerCyborg.getInstance().setheading(180- PlayerCyborg.getInstance().getheading());
		}
		else if (otherObject instanceof NPCCyborg) {
			NPCCyborg new_name = (NPCCyborg) otherObject;
			this.setheading(180 -this.getheading());
			new_name.setheading(180- new_name.getheading());
		}
	
		else if (otherObject instanceof Drone) {
			Drone new_name = (Drone) otherObject;
//			this.setheading(getheading()-this.getheading());
//			new_name.setheading(180- new_name.getheading());
		}
		else if (otherObject instanceof Base) {
			Base new_name = (Base) otherObject;
				GameWorld.getInstance().NPCCyborgBaseCollison(new_name,this);
			
		}
	}
}
