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

	/**
	 * 
	 */
	public Movable(int heading, int speed, int size, Point point, int color, int UUID) {
		super(size, point, color, UUID);
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
			int size = this.getSize();
			double xLoc = this.getPoint().getX();
			double yLoc = this.getPoint().getY();
			if (xLoc-size/2 <= 0) {
				this.heading = -heading;
				this.getPoint().setX((float) xLoc + size/8);
				this.getPoint().setY((float) yLoc);
			}
			else if (xLoc+size/2 >= maxX) {
				this.heading = -heading;
				this.getPoint().setX((float) xLoc - size/8);
				this.getPoint().setY((float) yLoc);
			}
			else if (yLoc-size/2 <= 0) {	
				this.heading = 180 - heading;
				this.getPoint().setX((float) xLoc);
				this.getPoint().setY((float) yLoc + size/8);
			}
			else if (yLoc+size/2 >= maxY) {
				this.heading = 180 - heading;
				this.getPoint().setX((float) xLoc);
				this.getPoint().setY((float) yLoc - size/8);
			}

	} 

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

	
	protected void movableCollision(GameObject otherObject) {
		double maxX = otherObject.getPoint().getX() + otherObject.getSize()/2;
		double maxY = otherObject.getPoint().getY() + otherObject.getSize()/2;

		
		int size = this.getSize();
		double xLoc = this.getPoint().getX();
		double yLoc = this.getPoint().getY();
		if (xLoc-size/2 <= maxX) {
			this.heading = -heading;
			this.getPoint().setX((float) xLoc + size/8);
			this.getPoint().setY((float) yLoc);
		}
		else if (xLoc+size/2 > maxX) {
			this.heading = -heading;
			this.getPoint().setX((float) xLoc - size/8);
			this.getPoint().setY((float) yLoc);
		}
		else if (yLoc-size/2 <= maxY) {	
			this.heading = 180 - heading;
			this.getPoint().setX((float) xLoc);
			this.getPoint().setY((float) yLoc + size/8);
		}
		else if (yLoc+size/2 > maxY) {
			this.heading = 180 - heading;
			this.getPoint().setX((float) xLoc);
			this.getPoint().setY((float) yLoc - size/8);
		}
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
