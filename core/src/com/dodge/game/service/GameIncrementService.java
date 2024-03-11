package com.dodge.game.service;

import java.util.Random;

import com.dodge.game.domain.GameIncrement;
import com.dodge.game.domain.ObjectSpeed;
import com.dodge.game.domain.Ship;
import com.dodge.game.utils.MathUtil;

public class GameIncrementService {
	private GameIncrement gameIncrement;
	private MathUtil mathUtil = new MathUtil();

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
		}
		mathUtil.multiplySpeedThreshold(playerShip, gameIncrement);
		
	}

	private ObjectSpeed setObjectSpeeds(ObjectSpeed objectSpeed) {
		if (!objectSpeed.isHasBeenSet()) {
			objectSpeed.setLaserSpeed(400);
			objectSpeed.setAsteroidSpeed(170);
			objectSpeed.setGenerateAsteroidInterval(3);
			objectSpeed.setEnemyShipSpeed(200);
			objectSpeed.setGenerateEnemyShipInterval(5);
			objectSpeed.setGenerateEnemyShipInterval(2);
			objectSpeed.setRandomAsteroidSpeed(generateRandomSpeed(objectSpeed.getAsteroidSpeed()));
			objectSpeed.setRandomEnemyLaserSpeed(generateRandomSpeed(objectSpeed.getLaserSpeed()));
			objectSpeed.setRandomEnemyShipSpeed(objectSpeed.getEnemyShipSpeed());
			objectSpeed.setChaosAsteroidSpeed(170);
			objectSpeed.setHasBeenSet(true);
		}
		return objectSpeed;
	}

	private float generateRandomSpeed(float x) {
		Random random = new Random();
		float max = x + 20;
		float randomNumber = random.nextFloat(max - x + 1) + x;
		return randomNumber;
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
		System.out.println(asteroidGenerationInterval);
	}

}
