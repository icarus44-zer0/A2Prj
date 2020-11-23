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
	private  CheckBox aboutCheckbox;
	private CheckBox accelerateValueCheckbox;
	CheckBox soundValueCheckBox;
	
	/**
	 * constructor for the sidemenu
	 * @param gameToolbar
	 */
	public SideMenuItemForm(Toolbar gameToolbar) {
		makeSoundCheckbox(gameToolbar);
		makeAccelerateCheckbox(gameToolbar);
		makeAboutCheckbox(gameToolbar);
	}
	
	public SideMenuItemForm getForm() {
		return this;
	}

	/**
	 * creates the about command 
	 * @param gameToolbar
	 */
	private void makeAboutCheckbox(Toolbar gameToolbar) {
		
		SideMenuAboutCommand aboutCommand = new SideMenuAboutCommand("About");

		aboutCheckbox = new CheckBox();
		
		aboutCheckbox.setCommand(aboutCommand);
		aboutCheckbox.getAllStyles().setBgTransparency(255);
		aboutCheckbox.getAllStyles().setBgColor(ColorUtil.LTGRAY);	
		gameToolbar.addComponentToSideMenu(aboutCheckbox);
	}

	/**
	 * creates the accelerate command 
	 * @param gameToolbar
	 */
	private void makeAccelerateCheckbox(Toolbar gameToolbar) {
		
		AccelerateCommand sideMenuAccelerateCommand = new AccelerateCommand("Accelerate", GameWorld.getInstance());
		accelerateValueCheckbox = new CheckBox();
		accelerateValueCheckbox.setCommand(sideMenuAccelerateCommand);
		accelerateValueCheckbox.getAllStyles().setBgTransparency(255);
		accelerateValueCheckbox.getAllStyles().setBgColor(ColorUtil.LTGRAY);	
		gameToolbar.addComponentToSideMenu(accelerateValueCheckbox);		
	}

	/**
	 * creates the toolbar command 
	 * @param gameToolbar
	 */
	private void makeSoundCheckbox(Toolbar gameToolbar) {
		SideMenuSoundItemCommand sideMenuSoundItemCommand = new SideMenuSoundItemCommand("Sound", GameWorld.getInstance());
		soundValueCheckBox = new CheckBox();
		
		soundValueCheckBox.setCommand(sideMenuSoundItemCommand);
		soundValueCheckBox.getAllStyles().setBgTransparency(255);
		soundValueCheckBox.getAllStyles().setBgColor(ColorUtil.LTGRAY);	
		gameToolbar.addComponentToSideMenu(soundValueCheckBox);
	}

	/**
	 * @return the aboutCheckbox
	 */
	public CheckBox getAboutCheckbox() {
		return aboutCheckbox;
	}

	/**
	 * @param aboutCheckbox the aboutCheckbox to set
	 */
	public void setAboutCheckbox(CheckBox aboutCheckbox) {
		this.aboutCheckbox = aboutCheckbox;
	}

	/**
	 * @return the accelerateValueCheckbox
	 */
	public CheckBox getAccelerateValueCheckbox() {
		return accelerateValueCheckbox;
	}

	/**
	 * @param accelerateValueCheckbox the accelerateValueCheckbox to set
	 */
	public void setAccelerateValueCheckbox(CheckBox accelerateValueCheckbox) {
		this.accelerateValueCheckbox = accelerateValueCheckbox;
	}

	/**
	 * @return the soundValueCheckBox
	 */
	public CheckBox getSoundValueCheckBox() {
		return soundValueCheckBox;
	}

	/**
	 * @param soundValueCheckBox the soundValueCheckBox to set
	 */
	public void setSoundValueCheckBox(CheckBox soundValueCheckBox) {
		this.soundValueCheckBox = soundValueCheckBox;
	}
	
	

	
	
}

