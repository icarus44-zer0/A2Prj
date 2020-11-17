package com.mycompany.gameObjects;
/** Represents a Drone GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import java.util.Random;

public class Drone extends Movable {
	private int damageLevel;

	/**
	 * 
	 * @param damageLevel
	 * @param heading
	 * @param speed
	 * @param size
	 * @param point
	 * @param color
	 */
	public Drone(int damageLevel, int heading, int speed, int size, Point point, int color) {
		super(heading, speed, size, point, color);
		this.damageLevel = damageLevel;
	}
	


	/**
	 * Getter for the damage of a Movable Drone Gameobject.
	 * @return the damage of the gameobject.
	 */
	public int getdamageLevel() {
		return damageLevel;
	}

	/**
	 *Setter for the damage of a Movable Drone Gameobject. 
	 * @param the new damage of a gameobject.
	 */
	public void setdamageLevel(int damage) {
		this.damageLevel = damage;
	}

	/**
	 * Drones are moveable (but not steerable) objects which fly over the track. 
	 * They add (or subtract) small random values (e.g., 5 degrees)
	 * to their heading while they move so as to not run in a straight line.
	 */
	@Override
	public void move() {
		Random random = new Random();
		int numbers[] = new int[] {-5,5};
		int delta = numbers[random.nextInt(2)];
		int ret = super.getheading();
		super.setheading(ret + delta);
		super.move();
	}

	/**
	 * prevents changes to speed greater than five 
	 * limits speed to 10 
	 * ingnores all other inputs. 
	 */
	@Override
	public void setspeed(int speed) {
		if (super.getspeed() == 0 && speed >= 5 && speed <= 10)
			super.setspeed(speed);
	}
	
	/**
	 * prevemts Movable Drone Gameobject from changing their color.
	 */
	@Override
	public void setColor(int r, int g, int b) {
		if ( super.getcolor() == 0 )
			super.setColor(r, g, b);
	}
	
	
	@Override
	public void draw(Graphics g, Point p1) {
		g.setColor(this.getcolor());
		int xLoc = (int) (this.getPoint().getX() + p1.getX() - (getSize()/2));
		int yLoc = (int) (this.getPoint().getY() + p1.getY() - (getSize()/2));
		g.drawArc(xLoc, yLoc, this.getSize(), this.getSize(), 0, 360);
		g.fillArc(xLoc, yLoc, this.getSize(), this.getSize(), 0, 360);
		g.setColor(ColorUtil.BLACK);
		g.drawString(this.getClass().getSimpleName(), xLoc, yLoc);
	}
		
	/**
	 * overides toString for Movable Drone Gameobject.
	 */
	@Override
	public String toString() {
		String parentDesc = super.toString();	
		String myDesc = parentDesc
		+"Damage Level= " + this.damageLevel + " ";
		return myDesc;
	}

	/**
	 * overides equals opperator for Movable Drone Gameobject.
	 */
	@Override
	public boolean equals(Object obj) {
		  if (!(obj instanceof Drone)) {
	            return false;
	        }
		  	Drone test = (Drone) obj;
			return test.damageLevel == this.damageLevel 
					&& test.getcolor() == this.getcolor()
					&& test.getPoint() == this.getPoint() 
					&& test.getSize() == this.getSize()
					&& test.getspeed() == this.getspeed() 
					&& test.getheading() == this.getheading(); 
	}

	/**
	 * overides hascode for Movable Drone Gameobject.
	 */
	@Override
	public int hashCode() {
		int hash = 23;
		hash = 31 * hash + this.damageLevel;
		hash = 31 * hash + this.getcolor();
		hash = 31 * hash + this.getSize();
		hash = 31 * hash + this.getspeed();
		hash = 31 * hash + this.getheading();
		hash = 31 * hash + Float.floatToIntBits(this.getPoint().getX());
		hash = 31 * hash + Float.floatToIntBits(this.getPoint().getY());
		return hash;
	}
		
}
