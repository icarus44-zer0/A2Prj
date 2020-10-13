package com.mycompany.a2;
/** Represents a Abstarct Fixed GameObject.
* @author  Josh Poe 
* @version 1.0
* @since   202-09-28 
*/
public abstract class Fixed extends GameObject{
		
		public Fixed() {
			
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
}
