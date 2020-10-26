package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;

public class NPCAttackStratagy implements IStrategy {
	private NPCCyborg npcCyborg;
	private PlayerCyborg playerCyborg;
	private Point playerLocation;
	private Point npcLocation;

	/**
	 * 
	 * @param npcCyborg
	 */
	public NPCAttackStratagy(NPCCyborg npcCyborg) {
		this.npcCyborg = npcCyborg;
		playerCyborg = PlayerCyborg.getInstance();
	}
	
	/**
	 * 
	 */
	@Override
	public void apply() {
		setLocations();
		
		if (isCollision(npcCyborg, playerCyborg)) {
			GameWorld gameWorld = GameWorld.getInstance();
			
			gameWorld.pCyborgcyborgCollision(npcCyborg);
			
			setLocations();
		}
		
		Point npcLocation = npcCyborg.getpoint();
		float npcXcord = npcLocation.getX();
		float npcYcord = npcLocation.getY();
		
		Point playerLocation = playerCyborg.getpoint();
		
		float playerXcord = playerLocation.getX();
		float playerYcord = playerLocation.getY();
		
		float xDist = playerXcord - npcXcord;
		float yDist = playerYcord - npcYcord;
		
		double angrad = MathUtil.atan2(xDist, yDist);
		double angdeg = Math.toDegrees(angrad);
		
		double idealheading = 90 - angdeg;
		int heading = (int) Math.round(idealheading);
		
		npcCyborg.setheading(heading);
	}
	
	/**
	 * 
	 * @param npcCyborg
	 * @param playerCyborg
	 * @return
	 */
	private boolean isCollision(NPCCyborg npcCyborg, PlayerCyborg playerCyborg) {
		float b_xMax = playerCyborg.getpoint().getX() + playerCyborg.getsize()/2;
		float b_xMin = playerCyborg.getpoint().getX() - playerCyborg.getsize()/2;
		float b_yMax = playerCyborg.getpoint().getY() + playerCyborg.getsize()/2;
		float b_yMin = playerCyborg.getpoint().getY() - playerCyborg.getsize()/2;
		float c_xLoc = npcCyborg.getpoint().getX();
		float c_yLoc = npcCyborg.getpoint().getY();
		
		return (c_xLoc <= b_xMax && c_xLoc >= b_xMin && c_yLoc <= b_yMax && c_yLoc >= b_yMin);
	}

	/**
	 * 
	 */
	private void setLocations() {
		npcLocation = npcCyborg.getpoint();
		playerLocation = playerCyborg.getpoint();
	}
}
