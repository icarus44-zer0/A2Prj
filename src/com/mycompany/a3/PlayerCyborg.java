package com.mycompany.a3;

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
}

