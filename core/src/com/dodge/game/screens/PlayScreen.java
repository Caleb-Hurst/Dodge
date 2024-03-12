package com.dodge.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.dodge.game.domain.Asteroid;
import com.dodge.game.domain.Enemy;
import com.dodge.game.domain.Explosion;
import com.dodge.game.domain.GameIncrement;
import com.dodge.game.domain.Laser;
import com.dodge.game.domain.Ship;
import com.dodge.game.main.DodgeGame;
import com.dodge.game.service.AsteroidEventService;
import com.dodge.game.service.AsteroidService;
import com.dodge.game.service.EnemyService;
import com.dodge.game.service.GameIncrementService;
import com.dodge.game.service.InputHandlerService;
import com.dodge.game.service.LaserService;
import com.dodge.game.service.ObjectManagerService;
import com.dodge.game.service.SoundManagerService;
import com.dodge.game.service.TextService;
import com.dodge.game.utils.MathUtil;

public class PlayScreen implements Screen {
	private SoundManagerService soundManagerService;
	private Ship playerShip;
	private Laser playerLaser;
	private SpriteBatch spriteBatch;
	private InputHandlerService inputHandlerService;
	private LaserService laserService;
	private ObjectManagerService objectManagerService = new ObjectManagerService();
	private EnemyService enemyService;
	private ShapeRenderer shapeRenderer;
	private BitmapFont font = new BitmapFont();
	private Explosion explosion;
	private AsteroidService asteroidService;
	private AsteroidEventService asteroidEventService = new AsteroidEventService();
	private Asteroid asteroid;
	private MathUtil mathUtil;
	private TextService textService = new TextService();
	private GameIncrement gameIncrement = new GameIncrement();
	private GameIncrementService  gameIncrementService = new GameIncrementService(gameIncrement);
	// move this to constants class
	private static final float TOP_AREA_HEIGHT = 500f;
	public PlayScreen(DodgeGame game) {
		this.laserService = new LaserService();
		this.soundManagerService = new SoundManagerService();
		this.inputHandlerService = new InputHandlerService(laserService, enemyService);
		this.playerShip = ObjectManagerService.createPlayerShip();
		this.spriteBatch = new SpriteBatch();
		this.enemyService = new EnemyService(laserService, objectManagerService);
		this.explosion = ObjectManagerService.createExplosion();
		this.asteroidService = new AsteroidService();
		this.asteroid = objectManagerService.createAsteroid(playerShip, gameIncrement.getObjectSpeed());
		this.mathUtil = new MathUtil();

	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
//		Music backgroundMusic = soundManagerService.playMusic("2021-10-19_-_Funny_Bit_-_www.FesliyanStudios.com.mp3");
//		soundManagerService.setVolume(backgroundMusic, .1f);
		shapeRenderer = new ShapeRenderer();
		font.getData().setScale(5);
		
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0, 1);
		gameIncrementService.increaseGameSpeed(playerShip,gameIncrement);
		inputHandlerService.handleArrowInput(delta, playerShip, playerLaser);
		inputHandlerService.handleSpacebarInput(playerShip);
		laserService.updatePlayerLaser(delta, playerShip, enemyService.getEnemies(), explosion);
		enemyService.updateEnemyShip(delta, playerShip);
		asteroidService.updateAsteroids(delta, playerShip,enemyService.getEnemies(), explosion);
		asteroidService.generateAsteroidWithIncrement(playerShip, gameIncrement);
		enemyService.generateEnemyEveryWithIncrementSeconds(playerShip, gameIncrement);
		mathUtil.isScoreMultipleOfTen(playerShip);
		soundManagerService.isMultipleOfTen(playerShip);
		asteroidEventService.generateAsteroidWithIncrement(playerShip, gameIncrement);
		asteroidEventService.updateAsteroids(delta, playerShip, enemyService.getEnemies(), explosion);
		spriteBatch.begin();
		textService.flashColors(playerShip, font);
		soundManagerService.playBackgroundMusic(gameIncrement);
		
		// Draw the score
		font.draw(spriteBatch, "Score: " + playerShip.getScore(), (Gdx.graphics.getWidth() / 2) - 150, Gdx.graphics.getHeight() - 10);
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
		for (Asteroid asteroid : asteroidService.getAsteroids()) {
			asteroid.draw(spriteBatch);
		}
		for (Asteroid asteroid : asteroidEventService.getAsteroids()) {
			asteroid.draw(spriteBatch);
		}
		explosion.draw(spriteBatch);
//		megaAsteroid.draw(spriteBatch);
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
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
