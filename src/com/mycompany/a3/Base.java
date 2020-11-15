package com.mycompany.a3;
/** Represents a Base GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;


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
					&& test.getPoint() == this.getPoint() 
					&& test.getSize() == this.getSize();
	}

	/**
	 * overides hascode for Fixed Base Gameobject. 
	 */
	@Override
	public int hashCode() {
		int hash = 13;
		hash = 31 * hash + this.sequenceNumber;
		hash = 31 * hash + this.getcolor();
		hash = 31 * hash + this.getSize();
		hash = 31 * hash + Float.floatToIntBits(this.getPoint().getX());
		hash = 31 * hash + Float.floatToIntBits(this.getPoint().getY());
		return hash;
	}

	@Override
	public void draw(Graphics g, Point p1) {
		g.setColor(this.getcolor());
		int xLoc = (int) (this.getPoint().getX() + p1.getX() - (getSize()/2));
		int yLoc = (int) (this.getPoint().getY() + p1.getY() - (getSize()/2));
		g.drawArc(xLoc, yLoc, this.getSize(), this.getSize(), 0, 360);
		g.fillArc(xLoc, yLoc, this.getSize(), this.getSize(), 0, 360);
		
		g.setColor(ColorUtil.BLACK);
		g.drawString(this.getClass().getSimpleName() + sequenceNumber, xLoc, yLoc);
		
	}

	@Override
	public void setSelected(boolean isSelected) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Point p1, Point p2) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
