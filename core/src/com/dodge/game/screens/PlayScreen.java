package com.dodge.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.dodge.game.domain.Ship;
import com.dodge.game.main.DodgeGame;
import com.dodge.game.service.ObjectManagerService;
import com.dodge.game.service.SoundManagerService;

public class PlayScreen implements Screen {
	private DodgeGame game;
	private Texture shipImage;
	private ObjectManagerService objectManagerService;
	private SoundManagerService soundManagerService;
	private Ship playerShip;
	private SpriteBatch spriteBatch;
	public PlayScreen(DodgeGame game) {
		this.game = game;
		this.soundManagerService = new SoundManagerService();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Music backgroundMusic = soundManagerService.playMusic("2021-10-19_-_Funny_Bit_-_www.FesliyanStudios.com.mp3");
		soundManagerService.setVolume(backgroundMusic,.1f);
		playerShip = ObjectManagerService.createPlayerShip();
		spriteBatch = new SpriteBatch();
//		laser = dodgeService.generateGameObject("laser", 35, 10, ship.x + ship.width / 2 - 5, ship.y + ship.height);	
//		laserImage = dodgeService.loadImage("laser-2.png");
		
//		dodgeSoundShoot = dodgeService.loadSound("shoot-1-81135.mp3");
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
//		ScreenUtils.clear(0, 0, 0, 0);
//		camera.update();
//		batch.setProjectionMatrix(camera.combined);
//		batch.begin();
//		batch.draw(shipImage, ship.x, ship.y, ship.width, ship.height);
//		if (isShooting) {
//	        batch.draw(laserImage, laser.x, laser.y, laser.width, laser.height);
//	        laser.y += 2000 * Gdx.graphics.getDeltaTime();  // Adjust the speed of the laser
//	       
//	        
//		}
//		batch.end();
		ScreenUtils.clear(0, 0, 0, 1);

		// Update game state here

		// Draw a filled rectangle on the screen
		spriteBatch.begin();
		playerShip.draw(spriteBatch);
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
