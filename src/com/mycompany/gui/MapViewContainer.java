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
import com.mycompany.a3.GameObjectCollection;
import com.mycompany.a3.GameWorld;
import com.mycompany.a3.IDrawable;
import com.mycompany.a3.IIterator;
import com.mycompany.a3.ISelectable;
import com.mycompany.gameObjects.GameObject;
import com.mycompany.gameObjects.Selectable;

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
		// gameWorld.debug();
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		Point pCmpRelPrnt = new Point(this.getX(), this.getY());

		GameWorld gameWorld = GameWorld.getInstance();
		GameObjectCollection gameObjectCollection = gameWorld.getGameObjectCollection();
		IIterator iterator = gameObjectCollection.getIterator();

		while (iterator.hasNext()) {
			GameObject gameObject = (GameObject) iterator.getNext();

			if (gameObject instanceof IDrawable) {
				((IDrawable) gameObject).draw(graphics, pCmpRelPrnt);
			}

			if (gameObject instanceof ISelectable && !(gameWorld.isPaused())) {
				Selectable selectableGameObject = (Selectable) gameObject;
				if(selectableGameObject.isSelected()) {
					selectableGameObject.setSelected(false);
				}
			}
		}
	}

	public void pointerPressed(int x, int y) {
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x, y);
		Point pCmpRelPrnt = new Point(getX(), getY());

		GameWorld gameWorld = GameWorld.getInstance();
		GameObjectCollection gameObjectCollection = gameWorld.getGameObjectCollection();
		IIterator iterator = gameObjectCollection.getIterator();

		while (iterator.hasNext() && gameWorld.isPaused()) {
			GameObject gameObject = (GameObject) iterator.getNext();
			if (gameObject instanceof ISelectable) {
				Selectable selectableGameObject = (Selectable) gameObject;

				if (selectableGameObject.contains(pPtrRelPrnt, pCmpRelPrnt)) {
					selectableGameObject.setSelected(true);
				} else {
					selectableGameObject.setSelected(false);
				}
			}
		}
		repaint();
	}

	public int getMapWidth() {
		return this.getWidth();
	}

	public int getMapHeight() {
		return this.getHeight();
	}

}
