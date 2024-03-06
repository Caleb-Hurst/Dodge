package com.dodge.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dodge.game.domain.Enemy;
import com.dodge.game.domain.Laser;
import com.dodge.game.domain.Ship;
import com.dodge.game.main.DodgeGame;
import com.dodge.game.service.EnemyService;
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
	private LaserService laserService;
	private ObjectManagerService objectManagerService = new ObjectManagerService();
	private EnemyService enemyService;
	private Viewport viewport;

	public PlayScreen(DodgeGame game) {
		this.laserService = new LaserService();
		this.soundManagerService = new SoundManagerService();
		this.inputHandlerService = new InputHandlerService(laserService, enemyService);
		this.playerShip = ObjectManagerService.createPlayerShip();
		this.spriteBatch = new SpriteBatch();
		this.viewport = new FitViewport(820, 500);
		this.enemyService = new EnemyService(laserService, objectManagerService);

		// Apply the viewport to the camera
		this.viewport.setCamera(new OrthographicCamera());

		// Center the camera on the player ship initially
		this.viewport.getCamera().position.set(playerShip.getSprite().getX(), playerShip.getSprite().getY(), 0);
		this.viewport.getCamera().update();

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
//		Music backgroundMusic = soundManagerService.playMusic("2021-10-19_-_Funny_Bit_-_www.FesliyanStudios.com.mp3");
//		soundManagerService.setVolume(backgroundMusic, .1f);
		enemyService.generateEnemyEvery3Seconds(playerShip, viewport);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		inputHandlerService.handleArrowInput(delta, playerShip, playerLaser);
		inputHandlerService.handleSpacebarInput(playerShip);
		laserService.updatePlayerLaser(delta, playerShip.getRotation(), enemyService.getEnemies());
		enemyService.updateEnemyShip(delta, playerShip);

		spriteBatch.begin();

		playerShip.draw(spriteBatch);
		for (Laser laser : laserService.getPlayerLasers()) {
			laser.draw(spriteBatch);
		}
		playerShip.draw(spriteBatch);
		for (Enemy enemy : enemyService.getEnemies()) {
			if (enemy.getLasers() != null) {
				laserService.updateEnemyLaser(delta, enemy.getLasers());
				for (Laser laser : enemy.getLasers()) {
					laser.draw(spriteBatch);
				}
			}
		}
		for (Enemy enemy : enemyService.getEnemies()) {
			enemy.draw(spriteBatch);
		}
		spriteBatch.end();

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);

		// Center the camera on the player ship after the resize
		viewport.getCamera().position.set(playerShip.getSprite().getX(), playerShip.getSprite().getY(), 0);
		viewport.getCamera().update();
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
