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
public class BreakCommand extends Command {
	private GameWorld targetGameWorld;
	
	/**
	 * @param command
	 */
	public BreakCommand(String command, GameWorld gameworld) {
		super(command);
		this.targetGameWorld = gameworld;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		targetGameWorld.pCyborg_brake();
		super.actionPerformed(evt);
	}
}