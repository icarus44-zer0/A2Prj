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
	public EnergyStation(int capacity, int size, Point point, int color) {
		super(size, point, color);
		
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
		super.draw(g, pCmpRelPrnt);
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
	
	/**
	 * overides equals for Fixed Energy Station Gameobject. 
	 */
//	@Override
//	public boolean equals(Object obj) {
//		  if (!(obj instanceof EnergyStation)) {
//	            return false;
//	        }
//		  EnergyStation test = (EnergyStation) obj;
//			return test.capacity == this.capacity
//					&& test.MAXCAPACITY == this.MAXCAPACITY
//					&& test.getcolor() == this.getcolor()
//					&& test.getPoint() == this.getPoint() 
//					&& test.getSize() == this.getSize();
//	}
//
//	/**
//	 * overides hascode for Fixed Energy Station Gameobject.
//	 */
//	@Override
//	public int hashCode() {
//		int hash = 19;
//		hash = 31 * hash + this.capacity;
//		hash = 31 * hash + this.MAXCAPACITY;
//		hash = 31 * hash + this.getcolor();
//		hash = 31 * hash + this.getSize();
//		hash = 31 * hash + Float.floatToIntBits(this.getPoint().getX());
//		hash = 31 * hash + Float.floatToIntBits(this.getPoint().getY());
//		return hash;
//	}

	@Override
	public void setSelected(boolean isSelected) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Point p1, Point p2) {
		// TODO Auto-generated method stub
		return false;
	}
	
//	/**
//	 * 
//	 * @param GameObject
//	 * @return
//	 */
//	@Override
//	public boolean collidesWith(GameObject otherObject) {
//		float b_xMax = this.getPoint().getX() + this.getSize()/2 + 50;
//		float b_xMin = this.getPoint().getX() - this.getSize()/2 + 50;
//		float b_yMax = this.getPoint().getY() + this.getSize()/2 + 50;
//		float b_yMin = this.getPoint().getY() - this.getSize()/2 + 50;
//		float c_xLoc = otherObject.getPoint().getX();
//		float c_yLoc = otherObject.getPoint().getY();
//		return (c_xLoc <= b_xMax && c_xLoc >= b_xMin && c_yLoc <= b_yMax && c_yLoc >= b_yMin);
//	}


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
