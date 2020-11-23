package com.mycompany.gameObjects;

import com.codename1.charts.models.Point;
import com.mycompany.a3.GameWorld;


/**
 * Represents a Abstract Movable GameObject.
 * 
 * @author Josh Poe
 * @version 1.0
 * @since 202-09-28
 */
public abstract class Movable extends GameObject {
	private int heading;
	private int speed;
	private GameWorld gameWorld;
	// private MapViewContainer container = new MapViewContainer();

	/**
	 * 
	 */
	public Movable(int heading, int speed, int size, Point point, int color) {
		super(size, point, color);
		this.heading = heading;
		this.speed = speed;
	}
	
	
	
	
	/**
	 * @see com.mycompany.a3.GameObject.IMove#move()
	 * Move this Opponent subject to its speed and direction. If it hit the boundary, 
	 * its direction will be changed according to the reflection angle. After each move, the direction
	 * will be sightly changed random so that this Opponent does not move as a straight line.
	 */
	public void move() {
		GameWorld gameWorld = GameWorld.getInstance();
		double maxX = gameWorld.getGameWidth();
		double maxY = gameWorld.getGameHeight();
		double elapsedTime = 1;
		Point nextPoint = new Point(0,0);
		
		double theta = Math.toRadians(heading);
		float nextX = (float) (Math.sin(theta) * speed * elapsedTime + this.getPoint().getX());
		float nextY = (float) (Math.cos(theta) * speed * elapsedTime + this.getPoint().getY());
		
		nextPoint.setX(nextX);
		nextPoint.setY(nextY);
		
		this.setPoint(nextPoint);

		// rebound to direction based  when hit the boundary
			int s = this.getSize();
			double x = this.getPoint().getX();
			double y = this.getPoint().getY();
			int reboundOffset = s/8;
			if (x-s/2 <= 0) {
				this.heading = -heading;
				this.getPoint().setX((float) x+reboundOffset);
				this.getPoint().setY((float) y);
			}
			else if (x+s/2 >= maxX) {
				this.heading = -heading;
				this.getPoint().setX((float) x-reboundOffset);
				this.getPoint().setY((float) y);
			}
			else if (y-s/2 <= 0) {	
				this.heading = 180 - heading;
				this.getPoint().setX((float) x);
				this.getPoint().setY((float) y+reboundOffset);
			}
			else if (y+s/2 >= maxY) {
				this.heading = 180 - heading;
				this.getPoint().setX((float) x);
				this.getPoint().setY((float) y-reboundOffset);
			}

	} //move
	

//		/**
//		 * mymove function 
//		 */
//	public void move() {
//		gameWorld = GameWorld.getInstance();
//		int size = this.getSize();
//		int velocity = speed;
//		double time = 1; //gameWorld.gettimeElapsed();
//		int angle = 90 - this.heading;
//
//		float x = super.getPoint().getX();
//		float y = super.getPoint().getY();
//
//		double deltaX = Math.cos(Math.toRadians(angle)) * velocity * time;
//		double deltaY = Math.sin(Math.toRadians(angle)) * velocity * time;
//
//		double nextX = x + deltaX;
//		double nextY = y + deltaY;
//
//
//		 // left
//		if (nextX <= ((size / 2))) {
//			angle = 180 - angle;
//			deltaX = Math.cos(Math.toRadians(angle)) * velocity;
//			nextX = x + deltaX;
//			heading = angle;
//		}
//		// right
//		 if (nextX >= gameWorld.getGameWidth() - ((size / 2))) {
//			angle = 180 - angle;
//			deltaX = Math.cos(Math.toRadians(angle)) * velocity;
//			nextX = x + deltaX;
//			heading = angle;
//		}
//		// bottom
//		 if (nextY <= ((size / 2))) {
//			angle = 180 - angle;
//			deltaY = Math.sin(Math.toRadians(angle)) * velocity;
//			nextY = y + deltaY;
//			heading = angle;
//		}
//		// top
//		 if (nextY >= gameWorld.getGameHeight() - ((size / 2))) {
//			angle = 180 - angle;
//			deltaY = Math.sin(Math.toRadians(angle)) * velocity;
//			nextY = y + deltaY;
//			heading = angle;
//		}
//
//		super.setX((float) nextX);
//		super.setY((float) nextY);
//	}
//	
	
//	public void move() {
//		GameWorld gameWorld = GameWorld.getInstance();
//		double mapWidth = gameWorld.getGameWidth();
//		double mapHeight =  gameWorld.getGameHeight();
//		double time = .02;
//		
//		
//		Point oldLocation = this.getPoint(); //get current location and store here
//		
//		Point newLocation = new Point(0,0); // new location initialized
//		
//	
//		int properAngle = 90-heading; //angle because coordinate plane is shifted
//		double deltaX = 0;
//		double deltaY = 0;
//		
//		
//		
//		/*
//		 * direction * speed results in units of distance 
//		 */
//		if(heading == 0 || heading == 180) //if only vertical movement
//		{
//			deltaY = Math.sin(Math.toRadians(properAngle)) * speed;
//		}
//		else if( heading == 90 || heading == 270 ) //only horizontal
//			deltaX = Math.cos(Math.toRadians(properAngle)) * speed;
//		else // if both vertical and horizontal movement required
//		{
//			deltaX = Math.cos(Math.toRadians(properAngle))*speed; 
//			deltaY = Math.sin(Math.toRadians(properAngle)) * speed;
//		}
//		
//		/*
//		 * Changing new location x and y by using oldLocation x and y
//		 * and adding delta X and delta Y
//		 */
//		
//		newLocation.setX((float) deltaX + oldLocation.getX()); 
//		newLocation.setY((float) deltaY+oldLocation.getY());
//		
//		
//		if (newLocation.getX() >= mapWidth)
//		{
//			newLocation.setX((float) (newLocation.getX() - mapWidth));
//		}
//		else if (newLocation.getX() <= 0.0)
//		{
//			newLocation.setX( (float) mapWidth - Math.abs(newLocation.getX()));
//		}
//		
//		if (newLocation.getY() >= mapHeight)
//		{
//			newLocation.setY((float) (newLocation.getY()- mapHeight));
//		}
//		else if (newLocation.getY() <= 0.0)
//		{
//			newLocation.setY( (float) mapHeight - Math.abs(newLocation.getY()));
//			
//		}
//		newLocation.setX(newLocation.getX());
//		newLocation.setY(newLocation.getY());
//		
//
//		super.setPoint(newLocation); //changing location to new location		
//	}
	


	/**
	 * Getter for the heading of a movable gameobject.
	 * 
	 * @return the heading of a movable gameobject.
	 */
	public int getheading() {
		return heading;
	}

	/**
	 * Setter for the heading of a movable gameobject.
	 * 
	 * @param the new heading of a movable gameobject.
	 */
	public void setheading(int heading) {
		this.heading = heading;
		if (this.heading >= 360)
			this.heading -= 360;
		if (this.heading < 0)
			this.heading += 360;
	}

	/**
	 * Getter for the speed of a movable gameobject.
	 * 
	 * @return the speed of a movable gameobject.
	 */
	public int getspeed() {
		return speed;
	}

	/**
	 * Setter for the speed of a movable gameobject.
	 * 
	 * @param the new speed of a movable gameobject.
	 */
	public void setspeed(int speed) {
		this.speed = speed;
	}

	
	protected void movableCollision() {
		heading = 180- heading;
		float x = super.getPoint().getX();
		float y = super.getPoint().getY();

		double deltaX = Math.cos(Math.toRadians(heading)) * speed;
		double deltaY = Math.sin(Math.toRadians(heading)) * speed;

		double nextX = x + deltaX;
		double nextY = y + deltaY;
		
		super.setX((float) nextX);
		super.setY((float) nextY);
		move();
	}

	/**
	 * overides the to string method for movable gameobject.
	 */
	@Override
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = parentDesc + "Speed= " + this.speed + ", " + "Heading= " + this.heading + ", ";
		return myDesc;
	}

}
