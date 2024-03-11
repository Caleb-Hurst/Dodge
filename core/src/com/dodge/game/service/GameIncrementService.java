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

	public boolean increaseGameSpeed(Ship playerShip) {
		int score = mathUtil.isScoreMultipleOfTen(playerShip);
		boolean increaseSpeed = false;
		return increaseSpeed;
	}

	private ObjectSpeed setObjectSpeeds(ObjectSpeed objectSpeed) {
		if (objectSpeed.isHasBeenSet()) {
			objectSpeed.setLaserSpeed(400);
			objectSpeed.setAsteroidSpeed(170);
			objectSpeed.setEnemyShipSpeed(200);
			objectSpeed.setRandomAsteroidSpeed(generateRandomSpeed(objectSpeed.getAsteroidSpeed()));
			objectSpeed.setRandomEnemyLaserSpeed(generateRandomSpeed(objectSpeed.getLaserSpeed()));
			objectSpeed.setRandomEnemyShipSpeed(objectSpeed.getEnemyShipSpeed());
			objectSpeed.setChaosAsteroidSpeed(170);
			objectSpeed.setHasBeenSet(false);
		}
		return objectSpeed;
	}

	private float generateRandomSpeed(float x) {
		Random random = new Random();
		float max = x + 20;
		float randomNumber = random.nextFloat(max - x + 1) + x;
		return randomNumber;
	}
}
