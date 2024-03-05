package com.dodge.game.service;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.dodge.game.domain.Laser;
import com.dodge.game.domain.Ship;

public class LaserService {
	private ObjectManagerService objectManagerService = new ObjectManagerService();
	SoundManagerService soundManagerService = new SoundManagerService();
	public ArrayList<Laser> lasers = new ArrayList<>();

	public ArrayList<Laser> shoot(Ship playerShip) {
		Laser laser = objectManagerService.createPlayerLaser(playerShip);
		soundManagerService.laser();
		float spriteCenterX = playerShip.getX() + laser.getSprite().getWidth() / 2;
		float spriteBottomY = playerShip.getY() + -20f; // Change to bottom
		// Set the origin to the bottom-center of the sprite
		laser.getSprite().setOrigin(laser.getSprite().getWidth() / 2, 0);
		// Calculate a position relative to the bottom-center of the sprite
		float offsetX = 25f;
		// offset from the center along the x-axis;
		float offsetY = -50f;
		// offset from the bottom along the y-axis;
		float targetX = spriteCenterX + offsetX;
		// Subtract offsetY from the bottom Y
		float targetY = spriteBottomY - offsetY; 
		laser.getSprite().setPosition(targetX, targetY);
		laser.getSprite().setRotation(playerShip.getRotation());
		lasers.add(laser);
		laser.setActive(true);
		return lasers;
	}

	public void update(float delta, float initialAngle) {
		Iterator<Laser> iterator = lasers.iterator();
		while (iterator.hasNext()) {
			Laser currentLaser = iterator.next();
			// Calculate the movement along x and y axes based on the initial angle
			float deltaX = currentLaser.getSpeed() * delta * MathUtils.cosDeg(currentLaser.getAngle());
			float deltaY = -currentLaser.getSpeed() * delta * MathUtils.sinDeg(currentLaser.getAngle());

			// Move the laser based on calculated values
			currentLaser.getSprite().translate(deltaY, deltaX);

			// Optionally, you can add logic to check if the laser goes off the screen and
			// handle it accordingly
			if (currentLaser.getSprite().getY() > Gdx.graphics.getHeight() || currentLaser.getSprite().getY() < 0
					|| currentLaser.getSprite().getX() < 0
					|| currentLaser.getSprite().getX() > Gdx.graphics.getWidth()) {
				iterator.remove(); // Remove the laser if it goes off the screen
			}
		}
	}

	public ArrayList<Laser> getLasers() {
		return lasers;
	}


}
