package com.dodge.game.service;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Timer;
import com.dodge.game.domain.Ship;

public class TextService {
	private int x = 0;
	private Boolean isTimerDone = true;

	public void flashColors(Ship playerShip, BitmapFont font) {
		if (playerShip.isMultipleOfTen()) {
			if (isTimerDone) {
				isTimerDone = false;
				Timer.schedule(new Timer.Task() {
					@Override
					public void run() {
						if (x == 0) {
							font.setColor(Color.BLUE);
						}
						if (x == 1) {
							font.setColor(Color.RED);
						}
						if (x == 2) {
							font.setColor(Color.GREEN);
						}
						if (x == 3) {
							font.setColor(Color.YELLOW);
						}
						if(x <=3) {
						x++;
						}else x = 0;
						Timer.schedule(new Timer.Task() {
							@Override
							public void run() {
								playerShip.setMultipleOfTen(false);
								font.setColor(Color.WHITE);
							}
						}, 1);
						
						isTimerDone = true;
					}
				}, .01f);

			} 
		}
	}

}
