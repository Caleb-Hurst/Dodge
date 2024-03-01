package com.dodge.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class SoundManagerService {
	private Music backgroundMusic;
	
	public Texture loadImage(String fileName) {
		Texture texture = new Texture(Gdx.files.internal(fileName));
		return texture;
	}

	public Sound loadSound(String fileName) {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(fileName));
		return sound;
	}
	public Music loadMusic(String fileName) {
		Music music = Gdx.audio.newMusic(Gdx.files.internal(fileName));
		return music;
	}
	public Music playMusic(String fileName) {	
		backgroundMusic = loadMusic(fileName);		
//		// start playback
		backgroundMusic.setLooping(true);
//		backgroundMusic.play();
		return backgroundMusic;
	}
	
	public void setVolume(Music music, float x) {
		music.setVolume(x);
		
	}
	
	public void stopMusic() {
		if (backgroundMusic != null) {
            backgroundMusic.stop();
            backgroundMusic.dispose();
            backgroundMusic = null; // Set to null to indicate that the music has been stopped and disposed
        }
	}

	public void laser() {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal("shoot-1-81135.mp3"));
		sound.play(0.2f);
		
	}

}
