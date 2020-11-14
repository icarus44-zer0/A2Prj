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
public class EstatCollisionCommand extends Command {
	private GameWorld targetGameWorld;
	
	
	/**
	 * @param command
	 */
	public EstatCollisionCommand(String command, GameWorld gameworld) {
		super(command);
		this.targetGameWorld = gameworld;
	}
	
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		targetGameWorld.pCyborgeStationCollison();
		super.actionPerformed(evt);
	}
}
