/**
 * 
 */
package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Image;
import com.codename1.ui.plaf.Border;

/**
 * @author Icarus44
 *
 */
public class GameButton extends Button {

	
	public GameButton(Command command)
	{
		
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.BLACK));
		this.getAllStyles().setPadding(TOP, 5);
		this.getAllStyles().setPadding(BOTTOM, 5);
		
		this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		
		this.getPressedStyle().setBgTransparency(125);
		this.getPressedStyle().setBgColor(ColorUtil.rgb(0, 100, 100));
		this.getPressedStyle().setFgColor(ColorUtil.BLUE);
		
		this.getDisabledStyle().setBgTransparency(255);
		this.getDisabledStyle().setBgColor(ColorUtil.GRAY);
		this.getDisabledStyle().setFgColor(ColorUtil.WHITE);
		this.getDisabledStyle().setStrikeThru(true);
		
		this.setFocusable(false);
		
		this.setCommand(command);
		this.addActionListener(command);
		
	}
}

