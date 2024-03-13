package com.dodge.game.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.dodge.game.domain.Enemy;
import com.dodge.game.domain.Explosion;
import com.dodge.game.domain.GameIncrement;
import com.dodge.game.domain.ObjectSpeed;
import com.dodge.game.domain.Ship;

public class EnemyService {
	private ObjectManagerService objectManagerService;
	private SoundManagerService soundManagerService = new SoundManagerService();
	private LaserService laserService;
	private ArrayList<Enemy> enemies = new ArrayList<>();
	private Random random = new Random();
	private int x = 5;
	private CollisionDetectorService collisionDetectorService = new CollisionDetectorService();

	public EnemyService(LaserService laserService, ObjectManagerService objectManagerService) {
		this.laserService = laserService;
		this.objectManagerService = objectManagerService;
	}

	public boolean isEnemyTimerActive = true;
	public boolean isGameTimerActive = true;

	public void generateEnemyEveryWithIncrementSeconds(Ship playerShip, GameIncrement gameIncrement) {
		ObjectSpeed objectSpeed = gameIncrement.getObjectSpeed();
		if (!gameIncrement.isAsteroidEventHappening()) {
			if (isEnemyTimerActive) {
				isEnemyTimerActive = false;
				Timer.schedule(new Timer.Task() {
					@Override
					public void run() {
						if (playerShip.isAlive()) {
							createEnemies(playerShip, objectSpeed);
							isEnemyTimerActive = true;
						}
					}
				}, objectSpeed.getGenerateEnemyShipInterval());
			}
		}
	}

	private float generateRandomDelay() {
		return 2 + random.nextFloat() * 3;
	}

	public Enemy shootLaserEvery3Seconds(Enemy enemy, ObjectSpeed objectSpeed, Ship playerShip) {
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				if (enemy.getActive()) {
					if (playerShip.isAlive()) {
						laserService.enemyShoot(enemy, objectSpeed);
					}
				}
			}
		}, 2, generateRandomDelay());

		return enemy;
	}

	public ArrayList<Enemy> createEnemies(Ship playerShip, ObjectSpeed objectSpeed) {
		Enemy enemy = objectManagerService.createEnemy(playerShip, objectSpeed);
		shootLaserEvery3Seconds(enemy, objectSpeed, playerShip);
		enemy.getSprite().setRotation(playerShip.getRotation());
		enemy.setActive(true);
		enemies.add(enemy);
		return enemies;
	}

	public void updateEnemyShip(float delta, Ship playerShip, Explosion explosion) {
		Iterator<Enemy> iterator = enemies.iterator();
		while (iterator.hasNext()) {
			Enemy currentEnemy = iterator.next();
			boolean foundOverlapping = false;

			for (Enemy enemy : enemies) {
				Rectangle enemyLocation = enemy.getSprite().getBoundingRectangle();
				Rectangle currentEnemyLocation = currentEnemy.getSprite().getBoundingRectangle();
				if (enemies.size() >= 2) {
					if (enemy.hashCode() != currentEnemy.hashCode()) {
						if (enemyLocation.overlaps(currentEnemyLocation) && !enemy.isOverlaping()) {
							currentEnemy.setOverlaping(true);
							foundOverlapping = true;
							break; // Exit the loop as soon as an overlapping enemy is found
						} else {
							foundOverlapping = false;
						}
					}
				}
			}

			// Calculate the movement along x and y axes based on the initial angle
			Vector2 playerPosition = new Vector2(playerShip.getSprite().getX(), playerShip.getSprite().getY());
			Vector2 enemyPosition = new Vector2(currentEnemy.getSprite().getX(), currentEnemy.getSprite().getY());
			float angleRad = MathUtils.atan2(playerPosition.y - enemyPosition.y, playerPosition.x - enemyPosition.x);
			float angleDeg = MathUtils.radiansToDegrees * angleRad + 270f;
			currentEnemy.getSprite().setRotation(angleDeg);

			if (foundOverlapping) {
				if (!currentEnemy.isDelay()) {
					currentEnemy.setDelay(true);
					Timer.schedule(new Timer.Task() {
						@Override
						public void run() {
							currentEnemy.setDelay(false);
							currentEnemy.setOverlaping(false);
						}
					}, 3);
				} else {
					// Do nothing until the delay completes
				}
			} else {
				// Calculate the movement regardless of delay
				Vector2 direction = playerPosition.sub(enemyPosition).nor();
				float deltaX = currentEnemy.getSpeed() * direction.x * delta;
				float deltaY = currentEnemy.getSpeed() * direction.y * delta;
				currentEnemy.getSprite().translate(deltaX, deltaY);
			}
			Rectangle enemyBoundingBox = collisionDetectorService.createEnemyHitBox(currentEnemy);
			Rectangle shipBoundingBox = collisionDetectorService.createPlayerShipHitBox(playerShip);
			collisionDetectorService.drawHitBoxEnemyShip(enemyBoundingBox);
			collisionDetectorService.drawHitBoxPlayerShip(shipBoundingBox);
			
			
			if (enemyBoundingBox.overlaps(shipBoundingBox)) {
				explosion.getSprite().setX(playerShip.getSprite().getX());
				explosion.getSprite().setY(playerShip.getSprite().getY());
				soundManagerService.explosion();
				playerShip.setAlive(false);
				Timer.schedule(new Timer.Task() {
					@Override
					public void run() {
						soundManagerService.playDeath();
					}
				}, .2f);
				explosion.setActive(true);
				holdExplosionOnScreen(explosion);
			}
			// Remove the enemy if it goes off the screen
			if (currentEnemy.getSprite().getY() > Gdx.graphics.getHeight() || currentEnemy.getSprite().getY() < 0
					|| currentEnemy.getSprite().getX() < 0
					|| currentEnemy.getSprite().getX() > Gdx.graphics.getWidth()) {
				iterator.remove();
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
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	public Explosion getExplosion() {
		return new Explosion();
	}

}
