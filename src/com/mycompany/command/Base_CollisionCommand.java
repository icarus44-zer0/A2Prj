/**
 * 
 */
package com.mycompany.command;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionEvent.Type;
import com.codename1.ui.util.EventDispatcher;
import com.mycompany.a2.GameWorld;

/**
 * @author Icarus44
 *
 */
public class Base_CollisionCommand extends Command {
	private GameWorld targetGameWorld;
	
	/**
	 * @param command
	 */
	public Base_CollisionCommand(String command, GameWorld gameworld) {
		super(command);
		this.targetGameWorld = gameworld;
	}
	

	@Override
	public void actionPerformed(ActionEvent evt) {
		int key = 1;
		
		if (evt.getEventType() == Type.KeyRelease) {
			key = evt.getKeyEvent();
			
			switch(key){
			case 49:		// Keycode for 1
				key = 1;
				break;
			case 50:		// Keycode for 2
				key = 2;
				break;
			case 51:		// Keycode for 3
				key = 3;
				break;
			case 52:		// Keycode for 4
				key = 4;
				break;
			case 53:		// Keycode for 5
				key = 5;
				break;
			case 54:		// Keycode for 6
				key = 6;
				break;
			case 55:		// Keycode for 7
				key = 7;
				break;
			case 56:		// Keycode for 8
				key = 8;
				break;
			case 57:		// Keycode for 9
				key = 9;
			default: 
				key = 1;
				break;
				}
		}
		
		targetGameWorld.pCyborg_BaseCollison(key);
		super.actionPerformed(evt);
	}
}