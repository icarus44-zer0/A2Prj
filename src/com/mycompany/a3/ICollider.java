package com.mycompany.a3;

import com.mycompany.gameObjects.GameObject;

public interface ICollider {

	boolean collidesWith(GameObject otherObject);
	void handleCollision(GameObject otherObject);
}
