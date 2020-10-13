package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;

/** Represents an Abstract GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/

public abstract class GameObject {
	private int _size;
	private Point _point;
	private int _color;

	/**
	 * GameObject Constructor 
	 */
	public GameObject() {

	}

	/**
	 * Getter for the gameobject size.
	 * @return the size of the gameobject.
	 */
	public int get_size() {
		return _size;
	}

	/**
	 *Setter for the size of a gameobject. 
	 * @param the new size of a gameobject.
	 */
	public void set_size(int size) {
			this._size = size;
	}

	/**
	 * Getter for the location of a gameobject. 
	 * @return the location of a gameobject.
	 */
	public Point get_point() {
		return _point;
	}

	/**
	 * Setter for the location of a gameobject.  
	 * @param the new location of a gameobject.
	 */
	public void set_point(Point point) {
		this._point = point;
	}

	/**
	 *Getter for the color of the gameobject. 
	 * @return the color of the gameobject.
	 */
	public int get_color() {
		return this._color;
	}

	/**
	 * Setter for the color of the gameobject.  
	 * @param the new color of the gameobject.
	 */
	public void set_color(int r, int g, int b) {
		this._color = ColorUtil.rgb(r, g, b);
	}
	
	

	/**
	 * equals for game objects  
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	/**
	 * tostring for game objects  
	 */
	@Override
	public String toString() {	
		
		double xVal = this.get_point().getX();
		double rxVal = Math.round(xVal*10.0)/10.0;
		double yVal = this.get_point().getY();
		double ryVal = Math.round(yVal*10.0)/10.0;
		
		String myDesc =
		"Location= " + "(" + rxVal + "," + ryVal + "), " 
		+ "Color= " + "[" 
		+ ColorUtil.red(this.get_color()) + ","
		+ ColorUtil.green(this.get_color()) + ","
		+ ColorUtil.blue(this.get_color()) + "], "
		+ "Size= " + this.get_size() + ", ";
		return myDesc ;
	}
}
