package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
/** Represents a Energy Station GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/

public class EnergyStation extends Fixed {
	private final int MAXCAPACITY = 200;
	private int capacity;
	
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
	@Override
	public boolean equals(Object obj) {
		  if (!(obj instanceof EnergyStation)) {
	            return false;
	        }
		  EnergyStation test = (EnergyStation) obj;
			return test.capacity == this.capacity
					&& test.MAXCAPACITY == this.MAXCAPACITY
					&& test.getcolor() == this.getcolor()
					&& test.getPoint() == this.getPoint() 
					&& test.getSize() == this.getSize();
	}

	/**
	 * overides hascode for Fixed Energy Station Gameobject.
	 */
	@Override
	public int hashCode() {
		int hash = 19;
		hash = 31 * hash + this.capacity;
		hash = 31 * hash + this.MAXCAPACITY;
		hash = 31 * hash + this.getcolor();
		hash = 31 * hash + this.getSize();
		hash = 31 * hash + Float.floatToIntBits(this.getPoint().getX());
		hash = 31 * hash + Float.floatToIntBits(this.getPoint().getY());
		return hash;
	}

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
}
