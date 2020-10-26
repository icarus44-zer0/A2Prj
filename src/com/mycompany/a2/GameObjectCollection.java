package com.mycompany.a2;

import java.util.ArrayList;

public class GameObjectCollection implements ICollection {
	private ArrayList<GameObject> gameObjectCollection;

	public GameObjectCollection() {
		gameObjectCollection = new ArrayList<>();
	}

	/**
	 * 
	 */
	@Override
	public void add(Object object) {
		gameObjectCollection.add((GameObject) object);

	}

	/**
	 * 
	 */
	@Override
	public IIterator getIterator() {
		return new gameObjectIterator();
	}

	/**
	 * 
	 * @author jp
	 *
	 */
	private class gameObjectIterator implements IIterator {
		private int index;

		/**
		 * 
		 */
		public gameObjectIterator() {
			index = -1;
		}

		/**
		 * 
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
