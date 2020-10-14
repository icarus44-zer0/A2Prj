/**
 * 
 */
package com.mycompany.a2;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;

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
	public ScoreView(Layout layout) {
		super(layout);
		init();

	}
	

	/**
	 * @param model
	 */
	public ScoreView(Observable model) {
		model.addObserver(this);
		init();

	}

	@Override
	public void update(Observable observable, Object data) {
		timeValueLabel.setText("" + GameWorld.get_Instance().get_timeElapsed());
		livesValueLabel.setText("" + GameWorld.get_Instance().get_lives());
		baseValueLabel.setText("" + playerCyborg.get_lastBaseReached());
		energyValueLabel.setText("" + playerCyborg.get_energyLevel());
		damageValueLabel.setText("" + playerCyborg.get_damageLevel());
		soundValueLabel.setText("" + "NULL");
		revalidate();
	}
	
	private void init() {
		gameWorld = GameWorld.get_Instance();
		gameObjectMap = gameWorld.get_gameObjectMap();
		
		playerCyborg = (Cyborg) gameObjectMap.get("p1Cyborg");	
		
		timeLabel =  new Label("Time:");
		livesLabel =  new Label("Lives Remaining:");
		baseLabel =  new Label("Player last Base Reached:");
		energyLabel =  new Label("Player Energy Level:");
		damageLabel =  new Label("Player Damage Level:");
		soundLabel =  new Label("Sound:");
		
		timeValueLabel =  new Label("" + GameWorld.get_Instance().get_timeElapsed());
		livesValueLabel =  new Label("" + GameWorld.get_Instance().get_lives());
		baseValueLabel =  new Label("" + playerCyborg.get_lastBaseReached());
		energyValueLabel =  new Label("" + playerCyborg.get_energyLevel());
		damageValueLabel =  new Label("" + playerCyborg.get_damageLevel());
		soundValueLabel =  new Label("");
		
		 timeLabel.getAllStyles().setPadding(RIGHT,2);
		 livesLabel.getAllStyles().setPadding(RIGHT,2);
		 baseLabel.getAllStyles().setPadding(RIGHT,2);
		 energyLabel.getAllStyles().setPadding(RIGHT,2);
		 damageLabel.getAllStyles().setPadding(RIGHT,2);
		 soundLabel.getAllStyles().setPadding(RIGHT,2);

		 timeValueLabel.getAllStyles().setPadding(RIGHT,2);
		 livesValueLabel.getAllStyles().setPadding(RIGHT,2);
		 baseValueLabel.getAllStyles().setPadding(RIGHT,2);
		 energyValueLabel.getAllStyles().setPadding(RIGHT,2);
		 damageValueLabel.getAllStyles().setPadding(RIGHT,2);
		 soundValueLabel.getAllStyles().setPadding(RIGHT,2);
		 
		 timeLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 livesLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 baseLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 energyLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 damageLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 soundLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 
		 timeValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 livesValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 baseValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 energyValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 damageValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		 soundValueLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
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
	
	
	
}
