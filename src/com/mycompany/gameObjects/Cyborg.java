package com.mycompany.gameObjects;
/** Represents a Cyborge GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.mycompany.a3.ISteerable;



public abstract class Cyborg extends Movable implements ISteerable {

	protected int energyLevel;
	protected int energyConsumptionRate;
	protected int damageLevel;
	protected int lastBaseReached;
	protected int maxBaseReached;
	protected int steeringDirection;
	protected int  maxDamageLevel;
	protected final int MAXSPEED = 10;
	protected final int MAXSTEERINGDIRECTION = 40;


	/**
	 * 
	 * @param energyLevel
	 * @param energyConsumptionRate
	 * @param damageLevel
	 * @param maxDamageLevel
	 * @param lastBaseReached
	 * @param maxBaseReached
	 * @param steeringDirection
	 * @param heading
	 * @param speed
	 * @param size
	 * @param point
	 * @param color
	 */
	public Cyborg(int energyLevel, int energyConsumptionRate, int damageLevel, int maxDamageLevel,
			int lastBaseReached, int maxBaseReached, int steeringDirection,
			int heading, int speed, int size, Point point, int color, int UUID) {	
			super(heading, speed, size, point, color, UUID);
		
		this.energyLevel = energyLevel;
		this.energyConsumptionRate = energyConsumptionRate;
		this.damageLevel = damageLevel;
		this.maxDamageLevel = maxDamageLevel;
		this.lastBaseReached = lastBaseReached;
		this.maxBaseReached = maxBaseReached;
		this.steeringDirection = steeringDirection;
		
	}

	/**
	 * Getter for the energy level of Movable Cyborg Gameobject.
	 * @return the energy level of Movable Cyborg Gameobject.
	 */
	public int getenergyLevel() {
		return energyLevel;
	}

	/**
	 *Setter for the energy level of Movable Cyborg Gameobject. 
	 * @param the new energy level of Movable Cyborg Gameobject.
	 */
	public void setenergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}

	/**
	 * Getter for the energy Consumption Rate of Movable Cyborg Gameobject.
	 * @return the energy Consumption Rate of Movable Cyborg Gameobject.
	 */
	public int getenergyConsumptionRate() {
		return energyConsumptionRate;
	}

	/**
	 *Setter for the energy Consumption Rate of Movable Cyborg Gameobject. 
	 * @param the new energy Consumption Rate of Movable Cyborg Gameobject.
	 */
	protected void setenergyConsumptionRate(int rate) {
		this.energyConsumptionRate = rate;
	}

	/**
	 * Getter for the damageLevel of Movable Cyborg Gameobject.
	 * @return the damageLevel of Movable Cyborg Gameobject.
	 */
	public int getdamageLevel() {
		return damageLevel;
	}

	/**
	 *Setter for the damageLevel of Movable Cyborg Gameobject. 
	 * @param the new damageLevel of Movable Cyborg Gameobject.
	 */
	public void setdamageLevel(int damageLevel) {

		if (damageLevel >= maxDamageLevel)
			this.damageLevel = maxDamageLevel;
		else 
			this.damageLevel = damageLevel;
	}

	/**
	 * Getter for the lastBaseReached of Movable Cyborg Gameobject.
	 * @return the lastBaseReached of Movable Cyborg Gameobject.
	 */
	public int getlastBaseReached() {
		return lastBaseReached;
	}

	/**
	 *Setter for the lastBaseReached of Movable Cyborg Gameobject. 
	 * @param the new lastBaseReached of Movable Cyborg Gameobject.
	 */
	public void setlastBaseReached(int lastBaseReached) {
		this.lastBaseReached = lastBaseReached;
		if (this.lastBaseReached < maxBaseReached)
			maxBaseReached = lastBaseReached;
	}

	/**
	 * Getter for the steeringDirection of Movable Cyborg Gameobject.
	 * @return the steeringDirection of Movable Cyborg Gameobject.
	 */
	public int getsteeringDirection() {
		return steeringDirection;
	}

	/**
	 *Setter for the steeringDirection of Movable Cyborg Gameobject. 
	 * @param the new steeringDirection of Movable Cyborg Gameobject.
	 */
	public void setsteeringDirection(int steeringDirection) {
		int newDir = this.steeringDirection += steeringDirection;
		
		if (steeringDirection == 5) {
			if (newDir <= MAXSTEERINGDIRECTION ) {
				 this.steeringDirection = newDir;
			}
			else { 
				this.steeringDirection = MAXSTEERINGDIRECTION;
			}
		}
		else if (steeringDirection == -5) {
			if (newDir >= ((-1) *MAXSTEERINGDIRECTION)) {
				this.steeringDirection = newDir;
			}
			else {
				this.steeringDirection = (-1)*MAXSTEERINGDIRECTION;
			}
		}
	}
	
	/**
	 * Getter for the maxBaseReached of Movable Cyborg Gameobject.
	 * @return the maxBaseReached of Movable Cyborg Gameobject.
	 */
	public int getmaxBaseReached() {
		return maxBaseReached;
	}

	/**
	 *Setter for the maxBaseReached of Movable Cyborg Gameobject. 
	 * @param the new maxBaseReached of Movable Cyborg Gameobject.
	 */
	public void setmaxBaseReached(int max) {
		this.maxBaseReached = max;
	}
	
	
	/**
	 * Getter for the MAXSPEED of Movable Cyborg Gameobject.
	 * @return the MAXSPEED of Movable Cyborg Gameobject.
	 */
	public int getMAXSPEED() {
		return MAXSPEED;
	}


	/**
	 * Getter for the MAXSTEERINGDIRECTION of Movable Cyborg Gameobject.
	 * @return the MAXSTEERINGDIRECTION of Movable Cyborg Gameobject.
	 */
	public int getMAXSTEERINGDIRECTION() {
		return MAXSTEERINGDIRECTION;
	}


	/**
	 * Getter for the MAXDAMAGELEVEL of Movable Cyborg Gameobject.
	 * @return the MAXDAMAGELEVEL of Movable Cyborg Gameobject.
	 */
	public int getMAXDAMAGELEVEL() {
		return maxDamageLevel;
	}


	/*
	 * fades the color of the Movable Cyborg Gameobject.
	 * used when Movable Cyborg Gameobject takes damage. 
	 */
	public void fadeColor() {	
		int fade = (maxDamageLevel-damageLevel);
		int r= ColorUtil.red(super.getcolor()) * (((1 - fade) / 255 + fade) * 255); 
		int g= ColorUtil.green(super.getcolor()) * (((1 - fade) / 255 + fade) * 255);
		int b= ColorUtil.blue(super.getcolor()) * (((1 - fade) / 255 + fade) * 255);
		 super.setColor(r, g, b);		  
	}
	
	/**
	 * Overides the Movable Gameobject
	 * sets limitations for Movable Cyborg Gameobject from moving 
	 * based off of speed, energy level, and damage. 
	 */
	@Override
	public void move() {
			super.move();
	}

	/**
	 * Overides the Gameobject
	 * sets limitations for Movable Cyborg Gameobject from exceeding MAXSPEED.
	 */
	@Override
	public void setspeed(int speed) {
		if (energyLevel == 0 ) {
			super.setspeed(0);
		}
//		if (speed <= MAXSPEED && (!(speed<0))) {
//			super.setspeed(speed * (maxDamageLevel - damageLevel) / 100);	
//		}
		else if (speed > MAXSPEED) {
			super.setspeed(MAXSPEED);
		}
		else if (speed<0) {
			super.setspeed(0);
		}		
	}
	
	/**
	 * Overides the Gameobject
	 * prevents Movable Cyborg Gameobject from changing size.
	 */
	@Override
	public void setSize(int size) {
		if (super.getSize() == 0)
			super.setSize(size);
	}

	/**
	 * Implements the Steerable interface 
	 * updates the heading of the Movable Cyborg Gameobject
	 * based of steering direction. 
	 */
	@Override
	public void steer() {
		super.setheading(steeringDirection + super.getheading());

	}
	
	/**
	 *Overides toString for Movable Cyborg Gameobject 
	 */
	@Override
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = parentDesc
				+ "Last Base= " + maxBaseReached + ", " 
				+ "Steering Dir= " + steeringDirection + ", " 
				+ "MaxSpeed= " + MAXSPEED + ", " 
				+ "Energy Level= " + energyLevel + ", "
				+ "Damage Level= " + damageLevel + " ";
		return myDesc;
	}

	/**
	 *Overides equals opperator for Movable Cyborg Gameobject 
	 */
	@Override
	public boolean equals(Object obj) {
		  if (!(obj instanceof Cyborg)) {
	            return false;
	        }
		Cyborg test = (Cyborg) obj;
		return test.damageLevel == this.damageLevel 
				&& test.energyConsumptionRate == this.energyConsumptionRate
				&& test.energyLevel == this.energyLevel 
				&& test.lastBaseReached == this.lastBaseReached
				&& test.maxBaseReached == this.maxBaseReached 
				&& test.MAXSPEED == this.MAXSPEED
				&& test.steeringDirection == this.steeringDirection 
				&& test.getcolor() == this.getcolor()
				&& test.getPoint() == this.getPoint() 
				&& test.getSize() == this.getSize()
				&& test.getspeed() == this.getspeed() 
				&& test.getheading() == this.getheading(); 
	}

	/**
	 *Overides hascode for Movable Cyborg Gameobject 
	 */
	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + this.damageLevel;
		hash = 31 * hash + this.energyConsumptionRate;
		hash = 31 * hash + this.energyLevel;
		hash = 31 * hash + this.lastBaseReached;
		hash = 31 * hash + this.maxBaseReached;
		hash = 31 * hash + this.MAXSPEED;
		hash = 31 * hash + this.steeringDirection;
		hash = 31 * hash + this.getcolor();
		hash = 31 * hash + this.getSize();
		hash = 31 * hash + this.getspeed();
		hash = 31 * hash + this.getheading();
		hash = 31 * hash + Float.floatToIntBits(this.getPoint().getX());
		hash = 31 * hash + Float.floatToIntBits(this.getPoint().getY());
		return hash;
	}

}
