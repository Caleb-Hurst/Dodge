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
	private BackgroundMusic backgroundMusic = new BackgroundMusic();
	private Music currentBackgroundMusic;
	private boolean isMultipleOfTenSoundPlayed = false;
	private boolean isBackgroundMusicPlaying = false;
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

	public Music setBackgroundMusic(BackgroundMusic backgroundMusic, GameIncrement gameIncrement) {
        if (gameIncrement.isAsteroidEventHappening()) {
            backgroundMusic.setFileName("playScreenBackGroundMusic.mp3");
        } else {
            backgroundMusic.setFileName("asteroidEventLoop2.mp3");
        }

        String currentMusic = backgroundMusic.getFileName();
        if (currentBackgroundMusic != null) {
            currentBackgroundMusic.stop();
            currentBackgroundMusic.dispose();
            isBackgroundMusicPlaying = false;
        }

        currentBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(currentMusic));
        return currentBackgroundMusic;
    }

    public void playBackgroundMusic(GameIncrement gameIncrement) {
        if (!isBackgroundMusicPlaying) {
            Music music = setBackgroundMusic(backgroundMusic, gameIncrement);
            music.setLooping(true);
            music.setVolume(0.1f);
            music.play();
            isBackgroundMusicPlaying = true;
        }
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

	public void playAsteroidEventMusic(GameIncrement gameIncrement) {
		Music backgroundMusic = loadMusic("asteroidEventLoop2.mp3");

		if (gameIncrement.isAsteroidEventHappening() && !AEHasBeenPlayed) {
			backgroundMusic.setLooping(true);
			backgroundMusic.setVolume(0.1f);
			backgroundMusic.play();
			AEHasBeenPlayed = true;
		} else if (!gameIncrement.isAsteroidEventHappening() && AEHasBeenPlayed) {
			if (backgroundMusic != null && backgroundMusic.isPlaying()) {
				backgroundMusic.stop(); // Stop the music instead of pausing
				System.out.println("STOPPED");
				AEHasBeenPlayed = false; // Reset the flag
			}
		}
	}
}
