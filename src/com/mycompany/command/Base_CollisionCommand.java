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
public class Base_CollisionCommand extends Command {

	/**
	 * @param command
	 */
	public Base_CollisionCommand(String command) {
		super(command);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		GameWorld.get_Instance().pCyborg_BaseCollison(1);
		super.actionPerformed(evt);
	}

}
