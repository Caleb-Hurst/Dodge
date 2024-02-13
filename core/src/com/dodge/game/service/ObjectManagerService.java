package com.dodge.game.service;

import com.badlogic.gdx.math.Rectangle;

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
	
}
