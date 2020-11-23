/**
 * 
 */
package com.mycompany.command;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.util.UITimer;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameButton;
import com.mycompany.a3.GameWorld;

/**
 * @author Icarus44
 *
 */
public class PauseCommand extends Command {
	private GameWorld targetGameWorld;
	private UITimer timer;
	private GameButton pauseButton;
	private GameButton selectButton;
	private Game currentGame;
	private boolean isPause;
	/**
	 * @param command
	 */
	public PauseCommand(String command, GameWorld gameworld, UITimer timer,GameButton pauseButton, GameButton selectButton, Game currentGame) {
		super(command);
		isPause = false;
		this.targetGameWorld = gameworld;
		this.timer = timer;
		this.pauseButton = pauseButton;
		this.selectButton = selectButton;
		this.currentGame = currentGame;
	}
	
	/**
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		if (isPause) {
			isPause = false;
			currentGame.unpause();
		}else {
			isPause = true;
			currentGame.pause();
		}
		
		super.actionPerformed(evt);
	}
	
}

