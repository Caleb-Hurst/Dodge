package com.dodge.game.utils;

import java.util.Random;

import com.badlogic.gdx.utils.Timer;
import com.dodge.game.domain.GameIncrement;
import com.dodge.game.domain.Ship;

public class MathUtil {
	private Boolean isTimerDone = true;

	public int isScoreMultipleOfTen(Ship playerShip) {
		if (!playerShip.isMultipleOfTen()) {
			int score = playerShip.getScore();
			if (score % 10 == 0 && score != 0) {
				if (isTimerDone) {
					isTimerDone = false;
					playerShip.setMultipleOfTen(true);
					Timer.schedule(new Timer.Task() {
						@Override
						public void run() {
							playerShip.setMultipleOfTen(false);
							isTimerDone = true;
						}
					}, 4);
				} else {
					playerShip.setMultipleOfTen(false);
				}
				return score;
			}
		}
		return 0;
	}

	public void multiplySpeedIncrement(Ship playerShip, GameIncrement gameIncrement) {
		int a = playerShip.getScore();
		int b = gameIncrement.getGameScoreIncrement();
		int c = gameIncrement.getGameScoreIncrementCounter();
		int d = gameIncrement.getPreviousGameScoreIncrement();
		if (a == b && c == 0 && a != 0) {
			b += d;
			gameIncrement.setPreviousGameScoreIncrement(d);
			gameIncrement.setGameScoreIncrement(b);
			System.out.println("next event at " + gameIncrement.getGameScoreIncrement());
			c++;
			gameIncrement.setGameScoreIncrementCounter(c);
		} else if (a >= b && c == 1) {
			d *= 1.7;
			gameIncrement.setGameScoreIncrement(d + a);
			gameIncrement.setPreviousGameScoreIncrement(d);
			System.out.println("2nd loop next event at " + gameIncrement.getGameScoreIncrement());
			c = 0;
			gameIncrement.setGameScoreIncrementCounter(c);
		}
	}

	public float multiplyByTwentyPercent(float x) {
		x *= 1.05;
		return x;
	}

	public float multiplyGenerationInterval(float x) {
		x *= .75; // increase generation by 50 percent
		return x;
	}

	public float generateRandomSpeed(float x) {
		Random random = new Random();
		float max = x + 150;
		float randomNumber = random.nextFloat() * (max - x) + x;
		return randomNumber;
	}
}
