package com.dodge.game.service;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dodge.game.domain.Enemy;
import com.dodge.game.domain.Laser;
import com.dodge.game.domain.Ship;

public class ObjectManagerService {
	public ArrayList<Enemy> enemies = new ArrayList<>();

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
		laser.setSprite("laser-2.png");
		laser.setSpeed(300);
		laser.setWidth(6);
		laser.setHeight(40);
		laser.setSize(6, 40);
		laser.setX(playerShip.getX() + playerShip.getWidth() / 2 - laser.getWidth() / 2);
		laser.setY(playerShip.getY() + playerShip.getHeight());
		// Create and return a new player laser
		return laser;
	}

	public Enemy createEnemy(Ship playerShip, Viewport viewport) {
		Random random = new Random();
		Enemy enemy = new Enemy();
		float test = viewport.getWorldWidth();
		System.out.println(test);
		float randomX = random.nextFloat() *  480; // Random number between 480 and 500
		float randomY = random.nextFloat() *  800; // Random number between 800 and 820

	    System.out.println(randomY);
	    System.out.println(randomX);
	    enemy.setSprite("enemy-ship.png");
		float playerShipY = playerShip.getSprite().getY();
		float playerShipX = playerShip.getSprite().getX();
		float enemyShipX = enemy.getSprite().getX();
		float enemyShipY = enemy.getSprite().getY();
		float angleToPlayer = MathUtils.atan2(playerShipY - enemyShipY, playerShipX - enemyShipX) * MathUtils.radiansToDegrees;
		angleToPlayer = (angleToPlayer + 360) % 360; // Convert negative angles to positive in the range [0, 360)
		enemy.setAngle(angleToPlayer);
		enemy.setActive(true);
		enemy.setSize(70, 60);		
		enemy.getSprite().setOriginCenter();
		enemy.setSpeed(100);
		// random position for working code 
//		enemy.getSprite().setOriginBasedPosition(randomX,randomY);
		// temporary position for dev 
		enemy.getSprite().setPosition(randomX, randomY);
		enemies.add(enemy);
		return enemy;
	}

}
