/**
 * 
 */
package com.mycompany.gui;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.Layout;
import com.mycompany.a3.GameWorld;
import com.mycompany.gameObjects.PlayerCyborg;

/**
 *
 */
public class ScoreViewContainer extends Container implements Observer {
	private Label timeLabel;
	private Label livesLabel;
	private Label baseLabel;
	private Label energyLabel;
	private Label damageLabel;
	private Label soundLabel;
	private Label timeValueLabel;
	private Label livesValueLabel;
	private Label baseValueLabel;
	private Label energyValueLabel;
	private Label damageValueLabel;
	private Label soundValueLabel;
	private PlayerCyborg playerCyborg;
	
	
	/**
	 * @param flowLayout
	 */
	public ScoreViewContainer(Layout layout) {
		super(layout);
	    init();
	}
	

	/**
	 * @param model
	 */
	public ScoreViewContainer(Observable model) {
		model.addObserver(this);
	}

	@Override
	public void update(Observable observable, Object data) {
		GameWorld gameWorld = GameWorld.getInstance();
		playerCyborg = PlayerCyborg.getInstance();
		//timeValueLabel.setText("" + gameWorld.getTotalTimeElapsed());
		timeValueLabel.setText("" + (int) gameWorld.gettimeElapsed());
		//timeValueLabel.setText("" + 0);
		
		livesValueLabel.setText("" + gameWorld.getlives());
		baseValueLabel.setText("" + playerCyborg.getlastBaseReached());
		energyValueLabel.setText("" + playerCyborg.getenergyLevel());
		damageValueLabel.setText("" + playerCyborg.getdamageLevel());
		soundValueLabel.setText("" + gameWorld.getSoundFlagStatus());
		revalidate();
	}
	
	/**
	 * initialize the score view components 
	 */
	private void init() {
		setLabels();
		setValueLabels();
		setPadding();
		setForeGroundColor();
		addLables();
	}


	/**
	 * add labels to the form
	 */
	private void addLables() {
		this.add(timeLabel);
		this.add(timeValueLabel);
		this.add(livesLabel);
		this.add(livesValueLabel);
		this.add(baseLabel);
		this.add(baseValueLabel);
		this.add(energyLabel);
		this.add(energyValueLabel);
		this.add(damageLabel);
		this.add(damageValueLabel);
		this.add(soundLabel);
		this.add(soundValueLabel);
	}
		
	/**
	 * sets the style for all labels 
	 */
	private void setForeGroundColor() {
		 timeLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 timeValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 
		 livesLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 livesValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 
		 baseLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 baseValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 
		 energyLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 energyValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 
		 damageLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 damageValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 
		 soundLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 soundValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
	}


	/**
	 * Configures the style for all labels 
	 */
	private void setPadding() {
		 timeLabel.getAllStyles().setPadding(RIGHT,2);
		 timeValueLabel.getAllStyles().setPadding(RIGHT,2);
		 
		 livesLabel.getAllStyles().setPadding(RIGHT,2);
		 livesValueLabel.getAllStyles().setPadding(RIGHT,2);
		 
		 baseLabel.getAllStyles().setPadding(RIGHT,2);
		 baseValueLabel.getAllStyles().setPadding(RIGHT,2);
		 
		 energyLabel.getAllStyles().setPadding(RIGHT,2);
		 energyValueLabel.getAllStyles().setPadding(RIGHT,2);
		 
		 damageLabel.getAllStyles().setPadding(RIGHT,2);
		 damageValueLabel.getAllStyles().setPadding(RIGHT,2);
		 
		 soundLabel.getAllStyles().setPadding(RIGHT,2);
		 soundValueLabel.getAllStyles().setPadding(RIGHT,2);
	}


	/**
	 * sets values for all labels 
	 */
	private void setLabels() {
		timeLabel =  new Label("Time:");
		livesLabel =  new Label("Lives Remaining:");
		baseLabel =  new Label("Player last Base Reached:");
		energyLabel =  new Label("Player Energy Level:");
		damageLabel =  new Label("Player Damage Level:");
		soundLabel =  new Label("Sound:");
	}
	
	/*
	 * creates the labels that will be updated by the observer  
	 */
	private void setValueLabels() {
		timeValueLabel =  new Label();
		livesValueLabel =  new Label();
		baseValueLabel =  new Label();
		energyValueLabel =  new Label();
		damageValueLabel =  new Label();
		soundValueLabel =  new Label();
	}

}
