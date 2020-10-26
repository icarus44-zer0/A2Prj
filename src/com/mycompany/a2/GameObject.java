package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.models.Point;

/** Represents an Abstract GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/

public abstract class GameObject {
	private int size;
	private Point point;
	private int color;
	
	
	/**
	 * GameObject Constructor 
	 */
	public GameObject(int size, Point point, int color) {
		this.size = size;
		this.point = point;
		this.color = color;	
	}

	/**
	 * Getter for the gameobject size.
	 * @return the size of the gameobject.
	 */
	public int getsize() {
		return size;
	}

	/**
	 *Setter for the size of a gameobject. 
	 * @param the new size of a gameobject.
	 */
	public void setsize(int size) {
			this.size = size;
	}

	/**
	 * Getter for the location of a gameobject. 
	 * @return the location of a gameobject.
	 */
	public Point getpoint() {
		return point;
	}

	/**
	 * Setter for the location of a gameobject.  
	 * @param the new location of a gameobject.
	 */
	public void setpoint(Point point) {
		this.point = point;
	}
	
	/**
	 * 
	 * @param x
	 */
	protected void setX(float x) {
		this.point.setX(x);
	}
	
	/**
	 * 
	 * @param y
	 */
	protected void setY(float y) {
		this.point.setY(y);
	}

	/**
	 *Getter for the color of the gameobject. 
	 * @return the color of the gameobject.
	 */
	public int getcolor() {
		return this.color;
	}

	/**
	 * Setter for the color of the gameobject.  
	 * @param the new color of the gameobject.
	 */
	public void setcolor(int r, int g, int b) {
		this.color = ColorUtil.rgb(r, g, b);
	}
	
	

	/**
	 * equals for game objects  
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
//	/**
//	 * tostring for game objects  
//	 */
//	@Override
//	public String toString() {	
//		
//		double xVal = this.getpoint().getX();
//		double rxVal = Math.round(xVal*10.0)/10.0;
//		double yVal = this.getpoint().getY();
//		double ryVal = Math.round(yVal*10.0)/10.0;
//		
//		String myDesc =
//		"Location= " + "(" + rxVal + "," + ryVal + "), " 
//		+ "Color= " + "[" 
//		+ ColorUtil.red(this.getcolor()) + ","
//		+ ColorUtil.green(this.getcolor()) + ","
//		+ ColorUtil.blue(this.getcolor()) + "], "
//		+ "Size= " + this.getsize() + ", ";
//		return myDesc ;
//	}
	
	
	/**
	 * tostring for game objects  
	 */
	@Override
	public String toString() {	
		
		double xVal = this.getpoint().getX();
		double rxVal = Math.round(xVal*10.0)/10.0;
		double yVal = this.getpoint().getY();
		double ryVal = Math.round(yVal*10.0)/10.0;
		
		String myDesc =
		"Location= " + "(" + rxVal + ", " + ryVal + "), " ;
		return myDesc ;
	}
	
}
