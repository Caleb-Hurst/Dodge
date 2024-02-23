package com.dodge.game.domain;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Ship {
	
	private Sprite sprite;
	private Rectangle boundingBox;
	
	public Ship(float x, float y, float width, float height, String texturePath) {
		Texture shipTexture = new Texture(texturePath);
		sprite = new Sprite(shipTexture);
		
		// Set position and size 
		sprite.setPosition(x, y);
		sprite.setSize(width, height);
		
		// maybe use for collision detection later on HERE
		boundingBox = new Rectangle(x, y, width, height);
	}
	public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
        boundingBox.setPosition(x, y);
    }

    public void draw(Batch batch) {
        sprite.draw(batch);
    }

    public float getX() {
        return sprite.getX();
    }

    public float getY() {
        return sprite.getY();
    }

    public float getWidth() {
        return sprite.getWidth();
    }

    public float getHeight() {
        return sprite.getHeight();
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void dispose() {
        sprite.getTexture().dispose();
    }

}
