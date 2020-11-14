/**
 * 
 */
package com.mycompany.a3;

import com.codename1.charts.models.Point;

/**
 * @author jp
 *
 */
public class NPCCyborg extends Cyborg {
	private IStrategy strategy;
	/**
	 * @param energyLevel
	 * @param energyConsumptionRate
	 * @param damageLevel
	 * @param lastBaseReached
	 * @param maxBaseReached
	 * @param steeringDirection
	 * @param heading
	 * @param speed
	 * @param size
	 * @param point
	 * @param color
	 */
	public NPCCyborg(int energyLevel, int energyConsumptionRate, int damageLevel, 
			int maxDamageLevel, int lastBaseReached,int maxBaseReached, 
			int steeringDirection, int heading, int speed, 
			int size, Point point, int color) {
		
		super(energyLevel, energyConsumptionRate, damageLevel, 
				maxDamageLevel, lastBaseReached, maxBaseReached, 
				steeringDirection, heading, speed,
				size, point, color);
	}
	
	/**
	 * 
	 */
	public void invokeStrategy() {
		strategy.apply();
	}

	/**
	 * 
	 */
	@Override
	public void move() {
		invokeStrategy();
		super.move();
	}
	
	/**
	 * 
	 * @return
	 */
	public IStrategy getStrategy() {
		return strategy;
	}
	
	/**
	 * 
	 * @param strategy
	 */
	public void setStrategy(IStrategy strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = parentDesc
				+ "Strategy= " + strategy.getClass().getSimpleName();
		return myDesc;
	}	
}
