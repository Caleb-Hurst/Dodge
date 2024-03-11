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
							System.out.println("IT WORKED");
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
	
	public void multiplySpeedThreshold(Ship playerShip, GameIncrement gameIncrement) {
		int a = playerShip.getScore();
		int b = gameIncrement.getGameScoreIncrement();
		int c = gameIncrement.getGameScoreIncrementCounter();
		if (a >= b) {
			gameIncrement.setPreviousGameScoreIncrement(b);
			if(c<2) {
				a *= 1.3;
				gameIncrement.setGameScoreIncrement(a);
				c++;
				System.out.println("New Increment for event " + gameIncrement.getGameScoreIncrement());
				if(c==2) {
					c = 1;
				}
			}
		}
		
	}
	
	public float multiplyByTwentyPercent(float x) {
		x *= 1.05;
		return x;
	}

	public float multiplyGenerationInterval(float x) {
		x *= .95; // increase generation by 50 percent
		return x;
	}
	public float generateRandomSpeed(float x) {
	    Random random = new Random();
	    float max = x + 500;
	    float randomNumber = random.nextFloat() * (max - x) + x;
	    return randomNumber;
	}
}
