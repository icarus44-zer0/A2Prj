package com.mycompany.gameObjects;
import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.mycompany.a3.ICollider;
import com.mycompany.a3.IDrawable;

/** Represents an Abstract GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/

/**
 * @author Icarus44
 *
 */
public abstract class GameObject implements IDrawable, ICollider{
	private int size;
	private Point point;
	private int color;
	private int UUID; 
	
	
	
	/**
	 * GameObject Constructor 
	 */
	public GameObject(int size, Point point, int color, int UUID) {
		this.UUID = UUID;
		this.size = size;
		this.point = point;
		this.color = color;	
	}


	/**
	 * Getter for the gameobject size.
	 * @return the size of the gameobject.
	 */
	public int getSize() {
		return size;
	}

	/**
	 *Setter for the size of a gameobject. 
	 * @param the new size of a gameobject.
	 */
	public void setSize(int size) {
			this.size = size;
	}

	/**
	 * Getter for the location of a gameobject. 
	 * @return the location of a gameobject.
	 */
	public Point getPoint() {
		return point;
	}

	/**
	 * Setter for the location of a gameobject.  
	 * @param the new location of a gameobject.
	 */
	public void setPoint(Point point) {
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
	public void setColor(int r, int g, int b) {
		this.color = ColorUtil.rgb(r, g, b);
	}
	
	

	/**
	 * @return the uUID
	 */
	public int getUUID() {
		return UUID;
	}


	/**
	 * @param uUID the uUID to set
	 */
	public void setUUID(int uUID) {
		UUID = uUID;
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
		
		double xVal = this.getPoint().getX();
		double rxVal = Math.round(xVal*10.0)/10.0;
		double yVal = this.getPoint().getY();
		double ryVal = Math.round(yVal*10.0)/10.0;
		
		String myDesc =
		"Location= " + "(" + rxVal + "," + ryVal + "), " 
		+ "Color= " + "[" 
		+ ColorUtil.red(this.getcolor()) + ","
		+ ColorUtil.green(this.getcolor()) + ","
		+ ColorUtil.blue(this.getcolor()) + "], "
		+ "Size= " + this.getSize() + ", ";
		return myDesc ;
	}

	/**
	 * 
	 */
	@Override
	public boolean collidesWith(GameObject obj2) {
		boolean ret = false;
		int r1 = size / 2;
		int r2 = obj2.getSize() / 2;
		
		double cx1 = point.getX();
		double cy1 = point.getY();
		
		double cx2 = obj2.getPoint().getX();
		double cy2 = obj2.getPoint().getY();
		
		double dx = cx1 - cx2;
		double dy = cy1 - cy2;
		
		double dist = (dx * dx + dy * dy);
		double radsSqaured= (r1 * r1 + 2 * r1 * r2 + r2 * r2);
		
		if (dist <= radsSqaured) {ret = true;}
		return ret;		
	}

	/**
	 * 
	 */
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(ColorUtil.BLACK);
		int xLoc = (int) (pCmpRelPrnt.getX()+ this.getPoint().getX());
		int yLoc = (int) (pCmpRelPrnt.getY()+ this.getPoint().getY());
		int s = this.getSize();
		g.drawRect(xLoc-s/2, yLoc-s/2,  this.getSize() , this.getSize());

	}

}
