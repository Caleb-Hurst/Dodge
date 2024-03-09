package com.dodge.game.utils;

import com.badlogic.gdx.utils.Timer;
import com.dodge.game.domain.Ship;

public class MathUtil {
	private Boolean isTimerDone = true;

	public int isScoreMultipleOfTen(Ship playerShip) {

		if (!playerShip.isMultipleOfTen()) {
			if (isTimerDone) {
				isTimerDone = false;
				int score = playerShip.getScore();
				if (score % 10 == 0 && score != 0) {
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
}
