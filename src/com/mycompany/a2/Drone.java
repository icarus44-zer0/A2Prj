package com.mycompany.a2;
/** Represents a Drone GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/
import com.codename1.charts.models.Point;
import java.util.Random;

public class Drone extends Movable {
	private int _damageLevel;

	public Drone() {
	    Random random = new Random();
	    float min = 0f;
	    float max = 1000f;
	    float r1 = min + random.nextFloat() * (max - min);
	    float r2 = min + random.nextFloat() * (max - min);
	    
		final int r=128, g=0, b=128; //Purple 
	    
	    super.set_point(new Point(r1,r2));    
	    super.set_size(random.nextInt(50-10)+10);
		super.set_color(r,g,b);
		super.set_heading(random.nextInt(359)+1);
		super.set_speed(random.nextInt(10-5)+5);
		set_damageLevel(0);
	}
	
	/**
	 * Getter for the damage of a Movable Drone Gameobject.
	 * @return the damage of the gameobject.
	 */
	public int get_damageLevel() {
		return _damageLevel;
	}

	/**
	 *Setter for the damage of a Movable Drone Gameobject. 
	 * @param the new damage of a gameobject.
	 */
	public void set_damageLevel(int damage) {
		this._damageLevel = damage;
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
		int ret = super.get_heading();
		super.set_heading(ret + delta);
		super.move();
	}



	/**
	 * prevents changes to speed greater than five 
	 * limits speed to 10 
	 * ingnores all other inputs. 
	 */
	@Override
	public void set_speed(int speed) {
		if (super.get_speed() == 0 && speed >= 5 && speed <= 10)
			super.set_speed(speed);
	}
	
	
	/**
	 * prevemts Movable Drone Gameobject from changing their color.
	 */
	@Override
	public void set_color(int r, int g, int b) {
		if ( super.get_color() == 0 )
			super.set_color(r, g, b);
	}

	/**
	 * overides toString for Movable Drone Gameobject.
	 */
	@Override
	public String toString() {
		String parentDesc = super.toString();	
		String myDesc = parentDesc
		+"Damage Level= " + this._damageLevel + " ";
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
			return test._damageLevel == this._damageLevel 
					&& test.get_color() == this.get_color()
					&& test.get_point() == this.get_point() 
					&& test.get_size() == this.get_size()
					&& test.get_speed() == this.get_speed() 
					&& test.get_heading() == this.get_heading(); 
	}

	/**
	 * overides hascode for Movable Drone Gameobject.
	 */
	@Override
	public int hashCode() {
		int hash = 23;
		hash = 31 * hash + this._damageLevel;
		hash = 31 * hash + this.get_color();
		hash = 31 * hash + this.get_size();
		hash = 31 * hash + this.get_speed();
		hash = 31 * hash + this.get_heading();
		hash = 31 * hash + Float.floatToIntBits(this.get_point().getX());
		hash = 31 * hash + Float.floatToIntBits(this.get_point().getY());
		return hash;
	}
		
}
