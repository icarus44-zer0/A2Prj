package com.mycompany.a2;

import com.codename1.charts.models.Point;

/** Represents a Abstarct Fixed GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/
public abstract class Fixed extends GameObject{
		
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
		public void setpoint(Point point) {}
		
		
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
