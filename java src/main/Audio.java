package com.main;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
	
	public void playMusic(String musicLocation) {
		
		try {
			
			File musicPath = new File (musicLocation);
			if(musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				
				
				
			}
			else {
				System.out.println("Can't find file");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
public void playMusicContinuously(String musicLocation) {
		
		try {
			
			File musicPath = new File (musicLocation);
			if(musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				clip.start();
				
				
			}
			else {
				System.out.println("Can't find file");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
