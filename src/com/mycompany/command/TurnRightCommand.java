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
public class TurnRightCommand extends Command {
	private GameWorld targetGameWorld;
	
	public TurnRightCommand(String command, GameWorld gameworld) {
		super(command);
		this.targetGameWorld = gameworld;
	}
	
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		targetGameWorld.pCyborgturnRight();
		super.actionPerformed(evt);
	}
	
}
