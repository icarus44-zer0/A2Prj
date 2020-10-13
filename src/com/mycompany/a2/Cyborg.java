package com.mycompany.a2;
/** Represents a Cyborge GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;



public class Cyborg extends Movable implements ISteerable {

	private int _energyLevel;
	private int _energyConsumptionRate;
	private int _damageLevel;
	private int _lastBaseReached;
	private int _maxBaseReached;
	private int _steeringDirection;
	private final int _MAX_SPEED = 10;
	private final int _MAX_STEERING_DIRECTION = 40;
	private final int  _MAX_DAMAGE_LEVEL = 100;


	
	/*
	 * Constructor for NPC cyborgs 
	 */
	public Cyborg() {		
	    Random random = new Random();
	    float min = 0f;
	    float max = 1000f;
	    float r1 = min + random.nextFloat() * (max - min);
	    float r2 = min + random.nextFloat() * (max - min);
		final int r=170, g=169, b=173; //Silver (Metallic)
		
		
	    super.set_size(50);
	    super.set_point(new Point(r1,r2));
		super.set_color(r,g,b);
		super.set_heading(random.nextInt(359)+1);
		super.set_speed(random.nextInt(10-5)+5);
		set_energyLevel(100);
		set_energyConsumptionRate(10);
		set_damageLevel(0);
		set_lastBaseReached(0);
		set_maxBaseReached(0);
		set_steeringDirection(0);
	}
	
	
	/*
	 * Constructor for player Cyborgs 
	 */
	public Cyborg(Point startingPoint) {
		final int r=170, g=169, b=173; //Silver (Metallic)
		
		super.set_size(50);
		super.set_point(startingPoint);
		super.set_color(r,g,b);
		super.set_heading(0);
		super.set_speed(1);
		set_energyLevel(100);
		set_energyConsumptionRate(10);
		set_damageLevel(0);
		set_lastBaseReached(1);
		set_maxBaseReached(1);
		set_steeringDirection(0);
	}
	

	/**
	 * Getter for the gameobject size.
	 * @return the size of the gameobject.
	 */

	/**
	 *Setter for the size of a gameobject. 
	 * @param the new size of a gameobject.
	 */
	
	
	/**
	 * Getter for the energy level of Movable Cyborg Gameobject.
	 * @return the energy level of Movable Cyborg Gameobject.
	 */
	public int get_energyLevel() {
		return _energyLevel;
	}

	/**
	 *Setter for the energy level of Movable Cyborg Gameobject. 
	 * @param the new energy level of Movable Cyborg Gameobject.
	 */
	public void set_energyLevel(int energyLevel) {
		this._energyLevel = energyLevel;
	}

	/**
	 * Getter for the energy Consumption Rate of Movable Cyborg Gameobject.
	 * @return the energy Consumption Rate of Movable Cyborg Gameobject.
	 */
	public int get_energyConsumptionRate() {
		return _energyConsumptionRate;
	}

	/**
	 *Setter for the energy Consumption Rate of Movable Cyborg Gameobject. 
	 * @param the new energy Consumption Rate of Movable Cyborg Gameobject.
	 */
	private void set_energyConsumptionRate(int rate) {
		this._energyConsumptionRate = rate;
	}

	/**
	 * Getter for the damageLevel of Movable Cyborg Gameobject.
	 * @return the damageLevel of Movable Cyborg Gameobject.
	 */
	public int get_damageLevel() {
		return _damageLevel;
	}

	/**
	 *Setter for the damageLevel of Movable Cyborg Gameobject. 
	 * @param the new damageLevel of Movable Cyborg Gameobject.
	 */
	public void set_damageLevel(int damageLevel) {

		if (damageLevel >= this._MAX_DAMAGE_LEVEL) {
			this._damageLevel = this._MAX_DAMAGE_LEVEL;
		}
		else 
		this._damageLevel = damageLevel;
	}

	/**
	 * Getter for the lastBaseReached of Movable Cyborg Gameobject.
	 * @return the lastBaseReached of Movable Cyborg Gameobject.
	 */
	public int get_lastBaseReached() {
		return _lastBaseReached;
	}

	/**
	 *Setter for the lastBaseReached of Movable Cyborg Gameobject. 
	 * @param the new lastBaseReached of Movable Cyborg Gameobject.
	 */
	public void set_lastBaseReached(int lastBaseReached) {
		this._lastBaseReached = lastBaseReached;
		if (this._lastBaseReached < this._maxBaseReached)
			_maxBaseReached = lastBaseReached;
	}

	/**
	 * Getter for the steeringDirection of Movable Cyborg Gameobject.
	 * @return the steeringDirection of Movable Cyborg Gameobject.
	 */
	public int get_steeringDirection() {
		return _steeringDirection;
	}

	/**
	 *Setter for the steeringDirection of Movable Cyborg Gameobject. 
	 * @param the new steeringDirection of Movable Cyborg Gameobject.
	 */
	public void set_steeringDirection(int steeringDirection) {
		int newDir = this._steeringDirection += steeringDirection;
		
		if (steeringDirection == 5) {
			if (newDir <= _MAX_STEERING_DIRECTION ) {
				 this._steeringDirection = newDir;
			}
			else { 
				this._steeringDirection = _MAX_STEERING_DIRECTION;
			}
		}
		else if (steeringDirection == -5) {
			if (newDir >= ((-1) *_MAX_STEERING_DIRECTION)) {
				this._steeringDirection = newDir;
			}
			else {
				this._steeringDirection = (-1)*_MAX_STEERING_DIRECTION;
			}
		}
	}
	
	/**
	 * Getter for the maxBaseReached of Movable Cyborg Gameobject.
	 * @return the maxBaseReached of Movable Cyborg Gameobject.
	 */
	public int get_maxBaseReached() {
		return _maxBaseReached;
	}

	/**
	 *Setter for the maxBaseReached of Movable Cyborg Gameobject. 
	 * @param the new maxBaseReached of Movable Cyborg Gameobject.
	 */
	public void set_maxBaseReached(int max) {
		this._maxBaseReached = max;
	}
	
	
	/**
	 * Getter for the MAX_SPEED of Movable Cyborg Gameobject.
	 * @return the MAX_SPEED of Movable Cyborg Gameobject.
	 */
	public int get_MAX_SPEED() {
		return _MAX_SPEED;
	}


	/**
	 * Getter for the MAX_STEERING_DIRECTION of Movable Cyborg Gameobject.
	 * @return the MAX_STEERING_DIRECTION of Movable Cyborg Gameobject.
	 */
	public int get_MAX_STEERING_DIRECTION() {
		return _MAX_STEERING_DIRECTION;
	}


	/**
	 * Getter for the _MAX_DAMAGE_LEVEL of Movable Cyborg Gameobject.
	 * @return the _MAX_DAMAGE_LEVEL of Movable Cyborg Gameobject.
	 */
	public int get_MAX_DAMAGE_LEVEL() {
		return _MAX_DAMAGE_LEVEL;
	}


	/*
	 * fades the color of the Movable Cyborg Gameobject.
	 * used when Movable Cyborg Gameobject takes damage. 
	 */
	public void fadeColor() {	
		int fade = (this._MAX_DAMAGE_LEVEL-this._damageLevel);
		int r= ColorUtil.red(super.get_color()) * (((1 - fade) / 255 + fade) * 255); 
		int g= ColorUtil.green(super.get_color()) * (((1 - fade) / 255 + fade) * 255);
		int b= ColorUtil.blue(super.get_color()) * (((1 - fade) / 255 + fade) * 255);
		 super.set_color(r, g, b);		  
	}
	
	/**
	 * Overides the Movable Gameobject
	 * sets limitations for Movable Cyborg Gameobject from moving 
	 * based off of speed, energy level, and damage. 
	 */
	@Override
	public void move() {
		int newEng = this._energyLevel - this._energyConsumptionRate;
		if(newEng>=0 && !(this._damageLevel >= this._MAX_DAMAGE_LEVEL) && !(super.get_speed()==0)) {
			this._energyLevel -= this._energyConsumptionRate;
			steer();
			super.move();
		}
	}

	/**
	 * Overides the Gameobject
	 * sets limitations for Movable Cyborg Gameobject from exceeding MAXSPEED.
	 */
	@Override
	public void set_speed(int speed) {
		if (this._energyLevel == 0 ) {
			super.set_speed(0);
		}
		if (speed <= this._MAX_SPEED && (!(speed<0))) {
			super.set_speed(speed * (_MAX_DAMAGE_LEVEL - this._damageLevel) / 100);	
		}
		else if (speed > this._MAX_SPEED) {
			super.set_speed(this._MAX_SPEED);
		}
		else if (speed<0) {
			super.set_speed(0);
		}		
	}
	
	/**
	 * Overides the Gameobject
	 * prevents Movable Cyborg Gameobject from changing size.
	 */
	@Override
	public void set_size(int size) {
		if (super.get_size() == 0)
			super.set_size(size);
	}

	/**
	 * Implements the Steerable interface 
	 * updates the heading of the Movable Cyborg Gameobject
	 * based of steering direction. 
	 */
	@Override
	public void steer() {
		super.set_heading(this._steeringDirection + super.get_heading());

	}

	/**
	 *Overides toString for Movable Cyborg Gameobject 
	 */
	@Override
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = parentDesc + "\n\t"
				+ "Last Base= " + this._lastBaseReached + ", " 
				+ "Steering Dir= " + this._steeringDirection + ", " 
				+ "MaxSpeed= " + this._MAX_SPEED + ", " 
				+ "Energy Level= " + this._energyLevel + ", "
				+ "Damage Level= " + this._damageLevel + " ";
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
		return test._damageLevel == this._damageLevel 
				&& test._energyConsumptionRate == this._energyConsumptionRate
				&& test._energyLevel == this._energyLevel 
				&& test._lastBaseReached == this._lastBaseReached
				&& test._maxBaseReached == this._maxBaseReached 
				&& test._MAX_SPEED == this._MAX_SPEED
				&& test._steeringDirection == this._steeringDirection 
				&& test.get_color() == this.get_color()
				&& test.get_point() == this.get_point() 
				&& test.get_size() == this.get_size()
				&& test.get_speed() == this.get_speed() 
				&& test.get_heading() == this.get_heading(); 
	}

	/**
	 *Overides hascode for Movable Cyborg Gameobject 
	 */
	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + this._damageLevel;
		hash = 31 * hash + this._energyConsumptionRate;
		hash = 31 * hash + this._energyLevel;
		hash = 31 * hash + this._lastBaseReached;
		hash = 31 * hash + this._maxBaseReached;
		hash = 31 * hash + this._MAX_SPEED;
		hash = 31 * hash + this._steeringDirection;
		hash = 31 * hash + this.get_color();
		hash = 31 * hash + this.get_size();
		hash = 31 * hash + this.get_speed();
		hash = 31 * hash + this.get_heading();
		hash = 31 * hash + Float.floatToIntBits(this.get_point().getX());
		hash = 31 * hash + Float.floatToIntBits(this.get_point().getY());
		return hash;
	}

}
