package com.dodge.game.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.dodge.game.domain.Enemy;
import com.dodge.game.domain.Explosion;
import com.dodge.game.domain.Laser;
import com.dodge.game.domain.Ship;

public class LaserService {
	private ObjectManagerService objectManagerService = new ObjectManagerService();
	SoundManagerService soundManagerService = new SoundManagerService();
	public ArrayList<Laser> playerLasers = new ArrayList<>();
	public ArrayList<Laser> playerShoot(Ship playerShip) {
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
		playerLasers.add(laser);
		laser.setActive(true);
		return playerLasers;
	}

	public ArrayList<Laser> enemyShoot(Enemy enemyShip) {
		if (enemyShip.getSprite().getX() < Gdx.graphics.getWidth()) {
			Laser laser = objectManagerService.createEnemyLaser(enemyShip);
			soundManagerService.laser();
			float spriteCenterX = enemyShip.getX() + laser.getSprite().getWidth() / 2;
			float spriteBottomY = enemyShip.getY() + -20f; // Change to bottom
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
			laser.getSprite().setRotation(enemyShip.getRotation());
			enemyShip.getLasers().add(laser);
			laser.setActive(true);
//		System.out.println(x);
			return enemyShip.getLasers();
		}
		return new ArrayList<>();
	}

	public void updatePlayerLaser(float delta, Ship playerShip, ArrayList<Enemy> enemies, Explosion explosion) {
		int x = playerShip.getScore();
		Iterator<Laser> iterator = playerLasers.iterator();
		List<Laser> lasersToRemove = new ArrayList<>();
		while (iterator.hasNext()) {
			Laser currentLaser = iterator.next();
			// Calculate the movement along x and y axes based on the initial angle
			float deltaX = currentLaser.getSpeed() * delta * MathUtils.cosDeg(currentLaser.getAngle());
			float deltaY = -currentLaser.getSpeed() * delta * MathUtils.sinDeg(currentLaser.getAngle());

			// Move the laser based on calculated values
			currentLaser.getSprite().translate(deltaY, deltaX);
			for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext();) {
				Enemy enemy = enemyIterator.next();
				Rectangle enemyBoundingBox = new Rectangle(enemy.getSprite().getBoundingRectangle());
				// Adjust the enemy bounding box size as needed
				enemyBoundingBox.width *= 0.4f; // Make it 80% of the original width
				enemyBoundingBox.height *= 0.4f; // Make it 80% of the original height
				Rectangle laserBoundingBox = new Rectangle(currentLaser.getSprite().getBoundingRectangle());

				if (laserBoundingBox.overlaps(enemyBoundingBox)) {
					// Handle collision, e.g., mark the laser and enemy as inactive
					currentLaser.setActive(false);
					enemy.setActive(false);

					// Remove objects or add to a list for removal (based on your design)
					// Crashes when you shoot two ships at once.
					if (currentLaser != null) {
						lasersToRemove.add(currentLaser);
					}
					x++;
					float oldShipX = enemy.getSprite().getX();
					float oldShipY = enemy.getSprite().getY();
					enemy.getSprite().getY();
					enemyIterator.remove();
					soundManagerService.explosion();
					explosion.getSprite().setPosition(oldShipX, oldShipY);
					explosion.setActive(true);
					holdExplosionOnScreen(explosion);
		  	 		
				}
			}
			// Optionally, you can add logic to check if the laser goes off the screen and
			// handle it accordingly
			if (currentLaser.getSprite().getY() > Gdx.graphics.getHeight() || currentLaser.getSprite().getY() < 0
					|| currentLaser.getSprite().getX() < 0
					|| currentLaser.getSprite().getX() > Gdx.graphics.getWidth()) {
				iterator.remove(); // Remove the laser if it goes off the screen
			}
		}
	    playerLasers.removeAll(lasersToRemove);
	    playerShip.setScore(x);
	}

	public ArrayList<Laser> getPlayerLasers() {
		return playerLasers;
	}

	public void updateEnemyLaser(float delta, ArrayList<Laser> enemyLasers) {
		Iterator<Laser> iterator = enemyLasers.iterator();
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
				System.out.println("something was removed");
			}
		}
	}
	public void holdExplosionOnScreen(Explosion explosion) {

		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				explosion.setActive(false);
			}
		}, 1);

	}

}
