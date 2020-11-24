package com.mycompany.a3;

import java.util.ArrayList;


import com.mycompany.gameObjects.GameObject;

public interface ICollider {
	ArrayList<Integer> collideList = new ArrayList<Integer>();;


	boolean collidesWith(GameObject otherObject);
	void handleCollision(GameObject otherObject);
	
	
	/**
	 * @return the collideVector
	 */
	public default ArrayList<Integer> getCollideList() {
		return collideList;
	}

}
