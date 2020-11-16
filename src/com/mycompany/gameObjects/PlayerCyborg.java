package com.mycompany.gameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class PlayerCyborg extends Cyborg {
	private static PlayerCyborg instance = null;

	public static PlayerCyborg getInstance() {
        return instance; 
	}
	
	/**
	 * 
	 * @param energyLevel
	 * @param energyConsumptionRate
	 * @param damageLevel
	 * @param maxDamageLevel
	 * @param lastBaseReached
	 * @param maxBaseReached
	 * @param steeringDirection
	 * @param heading
	 * @param speed
	 * @param size
	 * @param point
	 * @param color
	 * @return
	 */
	public static PlayerCyborg getInstance(int energyLevel, int energyConsumptionRate, int damageLevel, int maxDamageLevel, int lastBaseReached, 
			int maxBaseReached, int steeringDirection,
			int heading, int speed, int size, Point point, int color) {
		 if (instance == null) 
	        { 
			 instance = new PlayerCyborg(energyLevel, energyConsumptionRate, damageLevel, maxDamageLevel, lastBaseReached, 
					 maxBaseReached, steeringDirection, heading, speed, size, point, color);
	        } 
	        return instance; 
	}
	
	/**
	 * 
	 * @param energyLevel
	 * @param energyConsumptionRate
	 * @param damageLevel
	 * @param maxDamagelevel
	 * @param lastBaseReached
	 * @param maxBaseReached
	 * @param steeringDirection
	 * @param heading
	 * @param speed
	 * @param size
	 * @param point
	 * @param color
	 */
	private PlayerCyborg(int energyLevel, int energyConsumptionRate, int damageLevel, int maxDamagelevel, 
			int lastBaseReached, int maxBaseReached, int steeringDirection,
			int heading, int speed, int size, Point point, int color) {
		
		//Todo get location
		
		super(energyLevel, energyConsumptionRate, damageLevel, maxDamagelevel, lastBaseReached, 
				maxBaseReached, steeringDirection, heading, speed, size, point, color);
		
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
	 * Overides the Movable Gameobject
	 * sets limitations for Movable Cyborg Gameobject from moving 
	 * based off of speed, energy level, and damage. 
	 */
	@Override
	public void move() {
		int newEng = energyLevel - energyConsumptionRate;
		if(newEng>=0 && !(damageLevel >= maxDamageLevel) && !(super.getspeed()==0)) {
			energyLevel -= energyConsumptionRate;
			steer();
			super.move();
		}
	}
}

