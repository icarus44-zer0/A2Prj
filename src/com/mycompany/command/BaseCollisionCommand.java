/**
 * 
 */
package com.mycompany.command;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * @author Icarus44
 *
 */
public class BaseCollisionCommand extends Command {
	private GameWorld targetGameWorld;
	
	/**
	 * @param command
	 */
	public BaseCollisionCommand(String command, GameWorld gameworld) {
		super(command);
		this.targetGameWorld = gameworld;
	}
	
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		int key = 1;
		String keyString = "";
		
		Command cOk = new Command("Ok");
		Command cCancel = new Command("Cancel");
		Command[] cmds = new Command[]{cOk, cCancel};
		TextField myTF = new TextField();
		Command c = Dialog.show("Enter Base Number", myTF, cmds);
		
		if (c == cOk) {
			keyString = myTF.getText();	
			switch(keyString){
			case "1":		// Keycode for 1
				key = 1;
				break;
			case "2":		// Keycode for 2
				key = 2;
				break;
			case "3":		// Keycode for 3
				key = 3;
				break;
			case "4":		// Keycode for 4
				key = 4;
				break;
			case "5":		// Keycode for 5
				key = 5;
				break;
			case "6":		// Keycode for 6
				key = 6;
				break;
			case "7":		// Keycode for 7
				key = 7;
				break;
			case "8":		// Keycode for 8
				key = 8;
				break;
			case "9":		// Keycode for 9
				key = 9;
			default: 
				key = 1;
				break;
				}
		}
	
		targetGameWorld.pCyborgBaseCollison(key);
		super.actionPerformed(evt);
	}
}