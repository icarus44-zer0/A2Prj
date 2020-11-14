package com.mycompany.a3;
import com.codename1.charts.models.Point;

/** Represents a Abstract Movable GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/
public abstract class Movable extends GameObject {
	private int heading;
	private int speed;
	private GameWorld gameWorld;
	//private MapViewContainer container = new MapViewContainer();
	
	/**
	 * 
	 */
	public Movable(int heading, int speed, int size, Point point, int color) {
		super(size, point, color);
		this.heading = heading;
		this.speed = speed;
	}
	
	/**
	 * Controls movement of the gameobject 
	 * move() causes the object to update its location based on its current heading and speed
	 * takes into account the location relative to the gmae map with the top left corner of
	 * the screen being set to (0,0).
	 */
	public void move() {
		gameWorld = GameWorld.getInstance();
		int velocity = speed; //TODO add time when time is varable 
		int angle = this.heading;
	
		float x = super.getpoint().getX();
		float y = super.getpoint().getY();
		
		double deltaX =  Math.cos(Math.toRadians(angle))*velocity; 
		double deltaY =  Math.sin(Math.toRadians(angle))*velocity;
		
		double nextX = x + deltaX;
		double nextY = y + deltaY;
			
		if (nextX < 0 || nextX > gameWorld.getGameWidth()) {
			 angle -= 180;
			 deltaX =  Math.cos(Math.toRadians(angle))*velocity; 
			 nextX = x + deltaX;
			 setheading(angle);
		}
		if (nextY < 0 || nextY > gameWorld.getGameHeight()) {
			 angle -= 180;
			 deltaY =  Math.sin(Math.toRadians(angle))*velocity;
			 nextY = y + deltaY;
			 setheading(angle);
		}
			super.setX((float)nextX);
			super.setY((float)nextY);
		}
			
	/**
	 *Getter for the heading of a movable gameobject. 
	 * @return the heading of a movable gameobject.
	 */
	public int getheading() {
		return heading;
	}

	/**
	 * Setter for the heading of a movable gameobject. 
	 * @param the new heading of a movable gameobject.
	 */
	public void setheading(int heading) {
		this.heading = heading;
		if (this.heading >= 360)
			this.heading -= 360;
		if(this.heading < 0)
			this.heading += 360; 
	}

	/**
	 *Getter for the speed of a movable gameobject. 
	 * @return the speed of a movable gameobject.
	 */
	public int getspeed() {
		return speed;
	}

	/**
	 * Setter for the speed of a movable gameobject. 
	 * @param the new speed of a movable gameobject.
	 */
	public void setspeed(int speed) {
		this.speed = speed;
	}

	/**
	 * overides the to string method for movable gameobject.
	 */
	@Override
	public String toString() {
		String parentDesc = super.toString();	
		String myDesc = parentDesc 
		+ "Speed= " + this.speed + ", "
		+ "Heading= " + this.heading + ", ";
		return myDesc;
	}
	
	
}
