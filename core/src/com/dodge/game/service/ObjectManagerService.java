package com.dodge.game.service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
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

        // Calculate the initial position for centering the ship
        float shipWidth = 64;  // Replace with the actual width of your ship
        float shipHeight = 64; // Replace with the actual height of your ship
        float initialX = (screenWidth - shipWidth) / 2;
        float initialY = (screenHeight - shipHeight) / 2;

        // Create and return a new player ship
        return new Ship(initialX, initialY, shipWidth, shipHeight, "ship.png");
    }
	
}
