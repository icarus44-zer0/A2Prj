package com.mycompany.gameObjects;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.mycompany.a3.GameWorld;

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
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		g.setColor(this.getcolor());
		int xLoc = (int) (pCmpRelPrnt.getX()+ this.getPoint().getX());
		int yLoc = (int) (pCmpRelPrnt.getY()+ this.getPoint().getY());
		int s = this.getSize();
		g.fillRect(xLoc-s/2, yLoc-s/2, this.getSize(), this.getSize());
		super.draw(g, pCmpRelPrnt);
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
			setsteeringDirection(0);
		}
	}

	/**
	 * 
	 * @param GameObject
	 * @return
	 */
	@Override
	public boolean collidesWith(GameObject otherObject) {
		float b_xMax = this.getPoint().getX() + this.getSize()/2 + 50;
		float b_xMin = this.getPoint().getX() - this.getSize()/2 + 50;
		float b_yMax = this.getPoint().getY() + this.getSize()/2 + 50;
		float b_yMin = this.getPoint().getY() - this.getSize()/2 + 50;
		float c_xLoc = otherObject.getPoint().getX();
		float c_yLoc = otherObject.getPoint().getY();
		return (c_xLoc <= b_xMax && c_xLoc >= b_xMin && c_yLoc <= b_yMax && c_yLoc >= b_yMin);
	}
	
	

	@Override
	public void handleCollision(GameObject otherObject) {
		GameWorld gWorld = GameWorld.getInstance();

		if (otherObject instanceof NPCCyborg) {
			NPCCyborg new_name = (NPCCyborg) otherObject;
			this.setheading(180 -this.getheading());
			new_name.setheading(180- new_name.getheading());
			gWorld.pCyborgcyborgCollision(new_name);
		}
	
		else if (otherObject instanceof Drone) {
			Drone new_name = (Drone) otherObject;
			this.setheading(180 -this.getheading());
			new_name.setheading(180- new_name.getheading());
			gWorld.pCyborgdroneCollison(new_name);
		}
		
		else if (otherObject instanceof EnergyStation) {
			EnergyStation new_name = (EnergyStation) otherObject;
			gWorld.pCyborgeStationCollison(new_name);
		}
		
		else if (otherObject instanceof Base) {
			Base new_name = (Base) otherObject;
			gWorld.pCyborgBaseCollison(new_name);
			
		}
	}
	
}

