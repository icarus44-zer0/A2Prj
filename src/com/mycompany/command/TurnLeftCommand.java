/**
 * 
 */
package com.mycompany.command;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * @author Josh Poe
 *
 */
public class TurnLeftCommand extends Command {
	private GameWorld targetGameWorld;
	
	public TurnLeftCommand(String command, GameWorld gameworld) {
		super(command);
		this.targetGameWorld = gameworld;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		targetGameWorld.pCyborg_turnLeft();
		super.actionPerformed(evt);
	}
	
}

