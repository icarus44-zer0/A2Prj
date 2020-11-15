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
import com.mycompany.a3.GameObject;
import com.mycompany.a3.GameObjectCollection;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.IDrawable;
import com.mycompany.a3.IIterator;

/**
 * @author Icarus44
 *
 */
public class MapViewContainer extends Container implements Observer {
	private TextArea gameTextArea;
	private Graphics graphics;

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
		revalidate();
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
		// TODO Auto-generated method stub
		return this.getWidth();
	}

	public int getMapHeight() {
		return this.getHeight();
	}
	
	
	
}
