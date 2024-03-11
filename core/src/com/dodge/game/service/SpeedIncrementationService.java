package com.dodge.game.service;

import com.dodge.game.domain.Ship;

public class SpeedIncrementationService {

	private int y = 0;

	public int initiateBigAsteroid(Ship playerShip) {
		int x = playerShip.getScore();
		if (x == 50 || x > 50) {
			y = x * 2;
		}
		if (y == y / x) {
			x *= 2;
		}
		return x;
	}

}
