package com.dodge.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.dodge.game.domain.Laser;
import com.dodge.game.domain.Ship;
import com.dodge.game.main.DodgeGame;
import com.dodge.game.service.InputHandlerService;
import com.dodge.game.service.LaserService;
import com.dodge.game.service.ObjectManagerService;
import com.dodge.game.service.SoundManagerService;

public class PlayScreen implements Screen {
	private SoundManagerService soundManagerService;
	private Ship playerShip;
	private Laser playerLaser;
	private SpriteBatch spriteBatch;
	private InputHandlerService inputHandlerService;
	private LaserService laserService = new LaserService();
	public PlayScreen(DodgeGame game) {
		this.soundManagerService = new SoundManagerService();
		this.inputHandlerService = new InputHandlerService();
		this.playerShip = ObjectManagerService.createPlayerShip();
		this.playerLaser = ObjectManagerService.createPlayerLaser(playerShip);
		this.spriteBatch = new SpriteBatch();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Music backgroundMusic = soundManagerService.playMusic("2021-10-19_-_Funny_Bit_-_www.FesliyanStudios.com.mp3");
		soundManagerService.setVolume(backgroundMusic, .1f);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		inputHandlerService.handleInput(delta,playerShip,playerLaser);
		laserService.update(delta, playerShip.getRotation(),playerLaser);
		spriteBatch.begin();
		playerShip.draw(spriteBatch);
		playerLaser.draw(spriteBatch);
		spriteBatch.end();
		 
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
