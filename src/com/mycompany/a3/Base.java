package com.mycompany.a3;
/** Represents a Base GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/
import com.codename1.charts.models.Point;
import java.util.Random;


public class Base extends Fixed{
	private int sequenceNumber;
	
	/**
	 * 
	 * @param sequenceNumber
	 * @param size
	 * @param point
	 * @param color
	 */
	public Base(int sequenceNumber, int size, Point point, int color) {	
		super(size, point, color);
		this.sequenceNumber = sequenceNumber;
	}
		
	/**
	 * Getter for the sequenceNumber of a Fixed Base Gameobject.
	 * @return the sequenceNumber of a Fixed Base Gameobject.
	 */
	public int getsequenceNumber() {
		return sequenceNumber;
	}

	/**
	 *Setter for the sequenceNumber of a Fixed Base Gameobject. 
	 * @param the new the sequenceNumber of a Fixed Base Gameobject.
	 */
	public void setsequenceNumber(int sequenceNumber) {
		if (this.sequenceNumber == 0 )
			this.sequenceNumber = sequenceNumber;
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
	 * overides toString for Fixed Base Gameobject. 
	 */
	@Override
	public String toString() {
		String parentDesc = super.toString();	
		String myDesc = parentDesc
		+"Sequence Number= " + this.sequenceNumber + " ";
		return myDesc;		
	}
	
	/**
	 * overides equals for Fixed Base Gameobject. 
	 */
	@Override
	public boolean equals(Object obj) {
		  if (!(obj instanceof Base)) {
	            return false;
	        }
		  	Base test = (Base) obj;
			return test.sequenceNumber == this.sequenceNumber 
					&& test.getcolor() == this.getcolor()
					&& test.getpoint() == this.getpoint() 
					&& test.getsize() == this.getsize();
	}

	/**
	 * overides hascode for Fixed Base Gameobject. 
	 */
	@Override
	public int hashCode() {
		int hash = 13;
		hash = 31 * hash + this.sequenceNumber;
		hash = 31 * hash + this.getcolor();
		hash = 31 * hash + this.getsize();
		hash = 31 * hash + Float.floatToIntBits(this.getpoint().getX());
		hash = 31 * hash + Float.floatToIntBits(this.getpoint().getY());
		return hash;
	}
	
}
