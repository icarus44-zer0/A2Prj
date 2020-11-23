package com.mycompany.sfx;

import java.io.IOException;
import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class CrashSound  implements Runnable {

	private Media m;

	public CrashSound(String fileName) {
		if (Display.getInstance().getCurrent() == null) {
			System.exit(0);
		}
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
			m = MediaManager.createMedia(is, "audio/wav");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pause() {
		m.pause();
	}


	public void play() {
		// start playing the sound from time zero (beginning of the sound file)
		m.setTime(0);
		m.play();
	}

	@Override
	public void run() {
		m.setTime(0);
		m.play();
		
	}
}