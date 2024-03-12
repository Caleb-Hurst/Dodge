package com.dodge.game.service;

import java.util.Random;

import com.badlogic.gdx.utils.Timer;
import com.dodge.game.domain.Asteroid;
import com.dodge.game.domain.GameIncrement;
import com.dodge.game.domain.ObjectSpeed;
import com.dodge.game.domain.Ship;
import com.dodge.game.utils.MathUtil;

public class GameIncrementService {
	private GameIncrement gameIncrement;
	private MathUtil mathUtil = new MathUtil();
	private SoundManagerService soundManagerService = new SoundManagerService();

	public GameIncrementService(GameIncrement gameIncrement) {
		this.gameIncrement = gameIncrement;
		ObjectSpeed objectSpeed = gameIncrement.getObjectSpeed();
		setObjectSpeeds(objectSpeed);
	}

	public void increaseGameSpeed(Ship playerShip, GameIncrement gameIncrement) {
		int score = playerShip.getScore();
		int threshold = gameIncrement.getGameScoreIncrement();
		
		int previousThreshold = gameIncrement.getPreviousGameScoreIncrement();
		if (score == threshold && score != previousThreshold) {
			multiplyObjectSpeed(gameIncrement);
			gameIncrement.setAsteroidEventHappening(true);
			System.out.println(threshold);
			Timer.schedule(new Timer.Task() {
				@Override
				public void run() {
					gameIncrement.setAsteroidEventHappening(false);				
				}
			}, 20);
			
		}
		mathUtil.multiplySpeedThreshold(playerShip, gameIncrement);
	}

	private ObjectSpeed setObjectSpeeds(ObjectSpeed objectSpeed) {
		if (!objectSpeed.isHasBeenSet()) {
			objectSpeed.setLaserSpeed(400);
			objectSpeed.setAsteroidSpeed(200);
			objectSpeed.setGenerateAsteroidInterval(3);
			objectSpeed.setGenerateAsteroidEventInterval(.7f);
			objectSpeed.setAsteroidEventSpeed(mathUtil.generateRandomSpeed(objectSpeed.getAsteroidSpeed()));
			objectSpeed.setEnemyShipSpeed(150);
			objectSpeed.setGenerateEnemyShipInterval(3);
			objectSpeed.setRandomAsteroidSpeed(mathUtil.generateRandomSpeed(objectSpeed.getAsteroidSpeed()));
			objectSpeed.setRandomEnemyLaserSpeed(mathUtil.generateRandomSpeed(objectSpeed.getLaserSpeed()));
			objectSpeed.setRandomEnemyShipSpeed(objectSpeed.getEnemyShipSpeed());
			objectSpeed.setChaosAsteroidSpeed(120);
			objectSpeed.setHasBeenSet(true);
		}
		return objectSpeed;
	}

	

	private void multiplyObjectSpeed(GameIncrement gameIncrement) {
		float getLaserSpeed = gameIncrement.getObjectSpeed().getLaserSpeed();
		float laserSpeed = mathUtil.multiplyByTwentyPercent(getLaserSpeed);
		gameIncrement.getObjectSpeed().setLaserSpeed(laserSpeed);

		float getAsteroidSpeed = gameIncrement.getObjectSpeed().getAsteroidSpeed();
		float asteroidSpeed = mathUtil.multiplyByTwentyPercent(getAsteroidSpeed);
		gameIncrement.getObjectSpeed().setAsteroidSpeed(asteroidSpeed);

		float getEnemyShipSpeed = gameIncrement.getObjectSpeed().getEnemyShipSpeed();
		float enemyShipSpeed = mathUtil.multiplyByTwentyPercent(getEnemyShipSpeed);
		gameIncrement.getObjectSpeed().setEnemyShipSpeed(enemyShipSpeed);
		
		float getEnemyGenerationInterval = gameIncrement.getObjectSpeed().getGenerateEnemyShipInterval();
		float enemyGenerationInterval = mathUtil.multiplyGenerationInterval(getEnemyGenerationInterval);
		gameIncrement.getObjectSpeed().setGenerateEnemyShipInterval(enemyGenerationInterval);
		
		float getAsteroidGenerationInterval = gameIncrement.getObjectSpeed().getGenerateAsteroidInterval();
		float asteroidGenerationInterval = mathUtil.multiplyGenerationInterval(getAsteroidGenerationInterval);
		gameIncrement.getObjectSpeed().setGenerateAsteroidInterval(asteroidGenerationInterval);
		
		float getAsteroidEventGenerationInterval = gameIncrement.getObjectSpeed().getGenerateAsteroidEventInterval();
		float asteroidEventGenerationInterval = mathUtil.multiplyGenerationInterval(getAsteroidEventGenerationInterval);
		gameIncrement.getObjectSpeed().setGenerateAsteroidEventInterval(asteroidEventGenerationInterval);
	}
	
//	private void multiplyObjectGeneration(GameIncrement gameIncrement) {
//		float getShipGenerationInterval = gameIncrement.getObjectSpeed().getGenerateEnemyShipInterval();
//		float shipGenerationInterval = mathUtil.multiplyGenerationInterval(getShipGenerationInterval);
//		gameIncrement.getObjectSpeed().setGenerateEnemyShipInterval(shipGenerationInterval);
//		
//		float getAsteroidGenerationInterval = gameIncrement.getObjectSpeed().getGenerateAsteroidInterval();
//		float asteroidGenerationInterval = mathUtil.multiplyGenerationInterval(getAsteroidGenerationInterval);
//		gameIncrement.getObjectSpeed().setGenerateAsteroidInterval(asteroidGenerationInterval);
//		
//		float getAsteroidEventGenerationInterval = gameIncrement.getObjectSpeed().getGenerateAsteroidEventInterval();
//		float asteroidEventGenerationInterval = mathUtil.multiplyGenerationInterval(getAsteroidEventGenerationInterval);
//		gameIncrement.getObjectSpeed().setGenerateAsteroidEventInterval(asteroidEventGenerationInterval);
//	}

}
