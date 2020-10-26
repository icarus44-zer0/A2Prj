package com.mycompany.gui;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;

public class ConfirmCloseForm extends Form {

	public ConfirmCloseForm() {
		this.setTitle("Exit Game");
		Boolean bOk = Dialog.show("Confirm quit", "Are you sure you want to quit?", "Ok", "Cancel");
		// [in a dialog if you only want to display the okay option,
		// use Dialog.show("Title of dialog", "Text to display on dialog", "Ok", null);]
		if (bOk) {
			// instead of System.exit(0), CN1 recommends using:
			Display.getInstance().exitApplication();
		}
		this.show();
	}
}
