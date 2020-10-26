/**
 * 
 */
package com.mycompany.gui;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ClearableTextField;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Border;
import com.mycompany.a2.Base;
import com.mycompany.a2.Drone;
import com.mycompany.a2.EnergyStation;
import com.mycompany.a2.GameObject;
import com.mycompany.a2.GameObjectCollection;
import com.mycompany.a2.GameWorld;
import com.mycompany.a2.IIterator;
import com.mycompany.a2.NPCCyborg;
import com.mycompany.a2.PlayerCyborg;

/**
 * @author Icarus44
 *
 */
public class MapViewContainer extends Container implements Observer {
	private TextArea gameTextArea;

	/**
	 * @param flowLayout
	 */
	public MapViewContainer(Layout layout) {
		super(layout);	
		gameTextArea = new TextArea();
		
		setStyle();
		setTextArea();
		this.add(gameTextArea);
	}

	/**
	 * 
	 */
	private void setTextArea() {
		gameTextArea.setEditable(false);
		gameTextArea.getAllStyles().setBgTransparency(0);
	}

	/**
	 * 
	 */
	private void setStyle() {
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.MAGENTA));
	}

	/**
	 * 
	 * @param model
	 */
	public MapViewContainer(Observable model) {
		model.addObserver(this);
	}

	/**
	 * 
	 */
	@Override
	public void update(Observable observable, Object data) {
		this.repaint();	
		GameWorld gameWorld = GameWorld.getInstance();
		GameObjectCollection gameObjectCollection = gameWorld.getGameObjectCollection(); 
		IIterator iterator = gameObjectCollection.getIterator();
		String builderString = new String();
		while (iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			builderString += gameObject.getClass().getSimpleName() + ": " + gameObject.toString() + "\n";
			System.out.println(gameObject.getClass().getSimpleName() + ": " + gameObject.toString());
		}
		gameTextArea.setText(builderString);
		revalidate();
	}
}
