package com.dodge.game.utils;

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
		int x = playerShip.getScore();
		int y = gameIncrement.getGameScoreIncrement();
		int z = gameIncrement.getGameScoreIncrementCounter();
		if (x == y) {
			if(z<=2) {
				x *= 2;
				gameIncrement.setGameScoreIncrement(x);
				z++;
			}else {
				z = 1;
			}
		}
	}
}
