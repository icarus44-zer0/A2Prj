/**
 * 
 */
package com.mycompany.command;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * @author Icarus44
 *
 */
public class NPC_CollisionCommand extends Command {
	private GameWorld targetGameWorld;
	
	/**
	 * @param command
	 */
	public NPC_CollisionCommand(String command, GameWorld gameworld) {
		super(command);
		this.targetGameWorld = gameworld;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		targetGameWorld.pCyborg_cyborgCollision();
		super.actionPerformed(evt);
	}
}

