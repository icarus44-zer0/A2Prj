package com.mycompany.command;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class PositionCommand extends Command {
	private GameWorld targetGameWorld;
	
	/**
	 * @param command
	 */
	public PositionCommand(String command, GameWorld gameworld) {
		super(command);
		this.targetGameWorld = gameworld;
	}
	
	
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		super.actionPerformed(evt);
		System.out.println("test");
	}

}

