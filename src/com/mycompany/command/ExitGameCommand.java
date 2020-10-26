package com.mycompany.command;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

public class ExitGameCommand extends Command {

	public ExitGameCommand(String command) {
		super(command);
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Boolean close = Dialog.show("Confirm exit", "Are you sure you want to exit the game?","Ok", "Cancel");
		if (close) {
			Display.getInstance().exitApplication();
		}
		super.actionPerformed(evt);
	}
}
