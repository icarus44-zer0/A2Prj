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
	private Point pCmpRelPrnt;
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

	
//	/**
//	 * 
//	 * @param otherObject
//	 * @return
//	 */
//	public boolean collidesWith(GameObject otherObject) {
//		boolean hasCollided = false;
//		
//		int x1 = (int) this.getPoint().getX();
//		int y1 = (int) this.getPoint().getY();
//		
//		int x2 = (int)((GameObject)otherObject).getPoint().getX() + (otherObject.getSize()/2);
//		int y2 = (int)((GameObject)otherObject).getPoint().getY() + (otherObject.getSize()/2);
//		
//		int dx = x1 - x2;
//		int dy = y1 - y2;
//		
//		int dist = (dx * dx + dy * dy);
//		
//		int r1= this.getSize() / 2;
//		int r2= ((GameObject)otherObject).getSize() / 2;
//		
//		int radiiSquared= ((r1*r1) + (2*r1*r2) + (r2*r2));
//		
//		if ((dist + 25) <= radiiSquared) { 
//			hasCollided = true ; 
//		}
//		
//		return hasCollided;
//	}

	
	
	@Override
	public boolean collidesWith(GameObject obj) {
		boolean retval = false;
		
		double thisCenterX = point.getX();
		double thisCenterY = point.getY();
		
		double otherCenterX = ((GameObject)obj).getPoint().getX();
		double otherCenterY = ((GameObject)obj).getPoint().getY();
		
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		
		double distBetweenCentersSqr = (dx * dx + dy * dy);
		
		int thisRadius= this.getSize() / 2;
		int otherRadius= ((GameObject)obj).getSize() / 2;
		
		int radiiSqr= (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr) 
		{
			retval = true;
		}
		
		return retval;		
	}
//	
//	/**
//	 * 
//	 * @param GameObject
//	 * @return
//	 */
//	public boolean collidesWith(GameObject obj) {
//		
//		float b_xMax = (float) (this.getPoint().getX() + (size/2));
//		float b_xMin = (float) (this.getPoint().getX() - (size/2));
//		float b_yMax = (float) (this.getPoint().getY() + (size/2));
//		float b_yMin = (float) (this.getPoint().getY() - (size/2));
//		
//		int thisXLocMa = (int) (this.getPoint().getX());
//		int thisYLoc = (int) (this.getPoint().getY());
//		
//		
//		float c_xMax = (float) (obj.getPoint().getX() + (size/2));
//		float c_xMin = (float) (obj.getPoint().getX() - (size/2));
//		float c_yMax = (float) (obj.getPoint().getY() + (size/2));
//		float c_yMin = (float) (obj.getPoint().getY() - (size/2));
//		
//		
//		//(R1 < L2) OR (L1 > R2) -> No Left/Right overlap
//		//(T2 < B1) OR (T1 < B2) -> No Top/Bottom overlap
//		
//		if(b_xMax > c_xMin) {
//			
//		}
//		if() {
//			
//		}
//		if() {
//			
//		}if() {
//			
//			
//		}
//		
//		return (c_xLoc <= b_xMax && c_xLoc >= b_xMin && c_yLoc <= b_yMax && c_yLoc >= b_yMin);
//	}

	
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		this.pCmpRelPrnt = pCmpRelPrnt;
		
		g.setColor(ColorUtil.BLACK);
		int xLoc = (int) (pCmpRelPrnt.getX()+ this.getPoint().getX());
		int yLoc = (int) (pCmpRelPrnt.getY()+ this.getPoint().getY());
		int s = this.getSize();
		g.drawRect(xLoc-s/2, yLoc-s/2,  this.getSize() , this.getSize());
		
//		
//		
//		
//		
//		g.setColor(ColorUtil.BLACK);
//				
//		int xLoc = (int) (pCmpRelPrnt.getX()+ point.getX());
//		int yLoc = (int) (pCmpRelPrnt.getY()+ point.getY());
//		
//		double hitBoxX = (point.getX() + size/2 + (.15)*(size/2));
//		double hitBoxY=  (point.getY() + size/2 + (.15)*(size/2));
//		
//		g.drawRect(xLoc,yLoc, (int) hitBoxX , (int) hitBoxY);
		
	}
//	
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
//		"Location= " + "(" + rxVal + ", " + ryVal + "), " ;
//		return myDesc ;
//	}
//	
}
