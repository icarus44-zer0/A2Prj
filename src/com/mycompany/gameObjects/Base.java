package com.mycompany.gameObjects;
/** Represents a Base GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameWorld;


public class Base extends Selectable{
	private int sequenceNumber;
	private boolean collisionFlag = false;

	/**
	 * 
	 * @param sequenceNumber
	 * @param size
	 * @param point
	 * @param color
	 */
	public Base(int sequenceNumber, int size, Point point, int color, int UUID) {	
		super(size, point, color, UUID);
		this.sequenceNumber = sequenceNumber;
	}
		
	/**
	 * Getter for the sequenceNumber of a Fixed Base Gameobject.
	 * @return the sequenceNumber of a Fixed Base Gameobject.
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 *Setter for the sequenceNumber of a Fixed Base Gameobject. 
	 * @param the new the sequenceNumber of a Fixed Base Gameobject.
	 */
	public void setSequenceNumber(int sequenceNumber) {
		if (this.sequenceNumber == 0 )
			this.sequenceNumber = sequenceNumber;
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
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		g.setColor(super.getcolor());
		int xLoc = (int) (pCmpRelPrnt.getX()+ this.getPoint().getX());
		int yLoc = (int) (pCmpRelPrnt.getY()+ this.getPoint().getY());
		int sise = this.getSize();
		int [] traingleX = {xLoc-sise/2, xLoc+sise/2, xLoc};
		int [] traingleY = {yLoc-sise/2, yLoc-sise/2, yLoc+sise/2};
		
		g.fillPolygon(traingleX, traingleY, 3);
		
		g.setColor(ColorUtil.BLACK);
		g.drawString("" + sequenceNumber, xLoc, yLoc);
		
		if(isSelected()) {
			super.draw(g, pCmpRelPrnt);
		}
	}

	@Override
	public void handleCollision(GameObject otherObject) {
		
	}
	
	
	public boolean isCollisionFlag() {
		return collisionFlag;
	}

	public void setCollisionFlag(boolean b) {
		collisionFlag = true;
		
	}
}
