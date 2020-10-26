/**
 * 
 */
package com.mycompany.command;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameObject;
import com.mycompany.a2.GameObjectCollection;
import com.mycompany.a2.GameWorld;
import com.mycompany.a2.IIterator;
import com.mycompany.a2.NPCAttackStratagy;
import com.mycompany.a2.NPCCyborg;
import com.mycompany.a2.NPCNextBaseStratagy;

/**
 * @author Icarus44
 *
 */
public class ChangeStratsCommand extends Command {
	private GameWorld targetGameWorld;
	
	/**
	 * @param command
	 */
	public ChangeStratsCommand(String command, GameWorld gameworld) {
		super(command);
		this.targetGameWorld = gameworld;
	}
	
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		GameWorld gameWorld = GameWorld.getInstance();
		gameWorld.changeNPCStrategy();
		super.actionPerformed(evt);
	}

}



