package com.mycompany.command;


import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;

import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;


public class SideMenuSoundItemCommand extends Command {
	private GameWorld targetGameWorld;
	
	/**
	 * 
	 * @param command
	 * @param targetGameWorld
	 */
	public SideMenuSoundItemCommand(String command, GameWorld targetGameWorld) {
		super(command);
		this.targetGameWorld = targetGameWorld;
	}

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		if (((CheckBox)evt.getComponent()).isSelected()) {
			targetGameWorld.setSoundFlag(true);
		}else {
			targetGameWorld.setSoundFlag(false);
		}
	}
}


