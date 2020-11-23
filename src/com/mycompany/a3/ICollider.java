package com.mycompany.a3;

import java.util.ArrayList;


import com.mycompany.gameObjects.GameObject;

public interface ICollider {
	ArrayList<ICollider> collideVector = new ArrayList<ICollider>();;


	boolean collidesWith(GameObject otherObject);
	void handleCollision(GameObject otherObject);
	
	
	/**
	 * @return the collideVector
	 */
	public default ArrayList<ICollider> getCollideList() {
		return collideVector;
	}

}
