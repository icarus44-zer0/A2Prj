package com.mycompany.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.mycompany.a2.GameWorld;
import com.mycompany.command.SideMenuAccelerateCommand;
import com.mycompany.command.SideMenuSoundItemCommand;

public class SideMenuItemCheckBoxForm extends Form {

	public SideMenuItemCheckBoxForm(Toolbar gameToolbar) {
		
		SideMenuSoundItemCommand sideMenuSoundItemCommand = new SideMenuSoundItemCommand("Sound", GameWorld.getInstance());
		CheckBox soundValueCheckBox = new CheckBox();
		soundValueCheckBox.setCommand(sideMenuSoundItemCommand);
		soundValueCheckBox.getAllStyles().setBgTransparency(255);
		soundValueCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);	
		
		//gameToolbar.addCommandToSideMenu(sideMenuSoundItemCommand);
		gameToolbar.addComponentToSideMenu(soundValueCheckBox);
		
		
		SideMenuAccelerateCommand sideMenuAccelerateCommand = new SideMenuAccelerateCommand("Accelerate", GameWorld.getInstance());
		CheckBox accelerateValueCheckBox = new CheckBox();
		accelerateValueCheckBox.setCommand(sideMenuAccelerateCommand);
		accelerateValueCheckBox.getAllStyles().setBgTransparency(255);
		accelerateValueCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);	
		
		//gameToolbar.addCommandToSideMenu(sideMenuAccelerateCommand);
		gameToolbar.addComponentToSideMenu(accelerateValueCheckBox);
		
	}
}

