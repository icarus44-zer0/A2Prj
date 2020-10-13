package com.mycompany.a2;
/** Represents a Base GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/
import com.codename1.charts.models.Point;
import java.util.Random;


public class Base extends Fixed{
	private int _sequenceNumber;
	
	public Base(int sequenceNumber) {		
	    Random random = new Random();
	    float min = 0f;
	    float max = 1000f;
	    float r1 = min + random.nextFloat() * (max - min);
	    float r2 = min + random.nextFloat() * (max - min);
	    
		final int r=0, g=128, b=0; //Green 
	    
	    super.set_point(new Point(r1,r2));
	    super.set_size(50);		
		super.set_color(r,g,b);
		set_sequenceNumber(sequenceNumber);
	}
		
	/**
	 * Getter for the sequenceNumber of a Fixed Base Gameobject.
	 * @return the sequenceNumber of a Fixed Base Gameobject.
	 */
	public int get_sequenceNumber() {
		return _sequenceNumber;
	}

	/**
	 *Setter for the sequenceNumber of a Fixed Base Gameobject. 
	 * @param the new the sequenceNumber of a Fixed Base Gameobject.
	 */
	public void set_sequenceNumber(int _sequenceNumber) {
		if (this._sequenceNumber == 0 )
			this._sequenceNumber = _sequenceNumber;
	}

	/**
	 * prevents size from being changed after instantiation. 
	 */
	@Override
	public void set_size(int size) {
		if ( super.get_size() == 0) {
			super.set_size(size);
		}
	}

	/**
	 * prevents location from being changed after instantiation. 
	 */
	@Override
	public void set_point(Point point) {
		if (super.get_point() == null )
			super.set_point(point);
	}
	
	/**
	 * prevents color from being changed after instantiation. 
	 */
	@Override
	public void set_color(int r, int g, int b) {
		if (super.get_color() == 0)
			super.set_color(r, g, b);
	}

	/**
	 * overides toString for Fixed Base Gameobject. 
	 */
	@Override
	public String toString() {
		String parentDesc = super.toString();	
		String myDesc = parentDesc
		+"Sequence Number= " + this._sequenceNumber + " ";
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
			return test._sequenceNumber == this._sequenceNumber 
					&& test.get_color() == this.get_color()
					&& test.get_point() == this.get_point() 
					&& test.get_size() == this.get_size();
	}

	/**
	 * overides hascode for Fixed Base Gameobject. 
	 */
	@Override
	public int hashCode() {
		int hash = 13;
		hash = 31 * hash + this._sequenceNumber;
		hash = 31 * hash + this.get_color();
		hash = 31 * hash + this.get_size();
		hash = 31 * hash + Float.floatToIntBits(this.get_point().getX());
		hash = 31 * hash + Float.floatToIntBits(this.get_point().getY());
		return hash;
	}
	
}
