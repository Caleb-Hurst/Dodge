package com.dodge.game.service;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.dodge.game.domain.Enemy;
import com.dodge.game.domain.Laser;
import com.dodge.game.domain.Ship;

public class InputHandlerService {

	public InputHandlerService(LaserService laserService, EnemyService enemyService) {
		this.laserService = laserService;
		this.enemyService = enemyService;
	}

	private LaserService laserService;
	private EnemyService enemyService;
	private boolean spaceBarPressedLastFrame = false;

	public void handleArrowInput(float delta, Ship playerShip, Laser laser) {
		float speed = 250 * delta; // Adjust the speed
		float rotationSpeed = 500;
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			playerShip.move(0, speed);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			playerShip.move(0, -speed);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			playerShip.move(-speed, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			playerShip.move(speed, 0);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			playerShip.rotate(rotationSpeed * delta);
//	        playerLaser.rotate(rotationSpeed * delta);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			playerShip.rotate(-rotationSpeed * delta);
//	        playerLaser.rotate(rotationSpeed * delta);
		}

	}

	public ArrayList<Laser> handleSpacebarInput(Ship playerShip) {
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			if (!spaceBarPressedLastFrame) {
				ArrayList<Laser> lasers = laserService.playerShoot(playerShip);
				spaceBarPressedLastFrame = true;
				return lasers;
			}
		} else {
			spaceBarPressedLastFrame = false;
		}
		return new ArrayList<>();
	}


}
