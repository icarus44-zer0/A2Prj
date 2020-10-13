package com.mycompany.a2;
import java.util.Random;
import com.codename1.charts.models.Point;
/** Represents a Energy Station GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/

public class EnergyStation extends Fixed {
	private final int _MAXCAPACITY = 200;
	private int _capacity;
	
	public EnergyStation() {
	    Random random = new Random();
	    
		final int r=255, g=165, b=0; //Orange 
	    float min = 0f;
	    float max = 1000f;
	    float r1 = min + random.nextFloat() * (max - min);
	    float r2 = min + random.nextFloat() * (max - min);
	    
	    super.set_point(new Point(r1,r2));
	    super.set_size(random.nextInt(50-10)+10);	
		super.set_color(r,g,b);	
		
		set_capacity(this.get_size()*random.nextInt(3-1)+1);	
	}
		

	/**
	 * Getter for the capacity of a Fixed Energy Station Gameobject.
	 * @return the capacity of a Fixed Energy Station Gameobject.
	 */
	public int get_capacity() {
		return _capacity;
	}

	/**
	 * Setter for the capacity of a Fixed Energy Station Gameobject.
	 * @param the new the capacity of a Fixed Energy Station Gameobject.
	 */
	public void set_capacity(int capacity) {
		if((capacity <= _MAXCAPACITY)) 
			this._capacity = capacity;
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
	 * overides toString for Fixed Energy Station Gameobject. 
	 */
	@Override
	public String toString() {
		String parentDesc = super.toString();	
		String myDesc = parentDesc
		+ "Max Capacity= " + this._MAXCAPACITY + ", "
		+ "Current Capacity= " + this._capacity + " ";	
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
			return test._capacity == this._capacity
					&& test._MAXCAPACITY == this._MAXCAPACITY
					&& test.get_color() == this.get_color()
					&& test.get_point() == this.get_point() 
					&& test.get_size() == this.get_size();
	}

	/**
	 * overides hascode for Fixed Energy Station Gameobject.
	 */
	@Override
	public int hashCode() {
		int hash = 19;
		hash = 31 * hash + this._capacity;
		hash = 31 * hash + this._MAXCAPACITY;
		hash = 31 * hash + this.get_color();
		hash = 31 * hash + this.get_size();
		hash = 31 * hash + Float.floatToIntBits(this.get_point().getX());
		hash = 31 * hash + Float.floatToIntBits(this.get_point().getY());
		return hash;
	}
}
