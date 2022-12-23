package maplestory;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
	private Clip clip; // variable for playing, stopping music
	private URL music;
	private String name; // music name
	private FloatControl VolumeControl; // variable for controling volume of music
	private int loop; // loop number, -1: Infinity

	public Music(String _name, int _loop) {
		name = _name;
		loop = _loop;
	}

	// get music, make clip, and play music
	public void play() {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				music = getClass().getClassLoader().getResource(name);
				try {
					clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(music));
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (loop == -1) {
					clip.loop(Clip.LOOP_CONTINUOUSLY);
					VolumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					VolumeControl.setValue(-15.0f);
				}
				clip.start();
				
				if(loop == 1) {
					try {
						Thread.sleep(clip.getMicrosecondLength()/1000+1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					clip.stop();
					clip.close();
				}
			}
			
		};
		Maplestory.thread_pool.submit(runnable);
	}

	// stop music
	public void stop() {
		clip.stop();
		clip.close();
	}
}
