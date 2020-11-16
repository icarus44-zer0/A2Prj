package com.mycompany.gameObjects;

import com.codename1.charts.models.Point;
import com.mycompany.a3.ISelectable;

/** Represents a Abstarct Fixed GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/
public abstract class Fixed extends GameObject implements ISelectable{
		
		/**
		 * 
		 * @param size
		 * @param point
		 * @param color
		 */
		public Fixed(int size, Point point, int color) {
			super(size, point, color);
		}
		
		@Override
		public void setPoint(Point point) {}
		
		
		/**
		 * overides the toString for Fixed game objects
		 */
		@Override
		public String toString() {
			String parentDesc = super.toString();	
			String myDesc = "";
			return myDesc + parentDesc ;
		}		
}
