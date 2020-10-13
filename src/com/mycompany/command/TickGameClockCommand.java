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
public class TickGameClockCommand extends Command {

	
	/**
	 * @param command
	 */
	public TickGameClockCommand(String command) {
		super(command);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		GameWorld.get_Instance().tickGameClock();
		super.actionPerformed(evt);
	}
}
