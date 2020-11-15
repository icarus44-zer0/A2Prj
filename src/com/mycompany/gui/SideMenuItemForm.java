package com.mycompany.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.mycompany.a3.GameWorld;
import com.mycompany.command.SideMenuAboutCommand;
import com.mycompany.command.AccelerateCommand;
import com.mycompany.command.SideMenuSoundItemCommand;

public class SideMenuItemForm extends Form {
	
	/**
	 * constructor for the sidemenu
	 * @param gameToolbar
	 */
	public SideMenuItemForm(Toolbar gameToolbar) {
		makeCheckbox(gameToolbar);
		makeAccelerate(gameToolbar);
		makeAbout(gameToolbar);
	}

	/**
	 * creates the about command 
	 * @param gameToolbar
	 */
	private void makeAbout(Toolbar gameToolbar) {
		
		SideMenuAboutCommand aboutCommand = new SideMenuAboutCommand("About");

		CheckBox aboutButton = new CheckBox();
		
		aboutButton.setCommand(aboutCommand);
		aboutButton.getAllStyles().setBgTransparency(255);
		aboutButton.getAllStyles().setBgColor(ColorUtil.LTGRAY);	
		gameToolbar.addComponentToSideMenu(aboutButton);
	}

	/**
	 * creates the accelerate command 
	 * @param gameToolbar
	 */
	private void makeAccelerate(Toolbar gameToolbar) {
		
		AccelerateCommand sideMenuAccelerateCommand = new AccelerateCommand("Accelerate", GameWorld.getInstance());
		CheckBox accelerateValueButton = new CheckBox();
		accelerateValueButton.setCommand(sideMenuAccelerateCommand);
		accelerateValueButton.getAllStyles().setBgTransparency(255);
		accelerateValueButton.getAllStyles().setBgColor(ColorUtil.LTGRAY);	
		gameToolbar.addComponentToSideMenu(accelerateValueButton);		
	}

	/**
	 * creates the toolbar command 
	 * @param gameToolbar
	 */
	private void makeCheckbox(Toolbar gameToolbar) {
		SideMenuSoundItemCommand sideMenuSoundItemCommand = new SideMenuSoundItemCommand("Sound", GameWorld.getInstance());
		CheckBox soundValueCheckBox = new CheckBox();
		
		soundValueCheckBox.setCommand(sideMenuSoundItemCommand);
		soundValueCheckBox.getAllStyles().setBgTransparency(255);
		soundValueCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);	
		gameToolbar.addComponentToSideMenu(soundValueCheckBox);
	}
	
	
	
	
}

