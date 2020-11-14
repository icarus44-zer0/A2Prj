package com.mycompany.a3;

import com.codename1.charts.models.Point;
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
	public void setsize(int size) {
		if ( super.getsize() == 0) {
			super.setsize(size);
		}
	}

	/**
	 * prevents location from being changed after instantiation. 
	 */
	@Override
	public void setpoint(Point point) {
		if (super.getpoint() == null )
			super.setpoint(point);
	}
	
	/**
	 * prevents color from being changed after instantiation. 
	 */
	@Override
	public void setcolor(int r, int g, int b) {
		if (super.getcolor() == 0)
			super.setcolor(r, g, b);
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
					&& test.getpoint() == this.getpoint() 
					&& test.getsize() == this.getsize();
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
		hash = 31 * hash + this.getsize();
		hash = 31 * hash + Float.floatToIntBits(this.getpoint().getX());
		hash = 31 * hash + Float.floatToIntBits(this.getpoint().getY());
		return hash;
	}
}
