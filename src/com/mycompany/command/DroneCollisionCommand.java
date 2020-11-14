/**
 * 
 */
package com.mycompany.command;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * @author Icarus44
 *
 */
public class DroneCollisionCommand extends Command {
	private GameWorld targetGameWorld;
	
	/**
	 * @param command
	 */
	public DroneCollisionCommand(String command, GameWorld gameworld) {
		super(command);
		this.targetGameWorld = gameworld;
	}
	

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		targetGameWorld.pCyborgdroneCollison();
		super.actionPerformed(evt);
	}
}