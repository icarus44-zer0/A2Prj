package com.mycompany.command;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;


public class HelpCommand extends Command {

	public HelpCommand(String command) {
		super(command);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Command cOk = new Command("Close");
		Command[] cmds = new Command[]{cOk};
		TextArea myTA = new TextArea();
		setTextArea(myTA);
		
		Dialog.show("Game Information", myTA, cmds);
		super.actionPerformed(evt);
		
	}

	/**
	 * 
	 * @param myTA
	 */
	private void setTextArea(TextArea myTA) {
		String helpString = new String();
		helpString = "Command Key Bindings \n"
				+ "Accelerate \t a \n"
				+ "Brake \t b \n"
				+ "Left turn \t l \n"
				+ "Right turn \t r \n"
				+ "Collide with NPC \t g \n"
				+ "Collide with base \t 1-9 \n"
				+ "Collide with energy station \t e \n"
				+ "Collide with drone \t d \n"
				+ "Tick \t t \n" 
				+ "Exit \t x \n";
		myTA.setText(helpString);
		
	}
	
}
