package com.mycompany.a2;
import com.codename1.charts.models.Point;
/** Represents a Abstract Movable GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/

public abstract class Movable extends GameObject {
	private int _heading;
	private int _speed;
	
	
	/**
	 * 
	 */
	public Movable() {
		_heading = 0;
		_speed = 0;
	}
	
	/**
	 * Controls movement of the gameobject 
	 * move() causes the object to update its location based on its current heading and speed
	 * takes into account the location relative to the gmae map with the top left corner of
	 * the screen being set to (0,0).
	 */
	public void move() {	
		
		int velocity = _speed; //TODO add time when time is varable 
		int angle = 90 - this._heading;
		
		float x = super.get_point().getX();
		float y = super.get_point().getY();
		
		x += velocity * Math.cos(angle * Math.PI /180);
		y += velocity * Math.sin(angle * Math.PI /180);
		
		if (x < 0 || x > GameWorld.get_Instance().get_width()) {
			this._heading = 180 - this._heading;  
			 angle = this._heading;
			 x = super.get_point().getX();
			 y = super.get_point().getY();
			 x += velocity * Math.cos(angle * Math.PI /180);
			 y += velocity * Math.sin(angle * Math.PI /180);
		}
		else if (y <0 ||y > GameWorld.get_Instance().get_hieght()) {
			this._heading = 360 - this._heading; 
			 angle = this._heading;
			 x = super.get_point().getX();
			 y = super.get_point().getY();
			 x += velocity * Math.cos(angle * Math.PI /180);
			 y += velocity * Math.sin(angle * Math.PI /180);
			 
		}
			super.set_point(new Point(x,y));
		}
	
		
	/**
	 *Getter for the heading of a movable gameobject. 
	 * @return the heading of a movable gameobject.
	 */
	public int get_heading() {
		return _heading;
	}

	/**
	 * Setter for the heading of a movable gameobject. 
	 * @param the new heading of a movable gameobject.
	 */
	public void set_heading(int heading) {
		this._heading = heading;
		if (this._heading >= 360)
			this._heading -= 360;
		if(this._heading < 0)
			this._heading += 360; 
	}

	/**
	 *Getter for the speed of a movable gameobject. 
	 * @return the speed of a movable gameobject.
	 */
	public int get_speed() {
		return _speed;
	}

	/**
	 * Setter for the speed of a movable gameobject. 
	 * @param the new speed of a movable gameobject.
	 */
	public void set_speed(int speed) {
		this._speed = speed;
	}

	/**
	 * overides the to string method for movable gameobject.
	 */
	@Override
	public String toString() {
		String parentDesc = super.toString();	
		String myDesc = parentDesc 
		+ "Speed= " + this._speed + ", "
		+ "Heading= " + this._heading + ", ";
		return myDesc;
	}
	
	
}
