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
		sprite.setOriginCenter();
		// maybe use for collision detection later on HERE
		boundingBox = new Rectangle(x, y, width, height);
	}

	public void setPosition(float x, float y) {
		sprite.setPosition(x, y);
		boundingBox.setPosition(x, y);
	}

	public void setOrigin(float x, float y) {
		sprite.setOrigin(x, y);
	}

	public Float getRotation() {
		return sprite.getRotation();
	}

	public void rotate(float degrees) {
//		sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
		sprite.rotate(degrees);

	}

	public void move(float deltaX, float deltaY) {
		sprite.translate(deltaX, deltaY);
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

	public float getOriginX() {
		// TODO Auto-generated method stub
		return sprite.getOriginX();
	}
	public float getOriginY() {
		return sprite.getOriginY();
	}

}
