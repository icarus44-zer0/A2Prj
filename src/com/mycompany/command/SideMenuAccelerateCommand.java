/**
 * 
 */
package com.mycompany.command;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

/**
 * @author jp
 *
 */
public class SideMenuAccelerateCommand extends Command {
	private GameWorld targetGameWorld;
	
	/**
	 * 
	 * @param command
	 * @param targetGameWorld
	 */
	public SideMenuAccelerateCommand(String command, GameWorld targetGameWorld) {
		super(command);
		this.targetGameWorld = targetGameWorld;

	}

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		if (((CheckBox)evt.getComponent()).isSelected()) {
			targetGameWorld.pCyborgaccelerate();;
		}
	}

}
