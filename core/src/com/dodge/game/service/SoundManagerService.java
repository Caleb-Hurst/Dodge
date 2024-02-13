package com.dodge.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class SoundManagerService {
	
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
		Music backgroundMusic = loadMusic(fileName);		
//		// start playback
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
		return backgroundMusic;
	}

}
