/**
 * 
 */
package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

/**
 * @author Icarus44
 *
 */
public class MapView extends Container implements Observer {

	/**
	 * @param flowLayout
	 */
	public MapView() {
		super(new FlowLayout(Component.CENTER));
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.MAGENTA));
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub

	}

}
