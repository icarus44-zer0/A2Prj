package com.mycompany.gameObjects;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;
import com.mycompany.a3.ISelectable;

/** Represents a Abstarct Fixed GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/
public abstract class Selectable extends GameObject implements ISelectable{
		 boolean isSelected = false; 
		/**
		 * 
		 * @param size
		 * @param point
		 * @param color
		 */
		public Selectable(int size, Point point, int color, int UUID) {
			super(size, point, color, UUID);
		}
		
		/**
		 * overides the toString for Fixed game objects
		 */
		@Override
		public String toString() {
			String parentDesc = super.toString();	
			String myDesc = "";
			return myDesc + parentDesc ;
		}	
		
	
		public void setSelected(boolean isSelected) {
			this.isSelected  = isSelected;
		}


		public boolean isSelected() {
			return isSelected;
		}

		@Override
		public void draw(Graphics g, Point pCmpRelPrnt){
			if(isSelected) {
				super.draw(g, pCmpRelPrnt);
			}
		}

		public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
				int pxLoc = (int) pPtrRelPrnt.getX(); 
				int pyLoc = (int) pPtrRelPrnt.getY(); 
				int xLoc = (int) (pCmpRelPrnt.getX() + this.getPoint().getX());
				int yLoc = (int) (pCmpRelPrnt.getY() + this.getPoint().getY());
				int size = this.getSize();
				return(pxLoc >= xLoc-size/2) && (pxLoc <= xLoc+ size/2) && (pyLoc >= yLoc-size/2) && (pyLoc <= yLoc+size/2);
		}
}
