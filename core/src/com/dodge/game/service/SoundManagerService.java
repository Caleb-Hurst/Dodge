package com.dodge.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.dodge.game.domain.BackgroundMusic;
import com.dodge.game.domain.GameIncrement;
import com.dodge.game.domain.Ship;

public class SoundManagerService {
	private Music backgroundMusic;
	private boolean isMultipleOfTenSoundPlayed = false;
	private boolean PSEHasBeenPlayed = false;
	private boolean AEHasBeenPlayed = false;
	private int lastMultipleOfTenScore = 0;
	private ObjectManagerService objectManagerService = new ObjectManagerService();

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

	public void playBackgroundMusic(GameIncrement gameIncrement,Ship playerShip) {
		if (!gameIncrement.isAsteroidEventHappening() && !PSEHasBeenPlayed) {
			if (backgroundMusic != null) {
				backgroundMusic.stop();
			}
			backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("playScreenBackGroundMusic.mp3"));
			backgroundMusic.setLooping(true);
			backgroundMusic.setVolume(0.1f);
			backgroundMusic.play();
			PSEHasBeenPlayed = true;
			AEHasBeenPlayed = false;
		}
		if (gameIncrement.isAsteroidEventHappening() && !AEHasBeenPlayed) {
			if (backgroundMusic != null) {
				backgroundMusic.stop();
			}
			backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("asteroidEventLoop2.mp3"));
			backgroundMusic.setLooping(true);
			backgroundMusic.setVolume(0.1f);
			backgroundMusic.play();
			AEHasBeenPlayed = true;
			PSEHasBeenPlayed = false;
		}else if (!playerShip.isAlive()) {
			backgroundMusic.stop();
		}
	}

	public void shouldChangeBackgroundMusic(GameIncrement gameIncrement) {
		if (gameIncrement.isAsteroidEventHappening()) {
			PSEHasBeenPlayed = true;
		}
	}

	public String setBackgroundMusic(GameIncrement gameIncrement) {
		if (gameIncrement.isAsteroidEventHappening()) {
			String backgroundMusic = "asteroidEventLoop2.mp3";
			return backgroundMusic;
		}
		String backgroundMusic = "playScreenBackGroundMusic.mp3";

		return backgroundMusic;
	}

	public void setVolume(Music music, float x) {
		music.setVolume(x);

	}

	public void laser() {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal("shoot-1-81135.mp3"));
		sound.play(0.2f);

	}

	public void enemyLaser() {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal("enemyLaser3.mp3"));
		sound.play(0.03f);

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

		// Check if the score is a multiple of 10 and if the sound hasn't been played
		// yet for this increment
		if (score % 10 == 0 && score != lastMultipleOfTenScore && !isMultipleOfTenSoundPlayed) {
			Sound levelUpSound = Gdx.audio.newSound(Gdx.files.internal("multipleOfTen.mp3"));
			levelUpSound.play(.07f);
//            Timer.schedule(new Timer.Task() {
//                @Override
//                public void run() {
//                	 Sound nextSound = Gdx.audio.newSound(Gdx.files.internal("countdown.mp3"));
//                     nextSound.play(.07f);
//                }
//            }, 1f);

			// Set the lastMultipleOfTenScore to the current score to avoid playing the
			// sound again for the same increment
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

	public void asteroidEventCollision() {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal("asteroidExplosion.mp3"));
		sound.play(0.09f);
	}
	public void stopMusic() {
		backgroundMusic.stop();
	}
	
	public void playDeath() {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal("death.mp3"));
		sound.play();
	}

}
