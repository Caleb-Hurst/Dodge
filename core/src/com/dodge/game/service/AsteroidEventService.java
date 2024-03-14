package com.dodge.game.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.dodge.game.domain.Asteroid;
import com.dodge.game.domain.Enemy;
import com.dodge.game.domain.Explosion;
import com.dodge.game.domain.GameIncrement;
import com.dodge.game.domain.ObjectSpeed;
import com.dodge.game.domain.Ship;

public class AsteroidEventService {
	private ArrayList<Asteroid> asteroids = new ArrayList<>();
	private ObjectManagerService objectManagerService = new ObjectManagerService();
	private SoundManagerService soundManagerService = new SoundManagerService();
	private boolean isAsteroidTimerActive = true;
	private CollisionDetectorService collisionDetectorService = new CollisionDetectorService();

	public void generateAsteroidWithIncrement(Ship playerShip, GameIncrement gameIncrement) {
		ObjectSpeed objectSpeed = gameIncrement.getObjectSpeed();
		if (gameIncrement.isAsteroidEventHappening()) {
			if (isAsteroidTimerActive) {
				isAsteroidTimerActive = false;
				Timer.schedule(new Timer.Task() {
					@Override
					public void run() {

						Asteroid asteroid = objectManagerService.createEventAsteroid(playerShip, objectSpeed);

						asteroid.setActive(true);
						asteroids.add(asteroid);
						isAsteroidTimerActive = true;

					}
				}, objectSpeed.getGenerateAsteroidEventInterval());
			}
		}
	}

	public void updateAsteroids(float delta, Ship playerShip, ArrayList<Enemy> enemies, Explosion explosion) {
		Iterator<Asteroid> iterator = asteroids.iterator();
		List<Asteroid> asteroidsToRemove = new ArrayList<>();
		while (iterator.hasNext()) {
			Asteroid currentAsteroid = iterator.next();
			
			Circle currentAsteroidCircle = new Circle(currentAsteroid.getSprite().getX(),
					currentAsteroid.getSprite().getY(), currentAsteroid.getSprite().getWidth() / 2);
			
			// Calculate the movement along x and y axes based on the current angle
			float deltaX = currentAsteroid.getSpeed() * delta * MathUtils.cosDeg(currentAsteroid.getAngle());
			float deltaY = -currentAsteroid.getSpeed() * delta * MathUtils.sinDeg(currentAsteroid.getAngle());

			// Move the asteroid based on calculated values
			currentAsteroid.getSprite().translate(deltaY, deltaX);
			for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext();) {
				Enemy enemy = enemyIterator.next();
				Rectangle enemyBoundingBox = new Rectangle(enemy.getSprite().getBoundingRectangle());

				// Adjust the enemy bounding box size as needed
				enemyBoundingBox.width *= 0.2f; // Make it 80% of the original width
				enemyBoundingBox.height *= 0.2f; // Make it 80% of the original height
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

			for (Asteroid otherAsteroid : asteroids) {
				if (currentAsteroid != otherAsteroid) { // Avoid self-collision check
					Circle otherAsteroidCircle = new Circle(otherAsteroid.getSprite().getX(),
							otherAsteroid.getSprite().getY(), otherAsteroid.getSprite().getWidth() / 2);
					
					if (Intersector.overlaps(currentAsteroidCircle, otherAsteroidCircle)) {
						// Handle collision between asteroids

						// Remove objects or add to a list for removal
						asteroidsToRemove.add(currentAsteroid);
						asteroidsToRemove.add(otherAsteroid);
						soundManagerService.asteroidEventCollision();
						if (otherAsteroid.getSprite().getX() < currentAsteroid.getSprite().getX()) {

							float oldAsteroidX = otherAsteroid.getSprite().getX();
							float oldAsteroidY = otherAsteroid.getSprite().getY();
//		                    	explosion.getSprite().setPosition(oldAsteroidX, oldAsteroidY)
							explosion.getSprite().setPosition(oldAsteroidX, oldAsteroidY);
							holdExplosionOnScreen(explosion);
							explosion.getSprite().setSize(currentAsteroid.getSprite().getWidth(),
									currentAsteroid.getSprite().getHeight());
							explosion.setActive(true);
						}
						if (currentAsteroid.getSprite().getX() < otherAsteroid.getSprite().getX()) {

							float oldAsteroidX = currentAsteroid.getSprite().getX();
							float oldAsteroidY = currentAsteroid.getSprite().getY();
//		                    	currentAsteroid.getExplosion().getSprite().setPosition(oldAsteroidX, oldAsteroidY);
							explosion.getSprite().setPosition(oldAsteroidX, oldAsteroidY);
							explosion.getSprite().setSize(currentAsteroid.getSprite().getWidth() + 20,
									currentAsteroid.getSprite().getHeight() + 20);
							holdExplosionOnScreen(explosion);
							explosion.setActive(true);
							
						}
						currentAsteroid.setActive(false);
						otherAsteroid.setActive(false);
					}
					
				}
			}
			Rectangle shipRectangle = collisionDetectorService.createPlayerShipHitBox(playerShip);
			Rectangle asteroidRectangle = collisionDetectorService.createAsteroidHitBox(currentAsteroid);
//			collisionDetectorService.drawHitBoxAsteroid(asteroidRectangle, currentAsteroid);
//			collisionDetectorService.drawHitBoxPlayerShip(asteroidRectangle);
			float shipX = playerShip.getSprite().getX();
			float shipY = playerShip.getSprite().getY();		
			if(asteroidRectangle.overlaps(shipRectangle)) {
				explosion.getSprite().setPosition(shipX, shipY);
				soundManagerService.explosion();
				Timer.schedule(new Timer.Task() {
					@Override
					public void run() {
						soundManagerService.playDeath();
					}
				}, .2f);
				playerShip.setAlive(false);
				explosion.setActive(true);
				holdExplosionOnScreen(explosion);
			}
			// Optionally, you can add logic to check if the asteroid goes off the screen
			// and
			// handle it accordingly
			if (currentAsteroid.getSprite().getX() > Gdx.graphics.getWidth() + 800
					|| currentAsteroid.getSprite().getX() + currentAsteroid.getSprite().getWidth() < -800
					|| currentAsteroid.getSprite().getY() > Gdx.graphics.getHeight() + 800
					|| currentAsteroid.getSprite().getY() + currentAsteroid.getSprite().getHeight() < -800) {
				iterator.remove(); // Remove the asteroid if it goes too far outside the screen
				System.out.println("REMOVED ASTEROID IN EVENT");
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
