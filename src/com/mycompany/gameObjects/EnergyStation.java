package com.mycompany.gameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameWorld;
/** Represents a Energy Station GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/

public class EnergyStation extends Selectable {
	private final int MAXCAPACITY = 200;
	private int capacity;
	private boolean collisionFlag = false; 
	
	/**
	 * 
	 * @param capacity
	 * @param size
	 * @param point
	 * @param color
	 */
	public EnergyStation(int capacity, int size, Point point, int color, int UUID) {
		super(size, point, color, UUID);
		
	    this.capacity = capacity;
	}
		
	/**
	 * Getter for the capacity of a Fixed Energy Station Gameobject.
	 * @return the capacity of a Fixed Energy Station Gameobject.
	 */
	public int getcapacity() {
		return capacity;
	}

	/**
	 * Setter for the capacity of a Fixed Energy Station Gameobject.
	 * @param the new the capacity of a Fixed Energy Station Gameobject.
	 */
	public void setcapacity(int capacity) {
		if((capacity <= MAXCAPACITY)) 
			this.capacity = capacity;
		}

	/**
	 * prevents size from being changed after instantiation. 
	 */
	@Override
	public void setSize(int size) {
		if ( super.getSize() == 0) {
			super.setSize(size);
		}
	}

	/**
	 * prevents location from being changed after instantiation. 
	 */
	@Override
	public void setPoint(Point point) {
		if (super.getPoint() == null )
			super.setPoint(point);
	}
	
	/**
	 * prevents color from being changed after instantiation. 
	 */
	@Override
	public void setColor(int r, int g, int b) {
		if (super.getcolor() == 0)
			super.setColor(r, g, b);
	}
	
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(this.getcolor());
		int xLoc = (int) (this.getPoint().getX() + pCmpRelPrnt.getX() - (getSize()/2));
		int yLoc = (int) (this.getPoint().getY() + pCmpRelPrnt.getY() - (getSize()/2));
		g.drawArc(xLoc, yLoc, this.getSize(), this.getSize(), 0, 360);
		g.fillArc(xLoc, yLoc, this.getSize(), this.getSize(), 0, 360);
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + capacity, xLoc+getSize()/2, yLoc+getSize()/2);
		
		if(isSelected()) {
			super.draw(g, pCmpRelPrnt);
		}
		
	}

	/**
	 * overides toString for Fixed Energy Station Gameobject. 
	 */
	@Override
	public String toString() {
		String parentDesc = super.toString();	
		String myDesc = parentDesc
		+ "Max Capacity= " + this.MAXCAPACITY + ", "
		+ "Current Capacity= " + this.capacity + " ";	
		return myDesc;	
		
	}

	@Override
	public void handleCollision(GameObject otherObject) {

	}
	
	public boolean isCollisionFlag() {
		return collisionFlag;
		
	}

	public void setCollisionFlag(boolean b) {
		collisionFlag = true;
	}
}
