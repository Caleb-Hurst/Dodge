package com.dodge.game.service;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.dodge.game.domain.Enemy;
import com.dodge.game.domain.Laser;
import com.dodge.game.domain.Ship;

public class ObjectManagerService {
	public ArrayList<Enemy> enemies = new ArrayList<>();
	//generate any object and position on screen
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
	
	public Enemy createEnemy(Ship playerShip) {
		Enemy enemy = new Enemy();
		enemy.setAngle(playerShip.getRotation() - 180);
		enemies.add(enemy);
		return enemy;
	}
	
}
