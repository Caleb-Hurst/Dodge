package com.dodge.game.service;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.dodge.game.domain.Asteroid;
import com.dodge.game.domain.BackgroundMusic;
import com.dodge.game.domain.Enemy;
import com.dodge.game.domain.Explosion;
import com.dodge.game.domain.GameIncrement;
import com.dodge.game.domain.Laser;
import com.dodge.game.domain.ObjectSpeed;
import com.dodge.game.domain.Ship;
import com.dodge.game.utils.MathUtil;

public class ObjectManagerService {
	public ArrayList<Enemy> enemies = new ArrayList<>();
	private MathUtil mathUtil = new MathUtil();
	private int x = 1;
	private int y = 1;

	// generate any object and position on screen
	public Rectangle generateGameObject(String name, int height, int width, float x, float y) {
		Rectangle gameObject = new Rectangle();
		gameObject.height = height;
		gameObject.width = width;
		gameObject.x = x;
		gameObject.y = y;
		return gameObject;
	}

	public static Ship createPlayerShip() {
		// Get screen dimensions
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();

		// size of the player ship
		float shipWidth = 64;
		float shipHeight = 64;
		// calculate the position of the ship to the center
		// by subtracting each width and height divided by 2 to find the center
		float initialX = (screenWidth - shipWidth) / 2;
		float initialY = (screenHeight - shipHeight) / 2;
		// Create and return a new player ship
		return new Ship(initialX, initialY, shipWidth, shipHeight, "ship.png");
	}

	public Laser createPlayerLaser(Ship playerShip) {
		Laser laser = new Laser();
		laser.setAngle(playerShip.getRotation());
		laser.setSprite("laser-3.png");
		laser.setSpeed(300);
		laser.setWidth(6);
		laser.setHeight(40);
		laser.setSize(6, 40);
		laser.setX(playerShip.getX() + playerShip.getWidth() / 2 - laser.getWidth() / 2);
		laser.setY(playerShip.getY() + playerShip.getHeight());
		// Create and return a new player laser
		return laser;
	}

	public Laser createEnemyLaser(Enemy enemyShip, ObjectSpeed objectSpeed) {
		Laser laser = new Laser();
		laser.setAngle(enemyShip.getRotation());
		laser.setSprite("laser-2.png");
		laser.setSpeed(objectSpeed.getLaserSpeed());
		laser.setWidth(6);
		laser.setHeight(40);
		laser.setSize(6, 40);
		laser.setX(enemyShip.getX() + enemyShip.getWidth() / 2 - laser.getWidth() / 2);
		laser.setY(enemyShip.getY() + enemyShip.getHeight());
		// Create and return a new player laser
		return laser;
	}

	public Enemy createEnemy(Ship playerShip, ObjectSpeed objectSpeed) {
		Random random = new Random();
		Enemy enemy = new Enemy();
		float randomX = random.nextFloat() * 800; // Random number between 0 and 480
		float randomY = random.nextFloat() * 480; // Random number between 0 and 800
		enemy.setSprite("enemy-ship.png");
		float playerShipY = playerShip.getSprite().getY();
		float playerShipX = playerShip.getSprite().getX();
		float enemyShipX = enemy.getSprite().getX();
		float enemyShipY = enemy.getSprite().getY();
		float angleToPlayer = MathUtils.atan2(playerShipY - enemyShipY, playerShipX - enemyShipX)
				* MathUtils.radiansToDegrees;
		angleToPlayer = (angleToPlayer + 360) % 360; // Convert negative angles to positive in the range [0, 360)
		enemy.setLasers(new ArrayList<Laser>());
		enemy.setAngle(angleToPlayer);
		enemy.setActive(true);
		enemy.setSize(70, 60);
		enemy.getSprite().setOriginCenter();
		enemy.setSpeed(objectSpeed.getEnemyShipSpeed());
		enemy.setBoundingBox(new Rectangle());
		// random position for working code
//		enemy.getSprite().setOriginBasedPosition(randomX,randomY);
		// temporary position for dev
		switch (x) {
		case 1:
			enemy.getSprite().setPosition(0, randomY + 20);
			break;
		case 2:
			enemy.getSprite().setPosition(randomX + 20, 0);
			break;
		case 3:
			enemy.getSprite().setPosition(Gdx.graphics.getWidth(), randomY + 20);
			break;
		case 4:
			enemy.getSprite().setPosition(randomX + 20, Gdx.graphics.getHeight());
			break;
		}

		x = (x % 4) + 1;
		enemies.add(enemy);
		return enemy;
	}

	public static Explosion createExplosion() {
		Explosion explosion = new Explosion();
		explosion.setSprite("explosion.png");
		explosion.setSize(70, 60);
		return explosion;

	}

	public Asteroid createAsteroid(Ship playerShip, ObjectSpeed objectSpeed) {
		Random random = new Random();
		float randomX = random.nextFloat() * 800; // Random number between 0 and 800
		float randomY = random.nextFloat() * 480;

		Asteroid asteroid = new Asteroid();
		asteroid.setSprite("asteroid.png");

		// Set asteroid position based on random side
		switch (y) {
		case 1:
			asteroid.getSprite().setPosition(0, randomY + 20);
			break;
		case 2:
			asteroid.getSprite().setPosition(randomX + 20, 0);
			break;
		case 3:
			asteroid.getSprite().setPosition(Gdx.graphics.getWidth(), randomY + 20);
			break;
		case 4:
			asteroid.getSprite().setPosition(randomX + 20, Gdx.graphics.getHeight());
			break;
		}

		y = (y % 4) + 1;

		// Set angle towards player ship
		Vector2 playerPosition = new Vector2(playerShip.getSprite().getX(), playerShip.getSprite().getY());
		Vector2 asteroidPosition = new Vector2(asteroid.getSprite().getX(), asteroid.getSprite().getY());
		float angleRad = MathUtils.atan2(playerPosition.y - asteroidPosition.y, playerPosition.x - asteroidPosition.x);
		float angleDeg = MathUtils.radiansToDegrees * angleRad + 270f;

		asteroid.setAngle(angleDeg);
		asteroid.setRotation(angleDeg - 45);
		asteroid.getSprite().setSize(70, 70);
		asteroid.setSpeed(objectSpeed.getAsteroidSpeed());
		asteroid.getSprite().setOriginCenter();

		return asteroid;
	}

	public Asteroid createEventAsteroid(Ship playerShip, ObjectSpeed objectSpeed) {
		Random random = new Random();

		float randomX = random.nextFloat() * 800; // Random number between 0 and 800
		float randomY = random.nextFloat() * 480;
		float randomSize = random.nextFloat() * (250 - 70) + 70;
		float randomDirection = random.nextFloat() * (282 - 270) + 270;

		Asteroid asteroid = new Asteroid();
		asteroid.setSprite("asteroid.png");

		int side = random.nextInt(4) + 1; // Random value between 1 and 4 representing a side

		// Set initial position outside the screen based on the chosen side
		switch (side) {
		case 1:
			asteroid.getSprite().setPosition(-asteroid.getSprite().getWidth(), randomY + 20);
			break;
		case 2:
			asteroid.getSprite().setPosition(randomX + 20, Gdx.graphics.getHeight() + asteroid.getSprite().getHeight());
			break;
		case 3:
			asteroid.getSprite().setPosition(Gdx.graphics.getWidth() + asteroid.getSprite().getWidth(), randomY + 20);
			break;
		case 4:
			asteroid.getSprite().setPosition(randomX + 20, -asteroid.getSprite().getHeight());
			break;
		}

		// Set angle towards player ship
		Vector2 playerPosition = new Vector2(playerShip.getSprite().getX(), playerShip.getSprite().getY());
		Vector2 asteroidPosition = new Vector2(asteroid.getSprite().getX(), asteroid.getSprite().getY());
		float angleRad = MathUtils.atan2(playerPosition.y - asteroidPosition.y, playerPosition.x - asteroidPosition.x);
		float angleDeg = MathUtils.radiansToDegrees * angleRad + randomDirection;

		asteroid.setAngle(angleDeg);
		asteroid.setRotation(angleDeg - 45);
		asteroid.getSprite().setSize(randomSize, randomSize);
		asteroid.setSpeed(mathUtil.generateRandomSpeed(objectSpeed.getAsteroidEventSpeed()));
		asteroid.getSprite().setOriginCenter();
		return asteroid;
	}

	public Asteroid createMegaAsteroid(Ship playerShip) {
		Random random = new Random();
		float randomX = random.nextFloat() * 800; // Random number between 0 and 800
		float randomY = random.nextFloat() * 480;

		Asteroid asteroid = new Asteroid();
		asteroid.setSprite("asteroid.png");

		int side = random.nextInt(4) + 1; // Random value between 1 and 4 representing a side

		// Set initial position outside the screen based on the chosen side
		switch (side) {
		case 1: // Left side
			asteroid.getSprite().setPosition(-asteroid.getSprite().getWidth(), randomY + 20);
			break;
		case 2: // Top side
			asteroid.getSprite().setPosition(randomX + 20, Gdx.graphics.getHeight() + asteroid.getSprite().getHeight());
			break;
		case 3: // Right side
			asteroid.getSprite().setPosition(Gdx.graphics.getWidth() + asteroid.getSprite().getWidth(), randomY + 20);
			break;
		case 4: // Bottom side
			asteroid.getSprite().setPosition(randomX + 20, -asteroid.getSprite().getHeight());
			break;
		}

		// Rest of your code...

		x = (x % 4) + 1;

		// Set angle towards player ship
		Vector2 playerPosition = new Vector2(playerShip.getSprite().getX(), playerShip.getSprite().getY());
		Vector2 asteroidPosition = new Vector2(asteroid.getSprite().getX(), asteroid.getSprite().getY());
		float angleRad = MathUtils.atan2(playerPosition.y - asteroidPosition.y, playerPosition.x - asteroidPosition.x);
		float angleDeg = MathUtils.radiansToDegrees * angleRad + 282f;

		asteroid.setAngle(angleDeg);
		asteroid.setRotation(angleDeg - 45);
		asteroid.getSprite().setSize(700, 700);
		asteroid.setSpeed(100);
		asteroid.getSprite().setOriginCenter();
		return asteroid;
	}

	
}
