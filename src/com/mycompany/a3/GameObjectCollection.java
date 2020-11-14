package com.mycompany.a3;

import java.util.ArrayList;

public class GameObjectCollection implements ICollection {
	private ArrayList<GameObject> gameObjectCollection;

	public GameObjectCollection() {
		gameObjectCollection = new ArrayList<>();
	}

	/**
	 * adds objects to the gameobjects collection 
	 */
	@Override
	public void add(Object object) {
		gameObjectCollection.add((GameObject) object);

	}

	/**
	 * retuns the iterator object 
	 * @return IIterator
	 */
	@Override
	public IIterator getIterator() {
		return new gameObjectIterator();
	}

	/**
	 *Private inner calss to implement the iterator interface 
	 */
	private class gameObjectIterator implements IIterator {
		private int index;

		/**
		 * sets iterator to -1 
		 */
		public gameObjectIterator() {
			index = -1;
		}

		/**
		 * used by iterator pattern 
		 */
		public boolean hasNext() {
		if (gameObjectCollection.size ( ) <= 0) 
				return false;
			if (index == gameObjectCollection.size() - 1)
				return false;
			return true;
		}

		/**
		 * 
		 */
		public Object getNext() {
			index++;
			return (gameObjectCollection.get(index));
		}
	}
}
