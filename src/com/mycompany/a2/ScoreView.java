/**
 * 
 */
package com.mycompany.a2;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;

/**
 * @author Icarus44
 *
 */
public class ScoreView extends Container implements Observer {
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
	
	private GameWorld gameWorld;
	private HashMap<String,GameObject> gameObjectMap;
	private Cyborg playerCyborg;
	
	/**
	 * @param flowLayout
	 */
	public ScoreView() {
		super(new FlowLayout(Component.CENTER));
		
		gameWorld = GameWorld.get_Instance();
		gameObjectMap = gameWorld.get_gameObjectMap();
		
		playerCyborg = (Cyborg) gameObjectMap.get("p1Cyborg");	
		
		timeLabel =  new Label("Time");
		livesLabel =  new Label("Lives Remaining:");
		baseLabel =  new Label("Player last Base Reached");
		energyLabel =  new Label("Player Energy Level");
		damageLabel =  new Label("Player Damage Level");
		soundLabel =  new Label("Sound");
		
		timeValueLabel =  new Label("" + GameWorld.get_Instance().get_timeElapsed());
		livesValueLabel =  new Label("" + GameWorld.get_Instance().get_lives());
		baseValueLabel =  new Label("" + playerCyborg.get_lastBaseReached());
		energyValueLabel =  new Label("" + playerCyborg.get_energyLevel());
		damageValueLabel =  new Label("" + playerCyborg.get_damageLevel());
		soundValueLabel =  new Label("" + "NULL");
		
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

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub

	}
	
	
}
