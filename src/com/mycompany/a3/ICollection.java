package com.mycompany.a3;

public interface ICollection {
	
	/**
	 * 
	 * @param newObject
	 */
	public void add(Object newObject);
	
	/**
	 * 
	 * @return
	 */
	public IIterator getIterator();
}
