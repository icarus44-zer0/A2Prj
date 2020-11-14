/**
 * 
 */
package com.mycompany.command;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.NPCCyborg;

/**
 * @author Icarus44
 *
 */
public class NPCCollisionCommand extends Command {
	private GameWorld targetGameWorld;
	
	/**
	 * @param command
	 */
	public NPCCollisionCommand(String command, GameWorld gameworld) {
		super(command);
		this.targetGameWorld = gameworld;
	}

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		NPCCyborg empty = null;
		targetGameWorld.pCyborgcyborgCollision(empty);
		super.actionPerformed(evt);
	}
}

