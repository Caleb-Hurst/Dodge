package com.dodge.game.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.dodge.game.domain.Asteroid;
import com.dodge.game.domain.Enemy;
import com.dodge.game.domain.Explosion;
import com.dodge.game.domain.Ship;

public class AsteroidService {
	private ArrayList<Asteroid> asteroids = new ArrayList<>();
	private ObjectManagerService objectManagerService = new ObjectManagerService();
	private SoundManagerService soundManagerService = new SoundManagerService();
	private int x = 4;
	private boolean isAsteroidTimerActive = true;
	private boolean isGameTimerActive = true;

	public void increaseIntensity() {
		if (isGameTimerActive) {
			isGameTimerActive = false;
			Timer.schedule(new Timer.Task() {
				@Override
				public void run() {

					if (x >= 2) {
						x--;
						System.out.println(x);
					}

					isGameTimerActive = true;

				}
			},  15);
		}
	}

	public void generateAsteroidEvery10Seconds(Ship playerShip) {
		if (isAsteroidTimerActive) {
			isAsteroidTimerActive = false;
			Timer.schedule(new Timer.Task() {
				@Override
				public void run() {
					
					soundManagerService.asteroid();
					Timer.schedule(new Timer.Task() {
						@Override
						public void run() {
							Asteroid asteroid = objectManagerService.createAsteroid(playerShip);
							asteroid.setActive(true);
							asteroids.add(asteroid);
							isAsteroidTimerActive = true;
						}
					}, 1);
					
				}
			}, x);
		}
	}
	

	public void updateAsteroids(float delta, Ship playerShip, ArrayList<Enemy> enemies, Explosion explosion) {
		Iterator<Asteroid> iterator = asteroids.iterator();
		List<Asteroid> asteroidsToRemove = new ArrayList<>();
		while (iterator.hasNext()) {
			Asteroid currentAsteroid = iterator.next();

			// Calculate the movement along x and y axes based on the current angle
			float deltaX = currentAsteroid.getSpeed() * delta * MathUtils.cosDeg(currentAsteroid.getAngle());
			float deltaY = -currentAsteroid.getSpeed() * delta * MathUtils.sinDeg(currentAsteroid.getAngle());

			// Move the asteroid based on calculated values
			currentAsteroid.getSprite().translate(deltaY, deltaX);
			for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext();) {
				Enemy enemy = enemyIterator.next();
				Rectangle enemyBoundingBox = new Rectangle(enemy.getSprite().getBoundingRectangle());
				// Adjust the enemy bounding box size as needed
				enemyBoundingBox.width *= 0.4f; // Make it 80% of the original width
				enemyBoundingBox.height *= 0.4f; // Make it 80% of the original height
				Rectangle laserBoundingBox = new Rectangle(currentAsteroid.getSprite().getBoundingRectangle());

				if (laserBoundingBox.overlaps(enemyBoundingBox)) {
					// Handle collision, e.g., mark the laser and enemy as inactive
					currentAsteroid.setActive(false);
					enemy.setActive(false);

					// Remove objects or add to a list for removal (based on your design)
					// Crashes when you shoot two ships at once.
					if (currentAsteroid != null) {
						asteroidsToRemove.add(currentAsteroid);
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
			// Optionally, you can add logic to check if the asteroid goes off the screen
			// and
			// handle it accordingly
			if (currentAsteroid.getSprite().getY() > Gdx.graphics.getHeight() || currentAsteroid.getSprite().getY() < 0
					|| currentAsteroid.getSprite().getX() < 0
					|| currentAsteroid.getSprite().getX() > Gdx.graphics.getWidth()) {
				iterator.remove(); // Remove the asteroid if it goes off the screen
			}
		}
		asteroids.removeAll(asteroidsToRemove);
	}
	public void holdExplosionOnScreen(Explosion explosion) {

		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				explosion.setActive(false);
			}
		}, 1);

	}
	public ArrayList<Asteroid> getAsteroids() {
		return asteroids;
	}
}
