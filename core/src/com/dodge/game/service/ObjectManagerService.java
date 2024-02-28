package com.dodge.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.dodge.game.domain.Laser;
import com.dodge.game.domain.Ship;

public class ObjectManagerService {
	
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
	public static Laser createPlayerLaser(Ship playerShip) {
	    // size of the player laser
	    float laserWidth = 6;  
	    float laserHeight = 40; 

	    // Calculate the initial position based on the ship's current position
	    float initialX = playerShip.getX() + playerShip.getWidth() / 2 - laserWidth / 2;
	    float initialY = playerShip.getY() + playerShip.getHeight();

	    // Create and return a new player laser
	    return new Laser(initialX, initialY, laserWidth, laserHeight, "laser-2.png",300,playerShip.getRotation());
	}
	
}
