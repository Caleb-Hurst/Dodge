package com.dodge.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.dodge.game.domain.Ship;

public class SoundManagerService {
	private Music backgroundMusic;
	private boolean isMultipleOfTenSoundPlayed = false;
	private int score;
	private int lastMultipleOfTenScore = 0;

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
		backgroundMusic.setVolume(4f);
		backgroundMusic.play();
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

	public void explosion() {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
		sound.play(0.1f);
	}

	public void asteroid() {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal("asteroid.mp3"));
		sound.play(0.09f);

	}

	public void isMultipleOfTen(Ship playerShip) {
        int score = playerShip.getScore();

        // Check if the score is a multiple of 10 and if the sound hasn't been played yet for this increment
        if (score % 10 == 0 && score != lastMultipleOfTenScore && !isMultipleOfTenSoundPlayed) {
            Sound sound = Gdx.audio.newSound(Gdx.files.internal("multipleOfTen.mp3"));
            sound.play(.9f);

            // Set the lastMultipleOfTenScore to the current score to avoid playing the sound again for the same increment
            lastMultipleOfTenScore = score;

            // Set the flag to true to indicate that the sound has been played
            isMultipleOfTenSoundPlayed = true;

            // Schedule a task to reset the flag after a delay (3 seconds in this case)
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    isMultipleOfTenSoundPlayed = false;
                }
            }, .7f);
        }
    }

}
