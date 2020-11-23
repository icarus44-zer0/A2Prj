/**
 * 
 */
package com.mycompany.gui;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Border;
import com.codename1.util.regex.REDebugCompiler;
import com.mycompany.a3.GameObjectCollection;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.IDrawable;
import com.mycompany.a3.IIterator;
import com.mycompany.gameObjects.Base;
import com.mycompany.gameObjects.GameObject;

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
		//this.add(gameTextArea);
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
		debugger();
		revalidate();
	}
	
	/**
	 * debug method for printing output to console and setting all NPC to never win 
	 */
	public void debugger() {
		GameWorld gameWorld = GameWorld.getInstance();
		//gameWorld.debug();
	}
	
	@Override
	public void paint(Graphics graphics)
	{
		super.paint(graphics);
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());
		
		
		GameWorld gameWorld = GameWorld.getInstance();
		GameObjectCollection gameObjectCollection = gameWorld.getGameObjectCollection(); 
		IIterator iterator = gameObjectCollection.getIterator();
		
		while (iterator.hasNext())
		{
			GameObject gameObject = (GameObject) iterator.getNext();
			if (gameObject instanceof IDrawable)
			{
				((IDrawable) gameObject).draw(graphics, pCmpRelPrnt);
			}
		}
	}

	public int getMapWidth() {
		return this.getWidth();
	}

	public int getMapHeight() {
		return this.getHeight();
	}
	
	
	
}
