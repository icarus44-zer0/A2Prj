package com.mycompany.sfx;

import java.io.IOException;
import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class ThemeSound implements Runnable{

	private Media m;

	public ThemeSound(String fileName) {
		if (Display.getInstance().getCurrent() == null) {
			System.exit(0);
		}
		while (m == null) {
			try {
				InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
				m = MediaManager.createMedia(is, "audio/wav");
			} catch (IOException e) {
				System.out.println("MARCELOUS WALLACE");
			}
		}
	}
	
	public void pause() {
		m.pause();
	}


	public void play() {
		m.setTime(0);
		m.play();
	}

	@Override
	public void run() {
		m.setTime(0);
		m.play();
	}
}
