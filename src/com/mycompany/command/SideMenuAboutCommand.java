package com.mycompany.command;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class SideMenuAboutCommand extends Command {

	public SideMenuAboutCommand(String command) {
		super(command);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		Command cOk = new Command("Close");
		Command[] cmds = new Command[]{cOk};
		String aboutString = "Josh Poe \n"
				+ "CSC 133 \n"
				+ "other information I want to display.";
		
		if (((CheckBox)evt.getComponent()).isSelected()) {
			Dialog.show("Game Information", aboutString, cmds);
		}
	}

}
