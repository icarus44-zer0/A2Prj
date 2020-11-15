/**
 * 
 */
package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

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
	
	@Override
	public void draw(Graphics g, Point p1) {
		g.setColor(this.getcolor());
		int xLoc = (int) (this.getPoint().getX() + p1.getX() - (getSize()/2));
		int yLoc = (int) (this.getPoint().getY() + p1.getY() - (getSize()/2));
		g.drawArc(xLoc, yLoc, this.getSize(), this.getSize(), 0, 360);
		g.fillArc(xLoc, yLoc, this.getSize(), this.getSize(), 0, 360);
		g.setColor(ColorUtil.BLACK);
		g.drawString(this.getClass().getSimpleName(), xLoc, yLoc);
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
