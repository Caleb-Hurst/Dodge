package com.dodge.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.dodge.game.domain.Laser;
import com.dodge.game.domain.Ship;

public class LaserService {
	
	SoundManagerService soundManagerService = new SoundManagerService();
	
	public void shoot(Ship playerShip, Laser laser) {		
		soundManagerService.laser();
		float spriteCenterX = playerShip.getX() + laser.getSprite().getWidth() / 2;
		float spriteBottomY = playerShip.getY() + -20f; // Change to bottom
		// Set the origin to the bottom-center of the sprite
		laser.getSprite().setOrigin(laser.getSprite().getWidth() / 2, 0);
		// Calculate a position relative to the bottom-center of the sprite
		float offsetX = 25f;
		// offset from the center along the x-axis */;
		float offsetY = -50f;
		// offset from the bottom along the y-axis */;
		float targetX = spriteCenterX + offsetX;
		float targetY = spriteBottomY - offsetY; // Subtract offsetY from the bottom Y
		laser.getSprite().setPosition(targetX, targetY);
		laser.getSprite().setRotation(playerShip.getRotation());
		laser.setActive(true);
	}
	public void update(float delta, float initialAngle,Laser laser) {
		// Calculate the movement along x and y axes based on the initial angle
		float deltaX = laser.getSpeed() * delta * MathUtils.cosDeg(initialAngle);
		float deltaY = -laser.getSpeed() * delta * MathUtils.sinDeg(initialAngle); // Negate the sin component

		// Move the laser based on calculated values
		laser.getSprite().translate(deltaY, deltaX);

		// Optionally, you can add logic to check if the laser goes off the screen and
		// handle it accordingly
		if (laser.getSprite().getY() > Gdx.graphics.getHeight() || laser.getSprite().getY() < 0 || laser.getSprite().getX() < 0
				|| laser.getSprite().getX() > Gdx.graphics.getWidth()) {
			// Optionally, you can handle what should happen when the laser goes off the
			// screen
		}
	}
	
}
